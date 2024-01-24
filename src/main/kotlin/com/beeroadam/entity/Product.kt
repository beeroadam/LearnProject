package com.beeroadam.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    val name: String,
    @Enumerated(EnumType.STRING)
    val category: ProductCategory,
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    val productDetails: List<ProductDetails> = mutableListOf()
)



enum class ProductCategory {
    ELECTRONICS, CLOTHING, GROCERY, BOOKS, HOME_APPLIANCES
}

