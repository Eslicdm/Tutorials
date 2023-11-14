package com.eslirodrigues.tutorials.contacts_provider.contacts

data class TutorialContact(
    val id: String = "",
    val name: String = "",
    val numbers: List<String> = emptyList(),
    val emails: List<String> = emptyList()
)