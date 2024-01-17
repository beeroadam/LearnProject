package com.beeroadam.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProductRepositoryTest {

    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setup() {
        productRepository = ProductRepository()
    }

    @Test
    fun `getProducts returns all products`() {
        assertEquals(5, productRepository.getProducts().size)
    }

    @Test
    fun `getProduct returns correct product when id exists`() {
        assertEquals(1, productRepository.getProduct(1).id)
    }

    @Test
    fun `getProduct throws exception when id does not exist`() {
        assertThrows<NoSuchElementException> {
            productRepository.getProduct(6)
        }
    }
}