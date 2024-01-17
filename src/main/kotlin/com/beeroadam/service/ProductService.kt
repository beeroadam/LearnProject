package com.beeroadam.service

import com.beeroadam.model.Product
import com.beeroadam.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired val productRepository: ProductRepository) {
    private val logger = LoggerFactory.getLogger(ProductService::class.java)

    fun getProducts(): List<Product> {
        logger.info("Fetching all products")
        return productRepository.getProducts()
    }

    fun getProduct(id: Int): Product {
        logger.info("Fetching product with id: $id")
        return productRepository.getProduct(id)
    }
}