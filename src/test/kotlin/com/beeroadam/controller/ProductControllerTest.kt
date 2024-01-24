package com.beeroadam.controller

import com.beeroadam.dto.ProductDTO
import com.beeroadam.service.ProductService
import com.beeroadam.utils.MetricsHelperService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.prometheus.client.CollectorRegistry
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ProductControllerTest {

    private val productService = mockk<ProductService>()
    private val metricsHelperService = mockk<MetricsHelperService>(relaxed = true) // relaxed = true to avoid mocking all methods
    private val productController = ProductController(productService, metricsHelperService)


    @Test
    fun `getProducts returns all products`() {
        val expectedProducts = listOf(
            ProductDTO(1, "Product 1", "BOOKS", mutableListOf()),
            ProductDTO(2, "Product 2", "ELECTRONICS", mutableListOf())
        )
        every { productService.getProducts() } returns expectedProducts

        val responseEntity: ResponseEntity<List<ProductDTO>> = productController.getProducts()

        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(expectedProducts, responseEntity.body)
    }

    @Test
    fun `getProduct returns the correct product`() {
        val expectedProduct = ProductDTO(1, "Product 1", "BOOKS", mutableListOf())
        every { productService.getProduct(1) } returns expectedProduct

        val responseEntity: ResponseEntity<ProductDTO> = productController.getProduct(1)

        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(expectedProduct, responseEntity.body)
    }

    @Test
    fun `getProduct throws exception when product not found`() {
        val id = 1L
        every { productService.getProduct(id) } throws NoSuchElementException()

        assertThrows<NoSuchElementException> {
            productController.getProduct(id)
        }

        verify { productService.getProduct(id) }
    }

    @Test
    fun `getProducts throws exception when products cannot be fetched`() {
        every { productService.getProducts() } throws Exception()

        assertThrows<Exception> {
            productController.getProducts()
        }

        verify { productService.getProducts() }
    }

    @Test
    fun `createProduct creates a new product`() {
        val productDTO = ProductDTO(1, "Product 1", "BOOKS", mutableListOf())
        every { productService.createProduct(productDTO) } returns productDTO

        val responseEntity: ResponseEntity<ProductDTO> = productController.createProduct(productDTO)

        assertEquals(HttpStatus.CREATED, responseEntity.statusCode)
        assertEquals(productDTO, responseEntity.body)
    }

    @Test
    fun `createProduct throws exception when product cannot be created`() {
        val productDTO = ProductDTO(1, "Product 1", "BOOKS", mutableListOf())
        every { productService.createProduct(productDTO) } throws Exception()

        assertThrows<Exception> {
            productController.createProduct(productDTO)
        }

        verify { productService.createProduct(productDTO) }
    }

    @Test
    fun `deleteProduct deletes a product`() {
        val id = 1L
        every { productService.deleteProduct(id) } returns Unit

        val responseEntity: ResponseEntity<Unit> = productController.deleteProduct(id)

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.statusCode)
    }

    @Test
    fun `deleteProduct throws exception when product cannot be deleted`() {
        val id = 1L
        every { productService.deleteProduct(id) } throws Exception()

        assertThrows<Exception> {
            productController.deleteProduct(id)
        }

        verify { productService.deleteProduct(id) }
    }

    @Test
    fun `updateProduct updates a product`() {
        val productDTO = ProductDTO(1, "Product 1", "BOOKS", mutableListOf())
        every { productService.updateProduct(1, productDTO) } returns productDTO

        val responseEntity: ResponseEntity<ProductDTO> = productController.updateProduct(1, productDTO)

        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(productDTO, responseEntity.body)
    }

    @Test
    fun `updateProduct throws exception when product cannot be updated`() {
        val productDTO = ProductDTO(1, "Product 1", "BOOKS", mutableListOf())
        every { productService.updateProduct(1, productDTO) } throws Exception()

        assertThrows<Exception> {
            productController.updateProduct(1, productDTO)
        }

        verify { productService.updateProduct(1, productDTO) }
    }
}