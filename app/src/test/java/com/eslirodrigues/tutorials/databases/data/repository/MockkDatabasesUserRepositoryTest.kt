//package com.eslirodrigues.tutorials.databases.data.repository
//
//import com.eslirodrigues.tutorials.databases.room.RoomUser
//import com.eslirodrigues.tutorials.databases.room.RoomUserRepository
//import com.eslirodrigues.tutorials.databases.rule.CoroutineDispatcherRule
//import io.mockk.*
//import io.mockk.impl.annotations.RelaxedMockK
//import io.mockk.junit4.MockKRule
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.callbackFlow
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.test.runTest
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class MockkDatabasesUserRepositoryTest {
//
//    @get:Rule
//    val mockkRule = MockKRule(this)
//
//    @get:Rule
//    val coroutineDispatcherRule = CoroutineDispatcherRule()
//
//    @RelaxedMockK lateinit var roomUserRepository: RoomUserRepository
//    lateinit var databasesUserRepository: DatabasesUserRepository
//
//    @Before
//    fun setUp() {
//        databasesUserRepository = DatabasesUserRepositoryImpl(roomUserRepository)
//    }
//
//    @Test
//    fun addUser_And_GetUsers_Verify_AddUser_And_GetAllUsersWereCalled() = runTest {
//        val user = RoomUser(id = 1, userName = "Carl")
//        coEvery {
//            roomUserRepository.addUser(any())
//        } just Runs
//        databasesUserRepository.addUser(user).first()
//
//        coEvery {
//            roomUserRepository.getAllUsers()
//        } returns callbackFlow { trySend(listOf(user)) }
//        databasesUserRepository.getAllUsers().first()
//
//        coVerifyAll {
//            roomUserRepository.addUser(any())
//            roomUserRepository.getAllUsers()
//        }
//
//    }
//
//    @Test
//    fun addUser_And_UpdateUser_Verify_AddUser_And_UpdateUserWereCalled() = runTest {
//        val user = RoomUser(id = 1, userName = "Carl")
//        coEvery {
//            roomUserRepository.addUser(any())
//        } just Runs
//        databasesUserRepository.addUser(user).first()
//
//        val updatedUser = RoomUser(id = 1, userName = "Mike")
//        coEvery {
//            roomUserRepository.updateUser(any())
//        } just Runs
//        databasesUserRepository.updateUser(updatedUser).first()
//
//        coVerifyAll {
//            roomUserRepository.addUser(any())
//            roomUserRepository.updateUser(any())
//        }
//    }
//
//    @Test
//    fun addUser_And_DeleteUser_Verify_AddUser_And_DeleteUserWereCalled() = runTest {
//        val user = RoomUser(id = 1, userName = "Carl")
//        coEvery {
//            roomUserRepository.addUser(any())
//        } just Runs
//        databasesUserRepository.addUser(user).first()
//
//        coEvery {
//            roomUserRepository.deleteUser(any())
//        } just Runs
//        databasesUserRepository.deleteUser(user).first()
//
//        coVerifySequence {
//            roomUserRepository.addUser(any())
//            roomUserRepository.deleteUser(any())
//        }
//    }
//}