package com.eslirodrigues.tutorials.contacts_provider.contact_provider_intent.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.contacts_provider.contact_provider_intent.contacts.TutorialIntentContact
import com.eslirodrigues.tutorials.contacts_provider.contact_provider_intent.contacts.TutorialIntentContactProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorialIntentContactViewModel @Inject constructor(
    private val tutorialContactIntent: TutorialIntentContactProvider
) : ViewModel() {

    private val _contact = MutableStateFlow(TutorialIntentContact())
    val contact: StateFlow<TutorialIntentContact> = _contact.asStateFlow()

    fun getContactByUri(contactUri: Uri) = viewModelScope.launch {
        tutorialContactIntent.getContactByUri(contactUri)?.let { contact ->
            _contact.update { contact }
        }
    }

    fun insertContactIntent(name: String, phone: String, email: String) = viewModelScope.launch {
        tutorialContactIntent.insertContactIntent(name, phone, email)
    }

    fun editContact(contact: TutorialIntentContact) = viewModelScope.launch {
        tutorialContactIntent.editContact(contact)
    }

    fun deleteContactById(contactId: Long) = viewModelScope.launch {
        tutorialContactIntent.deleteContactById(contactId)
    }
}