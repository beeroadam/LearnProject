package com.beeroadam.controller

import com.beeroadam.dto.ProductDTO
import com.beeroadam.service.ProductService
import com.beeroadam.utils.MetricsHelperService
import com.beeroadam.utils.MetricsHelperService.Companion.PRODUCTS_LIST_FAIL
import com.beeroadam.utils.MetricsHelperService.Companion.PRODUCT_CREATE_FAIL
import com.beeroadam.utils.MetricsHelperService.Companion.PRODUCT_CREATE_SUCCESS
import com.beeroadam.utils.MetricsHelperService.Companion.PRODUCT_DELETE_FAIL
import com.beeroadam.utils.MetricsHelperService.Companion.PRODUCT_DELETE_SUCCESS
import com.beeroadam.utils.MetricsHelperService.Companion.PRODUCT_DETAILS_FAIL
import com.beeroadam.utils.MetricsHelperService.Companion.PRODUCT_DETAILS_SUCCESS
import com.beeroadam.utils.MetricsHelperService.Companion.PRODUCT_UPDATE_FAIL
import com.beeroadam.utils.MetricsHelperService.Companion.PRODUCT_UPDATE_SUCCESS
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.net.HttpURLConnection.HTTP_CREATED

@Controller
@RequestMapping("/products")
class ProductController(val productService: ProductService, val metricsHelperService: MetricsHelperService) {
    private val logger = LoggerFactory.getLogger(ProductController::class.java)

    @GetMapping("", produces = ["application/json"])
    fun getProducts(): ResponseEntity<List<ProductDTO>> {
        logger.info("Handling request to fetch all products")
        val products = try {
            productService.getProducts().also { metricsHelperService.incrementCounter(PRODUCTS_LIST_FAIL) }
        } catch (e: Exception) {
            metricsHelperService.incrementCounter(PRODUCTS_LIST_FAIL)
            throw e
        }
        return ResponseEntity.ok(products)
    }

    @GetMapping("/{id}", produces = ["application/json"])
    @ResponseBody
    fun getProduct(@PathVariable("id") id: Long): ResponseEntity<ProductDTO> {
        logger.info("Handling request to fetch product with id: $id")
        val product = try {
            productService.getProduct(id).also { metricsHelperService.incrementCounter(PRODUCT_DETAILS_SUCCESS) }
        } catch (e: Exception) {
            metricsHelperService.incrementCounter(PRODUCT_DETAILS_FAIL)
            throw e
        }
        return ResponseEntity.ok(product)
    }

    @PostMapping("/create", produces = ["application/json"], consumes = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun createProduct(@RequestBody productDTO: ProductDTO): ResponseEntity<ProductDTO> {
        logger.info("Handling request to create product")
        val createdProduct = try {
            productService.createProduct(productDTO)
                .also { metricsHelperService.incrementCounter(PRODUCT_CREATE_SUCCESS) }
        } catch (e: Exception) {
            metricsHelperService.incrementCounter(PRODUCT_CREATE_FAIL)
            throw e
        }
        return ResponseEntity.status(HTTP_CREATED).body(createdProduct)
    }

    @DeleteMapping("delete/{id}", produces = ["application/json"])
    @ResponseBody
    fun deleteProduct(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        logger.info("Handling request to delete product with id: $id")
        try {
            productService.deleteProduct(id).also { metricsHelperService.incrementCounter(PRODUCT_DELETE_SUCCESS) }
        } catch (e: Exception) {
            metricsHelperService.incrementCounter(PRODUCT_DELETE_FAIL)
            throw e
        }
        return ResponseEntity.noContent().build()
    }

    @PutMapping("update/{id}", produces = ["application/json"], consumes = ["application/json;charset=UTF-8"])
    @ResponseBody
    fun updateProduct(@PathVariable("id") id: Long, @RequestBody productDTO: ProductDTO): ResponseEntity<ProductDTO> {
        logger.info("Handling request to update product with id: $id")
        val updatedProduct = try {
            productService.updateProduct(id, productDTO).also {
                metricsHelperService.incrementCounter(
                    PRODUCT_UPDATE_SUCCESS
                )
            }
        } catch (e: Exception) {
            metricsHelperService.incrementCounter(PRODUCT_UPDATE_FAIL)
            throw e
        }
        return ResponseEntity.ok(updatedProduct)
    }
}