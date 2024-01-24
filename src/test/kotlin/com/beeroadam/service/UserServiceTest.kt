package com.beeroadam.service

import com.beeroadam.dto.UserDTO
import com.beeroadam.dto.toDTO
import com.beeroadam.dto.toEntity
import com.beeroadam.entity.Role
import com.beeroadam.entity.User
import com.beeroadam.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class UserServiceTest {
    private lateinit var userRepository: UserRepository
    private lateinit var userService: UserService

    private val user1 = User(id = 1, username = "user1", password = "password1", roles = setOf(Role.USER))
    private val user2 = User(id = 2, username = "user2", password = "password2", roles = setOf(Role.USER, Role.ADMIN))

    @BeforeEach
    fun setup() {
        userRepository = mockk()
        userService = UserService(userRepository)
    }

    @Test
    fun `getUsers returns all users`() {
        val users = listOf(user1, user2)
        every { userRepository.findAll() } returns users

        val result = userService.getUsers()

        assertEquals(users.size, result.size)
        verify { userRepository.findAll() }
    }

    @Test
    fun `getUser returns user by id`() {
        every { userRepository.findById(1) } returns Optional.of(user1)

        val result = userService.getUser(1)

        assertEquals(user1.toDTO().copy(password = ""), result)
        verify { userRepository.findById(1) }
    }

    @Test
    fun `getUser throws exception when user not found`() {
        every { userRepository.findById(1) } returns Optional.empty()

        assertThrows<NoSuchElementException> {
            userService.getUser(1)
        }
        verify { userRepository.findById(1) }
    }

    @Test
    fun `getUserByUsername returns user by username`() {
        every { userRepository.findByUsername("username1") } returns user1

        val result = userService.getUserByUsername("username1")

        assertEquals(user1.toDTO().copy(password = ""), result)
        verify { userRepository.findByUsername("username1") }
    }

    @Test
    fun `getUserByUsername throws exception when user not found`() {
        every { userRepository.findByUsername("username") } returns null

        assertThrows<NoSuchElementException> {
            userService.getUserByUsername("username")
        }
        verify { userRepository.findByUsername("username") }
    }

    @Test
    fun `createUser creates a new user`() {
        val user = UserDTO(id = 3, username = "user3", password = "password3", roles = setOf("USER"))
        every { userRepository.save(any()) } returns user.toEntity()

        val result = userService.createUser(user)

        assertEquals(user.copy(password = ""), result)
        verify { userRepository.save(user.toEntity()) }
    }

    @Test
    fun `deleteUser deletes user by id`() {
        every { userRepository.deleteById(1) } returns Unit

        userService.deleteUser(1)

        verify { userRepository.deleteById(1) }
    }

    @Test
    fun `updateUser updates user by id`() {
        val user = user1.copy(username = "userUpdated", password = "passwordUpdated")
        every { userRepository.findById(1) } returns Optional.of(user)
        every { userRepository.save(any()) } returns user

        val result = userService.updateUser(1, user.toDTO())

        assertEquals(user.toDTO().copy(password = ""), result)
        verify { userRepository.findById(1) }
        verify { userRepository.save(user) }
    }

    @Test
    fun `updateUser throws exception when user not found`() {
        val user = user1.copy(id = 3).toDTO()
        every { userRepository.findById(3) } returns Optional.empty()

        assertThrows<NoSuchElementException> {
            userService.updateUser(3, user)
        }
        verify { userRepository.findById(3) }
    }
}