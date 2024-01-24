package com.beeroadam.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    val username: String,
    val password: String,
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    @Enumerated(EnumType.STRING)
    val roles: Set<Role>
)

enum class Role {
    USER, ADMIN
}