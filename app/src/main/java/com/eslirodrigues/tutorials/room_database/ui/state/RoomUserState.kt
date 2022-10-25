package com.eslirodrigues.tutorials.room_database.ui.state

import com.eslirodrigues.tutorials.room_database.data.model.RoomUser

data class RoomUserState(
    val data: List<RoomUser>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)
