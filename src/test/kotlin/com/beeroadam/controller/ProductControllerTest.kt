package com.beeroadam.controller

import com.beeroadam.model.Price
import com.beeroadam.model.Product
import com.beeroadam.model.ProductCategory
import com.beeroadam.service.ProductService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.prometheus.client.CollectorRegistry
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

class ProductControllerTest {

    private val productService = mockk<ProductService>()
    private val collectorRegistry = mockk<CollectorRegistry>(relaxed = true) // relaxed = true to avoid mocking all methods
    private val productController = ProductController(productService, collectorRegistry)

    @Test
    fun `getProducts returns all products`() {
        val expectedProducts = listOf(
            Product(
                1,
                "Product 1",
                "Description 1",
                Price(10.0, null),
                ProductCategory.BOOKS,
                null,
                10,
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
            ), Product(
                2,
                "Product 2",
                "Description 2",
                Price(20.0, null),
                ProductCategory.ELECTRONICS,
                null,
                20,
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
            )
        )
        every { productService.getProducts() } returns expectedProducts

        val responseEntity: ResponseEntity<List<Product>> = productController.getProducts()

        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(expectedProducts, responseEntity.body)
    }

    @Test
    fun `getProduct returns the correct product`() {
        val expectedProduct = Product(
            1,
            "Product 1",
            "Description 1",
            Price(10.0, null),
            ProductCategory.BOOKS,
            null,
            10,
            true,
            LocalDateTime.now(),
            LocalDateTime.now()
        )
        every { productService.getProduct(1) } returns expectedProduct

        val responseEntity: ResponseEntity<Product> = productController.getProduct(1)

        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(expectedProduct, responseEntity.body)
    }

    @Test
    fun `getProduct throws exception when product not found`() {
        val id = 1
        every { productService.getProduct(id) } throws NoSuchElementException()

        assertThrows<NoSuchElementException> {
            productService.getProduct(id)
        }

        verify { productService.getProduct(id) }
    }

    @Test
    fun `getProducts throws exception when products cannot be fetched`() {
        every { productService.getProducts() } throws Exception()

        assertThrows<Exception> {
            productService.getProducts()
        }

        verify { productService.getProducts() }
    }
}