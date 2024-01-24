package com.beeroadam.service

import com.beeroadam.dto.UserDTO
import com.beeroadam.dto.toDTO
import com.beeroadam.dto.toEntity
import com.beeroadam.entity.User
import com.beeroadam.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(@Autowired val userRepository: UserRepository) {
    private val logger = LoggerFactory.getLogger(UserService::class.java)

    @Transactional
    fun getUsers(): List<UserDTO> {
        logger.info("Fetching all users")
        return userRepository.findAll().map { it.toDTO().copy(password = "") }
    }

    @Transactional
    fun getUser(id: Long): UserDTO {
        logger.info("Fetching user with id: $id")
        return userRepository.findById(id).map { it.toDTO().copy(password = "") }.orElseThrow { NoSuchElementException("User not found") }
    }

    @Transactional
    fun getUserByUsername(username: String): UserDTO {
        logger.info("Fetching user with username: $username")
        return userRepository.findByUsername(username)?.toDTO()?.copy(password = "") ?: throw NoSuchElementException("User not found")
    }

    @Transactional
    fun createUser(userDTO: UserDTO): UserDTO {
        return userRepository.save(userDTO.toEntity()).toDTO().copy(password = "")
    }

    @Transactional
    fun deleteUser(id: Long) {
        logger.info("Deleting user with id: $id")
        userRepository.deleteById(id)
    }

    @Transactional
    fun updateUser(id: Long, userDTO: UserDTO): UserDTO {
        logger.info("Updating user with id: $id")
        val existingUser = userRepository.findById(id).orElseThrow { NoSuchElementException("User not found") }
        return userRepository.save(userDTO.toEntity(id = existingUser.id)).toDTO().copy(password = "")
    }

}