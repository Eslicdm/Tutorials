//package com.eslirodrigues.tutorials.databases.ui.viewmodel
//
//import com.eslirodrigues.tutorials.databases.data.DatabasesUserResult
//import com.eslirodrigues.tutorials.databases.room.RoomUser
//import com.eslirodrigues.tutorials.databases.data.repository.DatabasesUserRepository
//import com.google.common.truth.Truth.assertThat
//import io.mockk.coEvery
//import io.mockk.coVerifyAll
//import io.mockk.impl.annotations.MockK
//import io.mockk.junit4.MockKRule
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runTest
//import kotlinx.coroutines.test.setMain
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class MockkDatabasesUserViewModelTest {
//
//    @get:Rule
//    val mockkRule = MockKRule(this)
//
//    private val dispatcher = StandardTestDispatcher()
//    private lateinit var roomUserViewModel: DatabasesUserViewModel
//    @MockK lateinit var repository: DatabasesUserRepository
//    private val user = RoomUser(id = 1, userName = "Carl")
//
//
//    @Before
//    fun setUp() {
//        Dispatchers.setMain(dispatcher)
//        roomUserViewModel = DatabasesUserViewModel(repository)
//
//        coEvery {
//            repository.addUser(any())
//        } returns flow { emit(DatabasesUserResult.Success("User Added")) }
//
//        coEvery {
//            repository.updateUser(any())
//        } returns flow { emit(DatabasesUserResult.Success("User Added")) }
//
//        coEvery {
//            repository.deleteUser(any())
//        } returns flow { emit(DatabasesUserResult.Success("User Deleted")) }
//    }
//
//    @After
//    fun close() {
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun addUser_Assert_IsUserRight() = runTest {
//        roomUserViewModel.addUser(user)
//
//        coEvery {
//            repository.getAllUsers()
//        } returns flow { emit(DatabasesUserResult.Success(listOf(user))) }
//
//        dispatcher.scheduler.advanceUntilIdle()
//        val value = roomUserViewModel.userState.value.data?.first()
//
//        coVerifyAll {
//            repository.addUser(any())
//            repository.getAllUsers()
//        }
//        assertThat(value).isEqualTo(user)
//    }
//
//    @Test
//    fun addUser_And_UpdateUserName_Assert_IsUpdatedUserNameRight() {
//        roomUserViewModel.addUser(user)
//
//        val updatedUser = RoomUser(id = 1, userName = "Mike")
//        roomUserViewModel.updateUser(updatedUser)
//
//        coEvery {
//            repository.getAllUsers()
//        } returns flow { emit(DatabasesUserResult.Success(listOf(updatedUser))) }
//
//        dispatcher.scheduler.advanceUntilIdle()
//        val value = roomUserViewModel.userState.value.data?.first()
//
//        coVerifyAll {
//            repository.addUser(any())
//            repository.updateUser(any())
//            repository.getAllUsers()
//        }
//        assertThat(value).isEqualTo(updatedUser)
//    }
//
//    @Test
//    fun addUser_And_DeleteUser_Assert_IsUserDeleted() {
//        roomUserViewModel.addUser(user)
//
//        roomUserViewModel.deleteUser(user)
//
//        coEvery {
//            repository.getAllUsers()
//        } returns flow { emit(DatabasesUserResult.Success(listOf())) }
//
//        dispatcher.scheduler.advanceUntilIdle()
//        val value = roomUserViewModel.userState.value.data
//
//        coVerifyAll {
//            repository.addUser(any())
//            repository.deleteUser(any())
//            repository.getAllUsers()
//        }
//        assertThat(value).doesNotContain(user)
//    }
//}