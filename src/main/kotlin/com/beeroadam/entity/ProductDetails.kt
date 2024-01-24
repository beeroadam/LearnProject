package com.beeroadam.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "productDetails")
data class ProductDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    val description: String?,
    val price: Price,
    val quantity: Int,
    @OneToMany(mappedBy = "productDetails", cascade = [CascadeType.ALL])
    val attributes: List<Attribute> = mutableListOf(),
    val imageUrl: String?,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    val product: Product? = null
)

@Embeddable
data class Price(
    val leadPrice: Double, val strikeOutPrice: Double?
)