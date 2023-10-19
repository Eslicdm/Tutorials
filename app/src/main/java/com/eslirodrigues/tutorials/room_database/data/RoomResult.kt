package com.eslirodrigues.tutorials.room_database.data

sealed class RoomResult<out R> {
    data class Success<out T>(val data: T): RoomResult<T>()
    data class Error(val exception: Throwable): RoomResult<Nothing>()
    data object Loading: RoomResult<Nothing>()
}