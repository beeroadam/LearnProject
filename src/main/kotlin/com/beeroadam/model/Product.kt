package com.beeroadam.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDateTime

data class Product(
    val id: Int,
    val name: String,
    val description: String?,
    val price: Price,
    val category: ProductCategory,
    val imageUrl: String?,
    val quantity: Int,
    val isActive: Boolean = true,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) : Serializable

enum class ProductCategory {
    ELECTRONICS,
    CLOTHING,
    GROCERY,
    BOOKS,
    HOME_APPLIANCES
}

data class Price(
    val leadPrice: Double,
    val strikeOutPrice: Double?
)
