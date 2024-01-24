package com.beeroadam.dto

import com.beeroadam.entity.Price
import com.beeroadam.entity.Product
import com.beeroadam.entity.ProductDetails
import com.fasterxml.jackson.annotation.JsonCreator
import java.time.LocalDateTime

data class ProductDetailsDTO @JsonCreator constructor(
    val id: Long? = null,
    val description: String?,
    val price: Price,
    val quantity: Int,
    val attributes: List<AttributeDTO>,
    val imageUrl: String?,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

fun ProductDetails.toDTO() = ProductDetailsDTO(
    id!!,
    description,
    price,
    quantity,
    attributes.map { it.toDTO() },
    imageUrl,
    isActive,
    createdAt,
    updatedAt,
)

fun ProductDetailsDTO.toEntity(id: Long? = this.id, product: Product) = ProductDetails(
    id,
    description,
    price,
    quantity,
    mutableListOf(),
    imageUrl,
    isActive,
    createdAt,
    updatedAt,
    product
)
