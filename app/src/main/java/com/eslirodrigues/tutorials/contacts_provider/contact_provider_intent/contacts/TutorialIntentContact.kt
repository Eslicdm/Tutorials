package com.eslirodrigues.tutorials.contacts_provider.contact_provider_intent.contacts

data class TutorialIntentContact(
    val id: String = "",
    val name: String = "",
    val phones: List<String> = emptyList(),
    val emails: List<String> = emptyList()
)
