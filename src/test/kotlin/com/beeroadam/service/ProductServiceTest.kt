package com.beeroadam.service

import com.beeroadam.model.Price
import com.beeroadam.model.Product
import com.beeroadam.model.ProductCategory
import com.beeroadam.repository.ProductRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class ProductServiceTest {

    private val productRepository = mockk<ProductRepository>()
    private val productService = ProductService(productRepository)
    private val product1 = Product(
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
    private val product2 = Product(
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

    @Test
    fun `getProducts returns all products`() {
        val expectedProducts = listOf(
            product1, product2
        )
        every { productRepository.getProducts() } returns expectedProducts

        val actualProducts = productService.getProducts()

        assertEquals(expectedProducts, actualProducts)
        verify { productRepository.getProducts() }
    }

    @Test
    fun `getProduct returns the correct product`() {
        val expectedProduct = product1
        val id = 1
        every { productRepository.getProduct(id) } returns expectedProduct

        val actualProduct = productService.getProduct(id)

        assertEquals(expectedProduct, actualProduct)
        verify { productRepository.getProduct(id) }
    }

    @Test
    fun `getProduct with non-existent id throws Exception`() {
        val id = 3
        every { productRepository.getProduct(id) } throws NoSuchElementException()

        assertThrows<NoSuchElementException> {
            productService.getProduct(id)
        }
        verify { productRepository.getProduct(id) }
    }
}