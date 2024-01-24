package com.beeroadam.repository

import com.beeroadam.entity.Attribute
import org.springframework.data.jpa.repository.JpaRepository

interface AttributeRepository: JpaRepository<Attribute, Long> {
}