package com.eslirodrigues.tutorials.firebase_firestore.data.model

data class FirestoreDBUser(
    val id: String? = null,
    val name: String? = null,
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name
        )
    }
}