package com.beeroadam.controller

import com.beeroadam.dto.UserDTO
import com.beeroadam.service.UserService
import com.beeroadam.utils.MetricsHelperService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.net.HttpURLConnection


@Controller
@RequestMapping("/users")
class UserController(val userService: UserService, val metricsHelperService: MetricsHelperService) {
    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @GetMapping("", produces = ["application/json"])
    @ResponseBody
    fun getUsers(): ResponseEntity<List<UserDTO>> {
        logger.info("Handling request to fetch all users")
        val users = try {
            userService.getUsers().also { metricsHelperService.incrementCounter(MetricsHelperService.USER_LIST_FAIL) }
        } catch (e: Exception) {
            metricsHelperService.incrementCounter(MetricsHelperService.USER_LIST_FAIL)
            throw e
        }
        return ResponseEntity.ok(users)
    }

    @GetMapping("id/{id}", produces = ["application/json"])
    @ResponseBody
    fun getUser(@PathVariable id: Long): ResponseEntity<UserDTO> {
        logger.info("Handling request to fetch user with id: $id")
        val user = try {
            userService.getUser(id)
                .also { metricsHelperService.incrementCounter(MetricsHelperService.USER_DETAIL_SUCCESS) }
        } catch (e: Exception) {
            metricsHelperService.incrementCounter(MetricsHelperService.USER_DETAIL_FAIL)
            throw e
        }
        return ResponseEntity.ok(user)
    }

    @GetMapping("/username/{username}", produces = ["application/json"])
    @ResponseBody
    fun getUserByUsername(@PathVariable username: String): ResponseEntity<UserDTO> {
        logger.info("Handling request to fetch product with username: $username")
        val user = try {
            userService.getUserByUsername(username)
                .also { metricsHelperService.incrementCounter(MetricsHelperService.USER_DETAIL_SUCCESS) }
        } catch (e: Exception) {
            metricsHelperService.incrementCounter(MetricsHelperService.USER_DETAIL_FAIL)
            throw e
        }
        return ResponseEntity.ok(user)
    }

    @PostMapping("/create", produces = ["application/json"], consumes = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun createUser(@RequestBody userDTO: UserDTO): ResponseEntity<UserDTO> {
        logger.info("Handling request to create user")
        val createdUser = try {
            userService.createUser(userDTO)
                .also { metricsHelperService.incrementCounter(MetricsHelperService.USER_CREATE_SUCCESS) }
        } catch (e: Exception) {
            metricsHelperService.incrementCounter(MetricsHelperService.USER_CREATE_FAIL)
            throw e
        }
        return ResponseEntity.status(HttpURLConnection.HTTP_CREATED).body(createdUser)
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        logger.info("Handling request to delete user with id: $id")
        try {
            userService.deleteUser(id)
                .also { metricsHelperService.incrementCounter(MetricsHelperService.USER_DELETE_SUCCESS) }
        } catch (e: Exception) {
            metricsHelperService.incrementCounter(MetricsHelperService.USER_DELETE_FAIL)
            throw e
        }
        return ResponseEntity.noContent().build()
    }

    @PutMapping("update/{id}", produces = ["application/json"], consumes = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun updateUser(@PathVariable id: Long, @RequestBody userDTO: UserDTO): ResponseEntity<UserDTO> {
        logger.info("Handling request to update user with id: $id")
        val updatedUser = try {
            userService.updateUser(id, userDTO).also {
                metricsHelperService.incrementCounter(
                    MetricsHelperService.USER_UPDATE_SUCCESS
                )
            }
        } catch (e: Exception) {
            metricsHelperService.incrementCounter(MetricsHelperService.USER_UPDATE_FAIL)
            throw e
        }
        return ResponseEntity.ok(updatedUser)
    }
}