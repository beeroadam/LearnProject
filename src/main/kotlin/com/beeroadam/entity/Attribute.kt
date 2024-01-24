package com.beeroadam.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "attribute")
data class Attribute(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    val name: String,
    @Column(name = "attribute_value")
    val value: String,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "product_details_id")
    @JsonBackReference
    val productDetails: ProductDetails
)