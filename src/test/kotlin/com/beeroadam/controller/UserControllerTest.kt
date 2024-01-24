package com.beeroadam.controller

import com.beeroadam.dto.UserDTO
import com.beeroadam.service.UserService
import com.beeroadam.utils.MetricsHelperService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class UserControllerTest {

    private val userService = mockk<UserService>()
    private val metricsHelperService = mockk<MetricsHelperService>(relaxed = true) // relaxed = true to avoid mocking all methods
    private val userController = UserController(userService, metricsHelperService)

    private val user1 = UserDTO(id = 1, username = "testUser1", password = "testPassword1", roles = setOf("USER"))
    private val user2 =
        UserDTO(id = 2, username = "testUser2", password = "testPassword2", roles = setOf("ADMIN", "USER"))

    @Test
    fun `getUsers returns all users`() {
        val expectedUsers = listOf(user1, user2)
        every { userService.getUsers() } returns expectedUsers

        val responseEntity: ResponseEntity<List<UserDTO>> = userController.getUsers()

        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(expectedUsers, responseEntity.body)
    }

    @Test
    fun `getUserById returns the correct user`() {
        every { userService.getUser(1) } returns user1

        val responseEntity: ResponseEntity<UserDTO> = userController.getUser(1)

        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(user1, responseEntity.body)
    }

    @Test
    fun `getUserById throws exception when user not found`() {
        val id = 3L
        every { userService.getUser(id) } throws NoSuchElementException()

        assertThrows<NoSuchElementException> {
            userController.getUser(id)
        }

        verify { userService.getUser(id) }
    }

    @Test
    fun `getUserByUsername returns the correct user`() {
        every { userService.getUserByUsername("testUser1") } returns user1

        val responseEntity: ResponseEntity<UserDTO> = userController.getUserByUsername("testUser1")

        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(user1, responseEntity.body)
    }

    @Test
    fun `getUserByUsername throws exception when user not found`() {
        every { userService.getUserByUsername("nonExistentUsername") } throws NoSuchElementException()

        assertThrows<NoSuchElementException> {
            userController.getUserByUsername("nonExistentUsername")
        }

        verify { userService.getUserByUsername("nonExistentUsername") }
    }

    @Test
    fun `getUsers throws exception when users cannot be fetched`() {
        every { userService.getUsers() } throws Exception()

        assertThrows<Exception> {
            userController.getUsers()
        }

        verify { userService.getUsers() }
    }

    @Test
    fun `createUser creates a new user`() {
        every { userService.createUser(user1) } returns user1

        val responseEntity: ResponseEntity<UserDTO> = userController.createUser(user1)

        assertEquals(HttpStatus.CREATED, responseEntity.statusCode)
        assertEquals(user1, responseEntity.body)
    }

    @Test
    fun `createUser throws exception when user cannot be created`() {
        every { userService.createUser(user1) } throws Exception()

        assertThrows<Exception> {
            userController.createUser(user1)
        }

        verify { userService.createUser(user1) }
    }

    @Test
    fun `deleteUser deletes a user`() {
        val id = 1L
        every { userService.deleteUser(id) } returns Unit

        val responseEntity: ResponseEntity<Unit> = userController.deleteUser(id)

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.statusCode)
    }

    @Test
    fun `deleteUser throws exception when user cannot be deleted`() {
        val id = 1L
        every { userService.deleteUser(id) } throws Exception()

        assertThrows<Exception> {
            userController.deleteUser(id)
        }

        verify { userService.deleteUser(id) }
    }

    @Test
    fun `updateUser updates a user`() {
        every { userService.updateUser(1, user1) } returns user1.copy(username = "updatedUsername")

        val responseEntity: ResponseEntity<UserDTO> = userController.updateUser(1, user1)

        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(user1.copy(username = "updatedUsername"), responseEntity.body)
    }

    @Test
    fun `updateUser throws exception when user cannot be updated`() {
        every { userService.updateUser(1, user1) } throws Exception()

        assertThrows<Exception> {
            userController.updateUser(1, user1)
        }

        verify { userService.updateUser(1, user1) }
    }
}