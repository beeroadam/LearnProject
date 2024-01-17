package com.beeroadam.repository

import com.beeroadam.model.Price
import com.beeroadam.model.Product
import com.beeroadam.model.ProductCategory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

@Repository
class ProductRepository {

    private val products = mutableListOf<Product>()
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getProducts(): List<Product> {
        logger.info("Fetching all products")
        return products
    }

    fun getProduct(id: Int): Product {
        logger.info("Fetching product with id: $id")
        try {
            return products.first { it.id == id }
        } catch (e: NoSuchElementException) {
            logger.error("Product with id $id not found")
            throw e
        }
    }

    /*
    * Instead of @PostConstruct, you can also use ‹init› block, if no dependencies are required,
    * since those are not available in init block
    */
    init {
        logger.info("Creating dummy products")
        repeat(5) {
            val id = it + 1
            val name = "Dummy Product $id"
            val category = ProductCategory.entries.random()
            val price = BigDecimal(Random.nextDouble(50.0, 500.0)).setScale(2, RoundingMode.HALF_EVEN).toDouble()
            val quantity = Random.nextInt(1, 100)

            products.add(
                Product(
                    id = id,
                    name = name,
                    description = "This is a $name",
                    price = Price(leadPrice = price, strikeOutPrice = null),
                    category = category,
                    imageUrl = null,
                    quantity = quantity
                )
            )
        }
    }
}