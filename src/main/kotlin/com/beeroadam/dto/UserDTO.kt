package com.beeroadam.dto

import com.beeroadam.entity.Role
import com.beeroadam.entity.User
import com.fasterxml.jackson.annotation.JsonCreator

data class UserDTO @JsonCreator constructor(
    val id: Long? = null,
    val username: String,
    val password: String,
    val roles: Set<String>
)

fun User.toDTO() = UserDTO(id!!, username, password, roles.map { it.name }.toSet())

fun UserDTO.toEntity(id: Long? = this.id) = User(id, username, password, roles.map { Role.valueOf(it) }.toSet())