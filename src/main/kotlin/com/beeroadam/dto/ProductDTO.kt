package com.beeroadam.dto

import com.beeroadam.entity.Product
import com.beeroadam.entity.ProductCategory
import com.fasterxml.jackson.annotation.JsonCreator

data class ProductDTO @JsonCreator constructor(
    val id: Long? = null, val name: String, val category: String, val productDetails: List<ProductDetailsDTO>
)

fun Product.toDTO() = ProductDTO(
    id!!,
    name,
    category.name,
    productDetails.map { it.toDTO() }
)

fun ProductDTO.toEntity(id: Long? = this.id) = Product(id, name, ProductCategory.valueOf(category), mutableListOf())
