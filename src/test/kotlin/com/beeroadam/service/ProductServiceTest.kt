package com.beeroadam.service

import com.beeroadam.dto.toDTO
import com.beeroadam.dto.toEntity
import com.beeroadam.entity.*
import com.beeroadam.repository.ProductRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*

class ProductServiceTest {

    private val productRepository = mockk<ProductRepository>()
    private val productService = ProductService(productRepository)

    @Test
    fun `getProducts returns all products`() {
        val expectedProducts = listOf(
            product1, product2
        )
        every { productRepository.findAll() } returns expectedProducts

        val actualProducts = productService.getProducts()

        assertEquals(expectedProducts.map { it.toDTO() }, actualProducts)
        verify { productRepository.findAll() }
    }

    @Test
    fun `getProduct returns the correct product`() {
        val expectedProduct = product1
        val id = 1L
        every { productRepository.findById(id) } returns Optional.of(expectedProduct)

        val actualProduct = productService.getProduct(id)

        assertEquals(expectedProduct.toDTO(), actualProduct)
        verify { productRepository.findById(id) }
    }

    @Test
    fun `getProduct with non-existent id throws Exception`() {
        val id = 3L
        every { productRepository.findById(id) } returns Optional.empty()

        assertThrows<NoSuchElementException> {
            productService.getProduct(id)
        }
        verify { productRepository.findById(id) }
    }

    @Test
    fun `createProduct creates a new product`() {
        val productDTO = product1.toDTO()
        val product = productDTO.toEntity(1)
        every { productRepository.save(any()) } returns product

        val actualProduct = productService.createProduct(productDTO)

        assertEquals(product.toDTO(), actualProduct)
        verify { productRepository.save(any()) }
    }

    @Test
    fun `deleteProduct deletes the specified product`() {
        val id = 1L
        every { productRepository.deleteById(id) } just Runs

        productService.deleteProduct(id)

        verify { productRepository.deleteById(id) }
    }

    @Test
    fun `deleteProduct with non-existent id does not throw Exception`() {
        val id = 3L
        every { productRepository.deleteById(id) } just Runs

        assertDoesNotThrow {
            productService.deleteProduct(id)
        }
        verify { productRepository.deleteById(id) }
    }

    @Test
    fun `updateProduct updates the specified product`() {
        val id = 1L
        val productDTO = product1.toDTO()
        val existingProduct = product2
        val updatedProduct = productDTO.toEntity(existingProduct.id)
        every { productRepository.findById(id) } returns Optional.of(existingProduct)
        every { productRepository.save(any()) } returns updatedProduct

        val actualProduct = productService.updateProduct(id, productDTO)

        assertEquals(updatedProduct.toDTO(), actualProduct)
        verify { productRepository.findById(id) }
        verify { productRepository.save(any()) }
    }

    @Test
    fun `updateProduct with non-existent id throws Exception`() {
        val id = 3L
        val productDTO = product1.toDTO()
        every { productRepository.findById(id) } returns Optional.empty()

        assertThrows<NoSuchElementException> {
            productService.updateProduct(id, productDTO)
        }
        verify { productRepository.findById(id) }
    }

    companion object {
        private val product1 = Product(
            1, "Product 1", category = ProductCategory.BOOKS, mutableListOf()
        )
        private val product2 = Product(
            2, "Product 2", category = ProductCategory.ELECTRONICS, mutableListOf()
        )

        private val productDetails1 = ProductDetails(
            1,
            "Description 1",
            Price(10.0, null),
            10,
            mutableListOf(),
            null,
            true,
            LocalDateTime.now(),
            LocalDateTime.now(),
            product1
        )

        private val productDetails2 = ProductDetails(
            2,
            "Description 2",
            Price(20.0, null),
            20,
            mutableListOf(),
            null,
            true,
            LocalDateTime.now(),
            LocalDateTime.now(),
            product2
        )

        private val attribute1 = Attribute(
            1,
            "Attribute 1",
            "Attribute 1 Value",
            productDetails1
        )

        private val attribute2 = Attribute(
            2,
            "Attribute 1",
            "Attribute 1 Value",
            productDetails1
        )

        private val attribute3 = Attribute(
            1,
            "Attribute 2",
            "Attribute 2 Value",
            productDetails2
        )
        @JvmStatic
        @BeforeAll
        fun setup(): Unit {
            (productDetails1.attributes as MutableList).addAll(listOf(attribute1, attribute2))
            (productDetails2.attributes as MutableList).add(attribute3)
            (product1.productDetails as MutableList).add(productDetails1)
            (product2.productDetails as MutableList).add(productDetails2)
        }
    }
}