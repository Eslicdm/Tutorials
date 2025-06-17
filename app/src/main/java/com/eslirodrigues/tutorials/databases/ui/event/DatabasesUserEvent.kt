package com.eslirodrigues.tutorials.databases.ui.event

import com.eslirodrigues.tutorials.databases.data.model.DatabasesUser

sealed class DatabasesUserEvent {
    data class AddUser(val user: DatabasesUser) : DatabasesUserEvent()
    data class UpdateUser(val user: DatabasesUser) : DatabasesUserEvent()
    data class DeleteUser(val user: DatabasesUser) : DatabasesUserEvent()
}