package com.beeroadam.dto

import com.beeroadam.entity.Attribute
import com.beeroadam.entity.ProductDetails
import com.fasterxml.jackson.annotation.JsonCreator

data class AttributeDTO @JsonCreator constructor(
    val id: Long? = null, val name: String, val value: String
)

fun AttributeDTO.toEntity(id: Long? = this.id, productDetails: ProductDetails) = Attribute(id, name, value, productDetails)

fun Attribute.toDTO() = AttributeDTO(id!!, name, value)