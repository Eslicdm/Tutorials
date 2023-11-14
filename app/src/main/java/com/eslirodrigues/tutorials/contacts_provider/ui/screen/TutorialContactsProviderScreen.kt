package com.eslirodrigues.tutorials.contacts_provider.ui.screen

import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.WRITE_CONTACTS
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eslirodrigues.tutorials.R
import com.eslirodrigues.tutorials.contacts_provider.ui.components.TutorialReadContactsPermissionDialog
import com.eslirodrigues.tutorials.contacts_provider.ui.viewmodel.TutorialContactsProviderViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState



@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TutorialContactsProviderScreen(
    contactsProviderViewModel: TutorialContactsProviderViewModel = hiltViewModel()
) {
    var showReadPermissionDialog by remember { mutableStateOf(false) }
    var showWritePermissionDialog by remember { mutableStateOf(false) }
    val readContactsPermissionState = rememberPermissionState(permission = READ_CONTACTS)
    val writeContactsPermissionState = rememberPermissionState(permission = WRITE_CONTACTS)
    if (showReadPermissionDialog) {
        TutorialReadContactsPermissionDialog(
            painterResourceId = R.drawable.ic_phone,
            title = "Read Contacts Permission",
            text = "Read Contacts Permission is Important",
            permissionState = readContactsPermissionState
        )
    }
    if (showWritePermissionDialog) {
        TutorialReadContactsPermissionDialog(
            painterResourceId = R.drawable.ic_phone,
            title = "Write Contacts Permission",
            text = "Write Contacts Permission is Important",
            permissionState = writeContactsPermissionState
        )
    }

    val contactsList by contactsProviderViewModel.contactsList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.Top)
    ) {
        Button(onClick = {
            if (!readContactsPermissionState.status.isGranted) { showReadPermissionDialog = true }
            else contactsProviderViewModel.getContacts()
        }) { Text(text = "Get Contacts") }

        Button(onClick = {
            if (!writeContactsPermissionState.status.isGranted) { showWritePermissionDialog = true }
            else contactsProviderViewModel.insertContact(
                "Inserted", "777", "inserted@gmail.com"
            )
        }) { Text(text = "Insert Contact") }

        LazyColumn {
            items(contactsList) { contact ->
                    Column {
                        Text(text = "Id: ${contact.id}")
                        Text(text = "Name: ${contact.name}")
                        contact.numbers.forEachIndexed { index, number ->
                            Text(text = "Number ${index + 1}: $number")
                        }
                        contact.emails.forEachIndexed { index, email ->
                            Text(text = "Email ${index + 1}: $email")
                        }
                        Row {
                            Button(onClick = {
                                contactsProviderViewModel.deleteContactById(contact.id)
                            }) { Text(text = "Delete") }
                            Button(onClick = {
                                contactsProviderViewModel.updateContact(
                                    contactId = contact.id,
                                    name = contact.name + " Edited",
                                    phones = contact.numbers,
                                    emails = contact.emails
                                )
                            }) { Text(text = "Update") }
                        }
                    }
                HorizontalDivider(Modifier.padding(20.dp), 3.dp)
            }
        }
    }
}