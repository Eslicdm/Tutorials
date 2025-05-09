package com.eslirodrigues.tutorials.databases.data.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.eslirodrigues.tutorials.databases.room.RoomUser
import com.eslirodrigues.tutorials.databases.room.RoomUserDao
import com.eslirodrigues.tutorials.databases.room.RoomUserDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RoomUserDatabaseTest {

    private lateinit var roomUserDao: RoomUserDao
    private lateinit var roomUserDatabase: RoomUserDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        roomUserDatabase = Room.inMemoryDatabaseBuilder(context, RoomUserDatabase::class.java).build()
        roomUserDao = roomUserDatabase.roomUserDao()
    }

    @After
    fun close() {
        roomUserDatabase.close()
    }

    @Test
    fun addUser_Assert_IsUserInList() = runTest {
        val user = RoomUser(id = 1, userName = "Carl")
        roomUserDao.addUser(user)

        val userList = roomUserDao.getAllUsers().first()

        assertThat(userList).contains(user)
    }

    @Test
    fun addUser_And_UpdateUser_Assert_IsUpdatedUserNameRight() = runTest {
        val user = RoomUser(id = 1, userName = "Carl")
        roomUserDao.addUser(user)

        val updatedUser = RoomUser(id = 1, userName = "Mike")
        roomUserDao.updateUser(updatedUser)

        val userList = roomUserDao.getAllUsers().first()
        val updatedUserName = userList.first().userName

        assertThat(updatedUserName).isEqualTo(updatedUser.userName)
    }

    @Test
    fun addUser_And_DeleteUser_Assert_IsUserNotInList() = runTest {
        val user = RoomUser(id = 1, userName = "Carl")
        roomUserDao.addUser(user)

        roomUserDao.deleteUser(user)

        val userList = roomUserDao.getAllUsers().first()

        assertThat(userList).contains(user)
    }
}