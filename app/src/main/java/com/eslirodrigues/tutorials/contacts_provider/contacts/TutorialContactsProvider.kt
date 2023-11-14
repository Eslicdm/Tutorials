package com.eslirodrigues.tutorials.contacts_provider.contacts

import android.content.ContentProviderOperation
import android.content.Context
import android.provider.ContactsContract
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject


// Rules for data integrity:
// Always add a ContactsContract.CommonDataKinds.StructuredName row for every ContactsContract.RawContacts row you add.
// Always link new ContactsContract.Data rows to their parent ContactsContract.RawContacts row.
// Change data only for those raw contacts that you own.
// Always use the constants defined in ContactsContract and its subclasses for authorities, content URIs, URI paths, column names, MIME types, and TYPE values.
// Use withYieldAllowed after every set of operations that affects a single contact and applyBatch

class TutorialContactsProvider @Inject constructor(
    @ApplicationContext private val context: Context
)  {
    private fun getContactsNames(): List<TutorialContact> {
        val contactsList = mutableListOf<TutorialContact>()
        context.contentResolver?.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )?.use { contactsCursor ->
            val idIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts._ID)
            val nameIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            while (contactsCursor.moveToNext()) {
                val id = contactsCursor.getString(idIndex)
                val name = contactsCursor.getString(nameIndex)
                name?.let { contactsList.add(TutorialContact(id, it)) }
            }
        }
        return contactsList
    }

    private fun getContactsNumbers(): Map<String, MutableList<String>> {
        val contactsNumbersMap = mutableMapOf<String, MutableList<String>>()
        context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )?.use { phoneCursor ->
            val contactIdIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val numberIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            while (phoneCursor.moveToNext()) {
                val contactId = phoneCursor.getString(contactIdIndex)
                val number = phoneCursor.getString(numberIndex)
                contactsNumbersMap.getOrPut(contactId) { mutableListOf() }.add(number)
            }
        }
        return contactsNumbersMap
    }

    private fun getContactsEmails(): Map<String, MutableList<String>> {
        val contactsEmailsMap = mutableMapOf<String, MutableList<String>>()
        context.contentResolver.query(
            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
            null,
            null,
            null,
            null
        )?.use { emailCursor ->
            val contactIdIndex = emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID)
            val emailIndex = emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)
            while (emailCursor.moveToNext()) {
                val contactId = emailCursor.getString(contactIdIndex)
                val email = emailCursor.getString(emailIndex)
                contactsEmailsMap.getOrPut(contactId) { mutableListOf() }.add(email)
            }
        }
        return contactsEmailsMap
    }

    suspend fun getContacts(): List<TutorialContact> = coroutineScope {
        val names = async(Dispatchers.IO) { getContactsNames() }.await()
        val numbers = async(Dispatchers.IO) { getContactsNumbers() }.await()
        val emails = async(Dispatchers.IO) { getContactsEmails() }.await()

        names.map { contact ->
            val contactNumbers = numbers[contact.id].orEmpty()
            val contactEmails = emails[contact.id].orEmpty()
            TutorialContact(contact.id, contact.name, contactNumbers, contactEmails)
        }
    }

    suspend fun insertContact(name: String, phone: String, email: String) = withContext(Dispatchers.IO) {
        val operations = ArrayList<ContentProviderOperation>()

        val contactOperation = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, "com.eslirodrigues.tutorials")
            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, name)
        operations.add(contactOperation.build())

        val nameOperation = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
        operations.add(nameOperation.build())

        val phoneOperation = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
        operations.add(phoneOperation.build())

        val emailOperation = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.Email.DATA, email)
            .withYieldAllowed(true)
        operations.add(emailOperation.build())

        try {
            context.contentResolver.applyBatch(ContactsContract.AUTHORITY, operations)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Insert Success", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Insert Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun updateContact(
        contactId: String, name: String, phones: List<String>, emails: List<String>
    ) = withContext(Dispatchers.IO) {
        val operations = ArrayList<ContentProviderOperation>()

        val updateNameOperation = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
            .withSelection(
                ContactsContract.Data.CONTACT_ID + "=? AND " +
                        ContactsContract.Data.MIMETYPE + "=?",
                arrayOf(contactId, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            )
            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
        operations.add(updateNameOperation.build())

        phones.forEach { phone ->
            val updatePhoneOperation = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                .withSelection(
                    ContactsContract.Data.CONTACT_ID + "=? AND " +
                            ContactsContract.Data.MIMETYPE + "=? AND " +
                            ContactsContract.CommonDataKinds.Phone.NUMBER + "=?",
                    arrayOf(contactId, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, phone)
                )
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
            operations.add(updatePhoneOperation.build())
        }

        emails.forEach { email ->
            val updateEmailOperation = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                .withSelection(
                    ContactsContract.Data.CONTACT_ID + "=? AND " +
                            ContactsContract.Data.MIMETYPE + "=? AND " +
                            ContactsContract.CommonDataKinds.Email.ADDRESS + "=?",
                    arrayOf(contactId, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE, email)
                )
                .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, email)
                .withYieldAllowed(true)
            operations.add(updateEmailOperation.build())
        }

        try {
            context.contentResolver.applyBatch(ContactsContract.AUTHORITY, operations)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Update Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun deleteContactById(contactId: String) = withContext(Dispatchers.IO) {
        val operations = ArrayList<ContentProviderOperation>()

        val rawContactUri = ContactsContract.RawContacts.CONTENT_URI.buildUpon()
            .appendPath(contactId)
            .build()

        val deleteOperation = ContentProviderOperation.newDelete(rawContactUri)
        operations.add(deleteOperation.build())

        try {
            context.contentResolver.applyBatch(ContactsContract.AUTHORITY, operations)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Delete Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}