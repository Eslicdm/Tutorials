package com.eslirodrigues.tutorials.contact_provider_intent.contacts

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.provider.ContactsContract
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TutorialIntentContactProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private fun getContactPhone(contactId: String): List<String> {
        val phoneNumbers = mutableListOf<String>()

        context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
            arrayOf(contactId),
            null
        )?.use { phoneCursor ->
            val phoneNumberIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            while (phoneCursor.moveToNext()) {
                val phoneNumber = phoneCursor.getString(phoneNumberIndex)
                phoneNumbers.add(phoneNumber)
            }
        }

        return phoneNumbers
    }

    private fun getContactEmail(contactId: String): List<String> {
        val emails = mutableListOf<String>()

        context.contentResolver.query(
            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
            arrayOf(contactId),
            null
        )?.use { emailCursor ->
            val emailIndex = emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)
            while (emailCursor.moveToNext()) {
                val email = emailCursor.getString(emailIndex)
                emails.add(email)
            }
        }

        return emails
    }

    fun getContactByUri(contactUri: Uri): TutorialIntentContact? {
        context.contentResolver.query(
            contactUri, null, null, null, null
        )?.use { contactCursor ->
            val contactIdIndex = contactCursor.getColumnIndex(ContactsContract.Contacts._ID)
            val contactNameIndex = contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

            while (contactCursor.moveToFirst()) {
                val contactId = contactCursor.getString(contactIdIndex)
                val contactName = contactCursor.getString(contactNameIndex)
                val phoneNumbers = getContactPhone(contactId)
                val emailAddresses = getContactEmail(contactId)

                return TutorialIntentContact(contactId, contactName, phoneNumbers, emailAddresses)
            }
        }
        return null
    }

    fun insertContactIntent(name: String, phone: String, email: String) {
        val intent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
            flags = FLAG_ACTIVITY_NEW_TASK
            type = ContactsContract.RawContacts.CONTENT_TYPE
            putExtra(ContactsContract.Intents.Insert.NAME, name)
            putExtra(ContactsContract.Intents.Insert.PHONE, phone)
            putExtra(
                ContactsContract.Intents.Insert.PHONE_TYPE,
                ContactsContract.CommonDataKinds.Phone.TYPE_WORK
            )
            putExtra(ContactsContract.Intents.Insert.EMAIL, email)
        }
        context.startActivity(intent)
    }

    fun editContact(contact: TutorialIntentContact) {
        val intent = Intent(Intent.ACTION_EDIT).apply {
            flags = FLAG_ACTIVITY_NEW_TASK
            data = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contact.id.toLong())
            putExtra(ContactsContract.Intents.Insert.NAME, contact.name)
            putExtra(ContactsContract.Intents.Insert.PHONE, contact.phones.last())
            putExtra(
                ContactsContract.Intents.Insert.PHONE_TYPE,
                ContactsContract.CommonDataKinds.Phone.TYPE_WORK
            )
            putExtra(ContactsContract.Intents.Insert.EMAIL, contact.emails.last())
        }
        context.startActivity(intent)
    }

    suspend fun deleteContactById(contactId: Long) = withContext(Dispatchers.IO) {
        val contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId)
        try {
            context.contentResolver.delete(contactUri, null, null)
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


// Activity
//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val contactUri = mutableStateOf<Uri?>(null)
//        val contactPicker = registerForActivityResult(ActivityResultContracts.PickContact()) { uri ->
//            contactUri.value = uri
//        }
//        setContent {
//            TutorialsTheme {
//                TutorialIntentContactScreen(
//                    contactUri = contactUri.value,
//                    onGetContactClick = { contactPicker.launch() }
//                )
//            }
//        }
//    }
//}