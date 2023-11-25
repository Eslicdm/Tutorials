package com.eslirodrigues.tutorials.contact_provider_intent.ui.screen

import android.Manifest
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.eslirodrigues.tutorials.contact_provider_intent.ui.components.TutorialIntentContactsPermissionDialog
import com.eslirodrigues.tutorials.contact_provider_intent.ui.viewmodel.TutorialIntentContactViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TutorialIntentContactScreen(
    contactsIntentViewModel: TutorialIntentContactViewModel = hiltViewModel(),
    contactUri: Uri?,
    onGetContactClick: () -> Unit,
) {
    var showReadPermissionDialog by remember { mutableStateOf(false) }
    var showWritePermissionDialog by remember { mutableStateOf(false) }
    val readContactsPermissionState = rememberPermissionState(permission = Manifest.permission.READ_CONTACTS)
    val writeContactsPermissionState = rememberPermissionState(permission = Manifest.permission.WRITE_CONTACTS)
    if (showReadPermissionDialog) {
        TutorialIntentContactsPermissionDialog(
            painterResourceId = R.drawable.ic_phone,
            title = "Read Contacts Permission",
            text = "Read Contacts Permission is Important",
            permissionState = readContactsPermissionState
        )
    }
    if (showWritePermissionDialog) {
        TutorialIntentContactsPermissionDialog(
            painterResourceId = R.drawable.ic_phone,
            title = "Write Contacts Permission",
            text = "Write Contacts Permission is Important",
            permissionState = writeContactsPermissionState
        )
    }

    LaunchedEffect(contactUri) { contactUri?.let { contactsIntentViewModel.getContactByUri(it) } }
    val contact by contactsIntentViewModel.contact.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.Top)
    ) {
        Button(onClick = {
            if (!readContactsPermissionState.status.isGranted) {
                showReadPermissionDialog = true
            } else onGetContactClick()
        }) { Text(text = "Get Contacts") }

        Button(onClick = {
            if (!writeContactsPermissionState.status.isGranted) {
                showWritePermissionDialog = true
            } else contactsIntentViewModel.insertContactIntent(
                name = "Inserted", phone = "777" , email = "inserted@gmail.com"
            )
        }) { Text(text = "Insert Contact") }

        Column {
            Text(text = "Id: ${contact.id}")
            Text(text = "Name: ${contact.name}")
            contact.phones.forEachIndexed { index, number -> Text(text = "Number ${index + 1}: $number") }
            contact.emails.forEachIndexed { index, email -> Text(text = "Email ${index + 1}: $email") }

            Row {
                Button(onClick = {
                    contactsIntentViewModel.editContact(contact.copy(name = contact.name + " Edited"))
                }) { Text(text = "Edit") }

                Button(onClick = {
                    contactsIntentViewModel.deleteContactById(contact.id.toLong())
                }) { Text(text = "Delete") }
            }
        }
    }
}