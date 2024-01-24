package com.beeroadam.service

import com.beeroadam.dto.ProductDTO
import com.beeroadam.dto.toDTO
import com.beeroadam.dto.toEntity
import com.beeroadam.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    @Autowired val productRepository: ProductRepository
) {
    private val logger = LoggerFactory.getLogger(ProductService::class.java)

    @Transactional
    fun getProducts(): List<ProductDTO> {
        logger.info("Fetching all products")
        return productRepository.findAll().map { it.toDTO() }
    }

    @Transactional
    fun getProduct(id: Long): ProductDTO {
        logger.info("Fetching product with id: $id")
        return productRepository.findById(id).map { it.toDTO() }
            .orElseThrow { NoSuchElementException("Product not found") }
    }

    @Transactional
    fun createProduct(productDTO: ProductDTO): ProductDTO {
        val product = productDTO.toEntity()

        val productDetailsList = productDTO.productDetails.map {
            val productDetails = it.toEntity(product = product)

            val attributes =
                it.attributes.map { attributeDTO -> attributeDTO.toEntity(productDetails = productDetails) }
            (productDetails.attributes as MutableList).addAll(attributes)
            productDetails
        }

        (product.productDetails as MutableList).addAll(productDetailsList)
        return productRepository.save(product).toDTO()
    }

    @Transactional
    fun deleteProduct(id: Long) {
        logger.info("Deleting product with id: $id")
        productRepository.deleteById(id)
    }

    @Transactional
    fun updateProduct(id: Long, productDTO: ProductDTO): ProductDTO {
        logger.info("Updating product with id: $id")
        val existingProduct = productRepository.findById(id).orElseThrow { NoSuchElementException("Product not found") }
        val product = productDTO.toEntity(existingProduct.id)

        val productDetailsList = productDTO.productDetails.mapIndexed { productDetailsIndex, productDetailsDTO ->
            val existingProductDetails = existingProduct.productDetails.getOrNull(productDetailsIndex)
            val productDetails = productDetailsDTO.toEntity(existingProductDetails?.id, product)

            val attributes = productDetailsDTO.attributes.mapIndexed { attributeIndex, attributeDTO ->
                val existingAttribute = existingProductDetails?.attributes?.getOrNull(attributeIndex)
                attributeDTO.toEntity(existingAttribute?.id, productDetails)
            }
            (productDetails.attributes as MutableList).addAll(attributes)
            productDetails
        }

        (product.productDetails as MutableList).addAll(productDetailsList)

        return productRepository.save(product).toDTO()
    }

}