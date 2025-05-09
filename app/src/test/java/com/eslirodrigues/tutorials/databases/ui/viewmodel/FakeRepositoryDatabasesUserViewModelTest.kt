//package com.eslirodrigues.tutorials.databases.ui.viewmodel
//
//import com.eslirodrigues.tutorials.databases.room.RoomUser
//import com.eslirodrigues.tutorials.databases.data.repository.FakeRoomUserRepository
//import com.google.common.truth.Truth.assertThat
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.setMain
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class FakeRepositoryDatabasesUserViewModelTest {
//
//    private lateinit var roomUserViewModel: DatabasesUserViewModel
//    private lateinit var fakeRoomUserRepository: FakeRoomUserRepository
//
//    private val dispatcher = StandardTestDispatcher()
//
//    @Before
//    fun setUp() {
//        Dispatchers.setMain(dispatcher)
//        fakeRoomUserRepository = FakeRoomUserRepository()
//        roomUserViewModel = DatabasesUserViewModel(fakeRoomUserRepository)
//    }
//
//    @After
//    fun close() {
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun addUser_Assert_IsUserRight() {
//        val user = RoomUser(id = 1, userName = "Carl")
//        roomUserViewModel.addUser(user)
//
//        dispatcher.scheduler.advanceUntilIdle()
//        val value = roomUserViewModel.userState.value.data?.first()
//
//        assertThat(value).isEqualTo(user)
//    }
//
//    @Test
//    fun addUser_And_UpdateUserName_Assert_IsUpdatedUserNameRight() {
//        val user = RoomUser(id = 1, userName = "Carl")
//        roomUserViewModel.addUser(user)
//
//        val updatedUser = RoomUser(id = 1, userName = "Mike")
//        roomUserViewModel.updateUser(updatedUser)
//
//        dispatcher.scheduler.advanceUntilIdle()
//        val value = roomUserViewModel.userState.value.data?.first()
//
//        assertThat(value).isEqualTo(updatedUser)
//    }
//
//    @Test
//    fun addUser_And_DeleteUser_Assert_IsUserDeleted() {
//        val user = RoomUser(id = 1, userName = "Carl")
//        roomUserViewModel.addUser(user)
//
//        roomUserViewModel.deleteUser(user)
//
//        dispatcher.scheduler.advanceUntilIdle()
//        val value = roomUserViewModel.userState.value.data
//
//        assertThat(value).doesNotContain(user)
//    }
//}