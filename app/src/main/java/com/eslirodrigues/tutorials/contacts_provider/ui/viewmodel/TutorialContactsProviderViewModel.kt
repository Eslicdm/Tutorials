package com.eslirodrigues.tutorials.contacts_provider.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.contacts_provider.contacts.TutorialContact
import com.eslirodrigues.tutorials.contacts_provider.contacts.TutorialContactsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorialContactsProviderViewModel @Inject constructor(
    private val contactsProvider: TutorialContactsProvider
) : ViewModel() {

    private val _contactsList = MutableStateFlow(emptyList<TutorialContact>())
    val contactsList: StateFlow<List<TutorialContact>> = _contactsList.asStateFlow()

    fun getContacts() = viewModelScope.launch {
        _contactsList.update { contactsProvider.getContacts() }
    }

    fun insertContact(name: String, phone: String, email: String) = viewModelScope.launch {
        contactsProvider.insertContact(name, phone, email)
        getContacts()
    }

    fun updateContact(
        contactId: String, name: String, phones: List<String>, emails: List<String>
    ) = viewModelScope.launch {
        contactsProvider.updateContact(contactId, name, phones, emails)
        getContacts()
    }

    fun deleteContactById(contactId: String) = viewModelScope.launch {
        contactsProvider.deleteContactById(contactId)
        getContacts()
    }
}