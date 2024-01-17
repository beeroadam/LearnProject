package com.beeroadam.controller

import com.beeroadam.model.Product
import com.beeroadam.service.ProductService
import io.prometheus.client.CollectorRegistry
import io.prometheus.client.Counter
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/products")
class ProductController(val productService: ProductService, private val collectorRegistry: CollectorRegistry) {
    private val logger = LoggerFactory.getLogger(ProductController::class.java)

    private val productDetailSuccessCounter = Counter.build()
        .name("product_details_successful_requests_total")
        .help("Total number of successful product detail requests")
        .register(collectorRegistry)

    private val productDetailFailCounter = Counter.build()
        .name("product_detail_failed_requests_total")
        .help("Total number of failed product detail requests")
        .register(collectorRegistry)

    private val productsListSuccessCounter = Counter.build()
        .name("products_list_successful_requests_total")
        .help("Total number of successful products list requests")
        .register(collectorRegistry)

    private val productsListFailCounter = Counter.build()
        .name("products_list_failed_requests_total")
        .help("Total number of failed products list requests")
        .register(collectorRegistry)

    @GetMapping("", produces = ["application/json"])
    fun getProducts(): ResponseEntity<List<Product>> {
        logger.info("Handling request to fetch all products")
        val products = try {
            productService.getProducts().also { productsListSuccessCounter.inc() }
        } catch (e: Exception) {
            productsListFailCounter.inc()
            throw e
        }
        return ResponseEntity.ok(products)
    }

    @GetMapping("/{id}", produces = ["application/json"])
    @ResponseBody
    fun getProduct(@PathVariable("id") id: Int): ResponseEntity<Product> {
        logger.info("Handling request to fetch product with id: $id")
        val product = try {
            productService.getProduct(id).also { productDetailSuccessCounter.inc() }
        } catch (e: Exception) {
            productDetailFailCounter.inc()
            throw e
        }
        return ResponseEntity.ok(product)
    }
}