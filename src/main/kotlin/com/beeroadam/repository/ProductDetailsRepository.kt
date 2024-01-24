package com.beeroadam.repository

import com.beeroadam.entity.ProductDetails
import org.springframework.data.jpa.repository.JpaRepository

interface ProductDetailsRepository: JpaRepository<ProductDetails, Long> {
}