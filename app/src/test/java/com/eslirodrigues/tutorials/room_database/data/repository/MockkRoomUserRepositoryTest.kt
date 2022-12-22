package com.eslirodrigues.tutorials.room_database.data.repository

import com.eslirodrigues.tutorials.room_database.data.model.RoomUser
import com.eslirodrigues.tutorials.room_database.data.room.RoomUserDao
import com.eslirodrigues.tutorials.room_database.rule.CoroutineDispatcherRule
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MockkRoomUserRepositoryTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val coroutineDispatcherRule = CoroutineDispatcherRule()

    @RelaxedMockK lateinit var roomUserDao: RoomUserDao
    lateinit var roomUserRepository: RoomUserRepository

    @Before
    fun setUp() {
        roomUserRepository = RoomUserRepositoryImpl(roomUserDao)
    }

    @Test
    fun addUser_And_GetUsers_Verify_AddUser_And_GetAllUsersWereCalled() = runTest {
        val user = RoomUser(id = 1, userName = "Carl")
        coEvery {
            roomUserDao.addUser(any())
        } just Runs
        roomUserRepository.addUser(user).first()

        coEvery {
            roomUserDao.getAllUsers()
        } returns callbackFlow { trySend(listOf(user)) }
        roomUserRepository.getAllUsers().first()

        coVerifyAll {
            roomUserDao.addUser(any())
            roomUserDao.getAllUsers()
        }

    }

    @Test
    fun addUser_And_UpdateUser_Verify_AddUser_And_UpdateUserWereCalled() = runTest {
        val user = RoomUser(id = 1, userName = "Carl")
        coEvery {
            roomUserDao.addUser(any())
        } just Runs
        roomUserRepository.addUser(user).first()

        val updatedUser = RoomUser(id = 1, userName = "Mike")
        coEvery {
            roomUserDao.updateUser(any())
        } just Runs
        roomUserRepository.updateUser(updatedUser).first()

        coVerifyAll {
            roomUserDao.addUser(any())
            roomUserDao.updateUser(any())
        }
    }

    @Test
    fun addUser_And_DeleteUser_Verify_AddUser_And_DeleteUserWereCalled() = runTest {
        val user = RoomUser(id = 1, userName = "Carl")
        coEvery {
            roomUserDao.addUser(any())
        } just Runs
        roomUserRepository.addUser(user).first()

        coEvery {
            roomUserDao.deleteUser(any())
        } just Runs
        roomUserRepository.deleteUser(user).first()

        coVerifySequence {
            roomUserDao.addUser(any())
            roomUserDao.deleteUser(any())
        }
    }
}