package com.beeroadam.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.NoHandlerFoundException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleProductNotFoundError(e: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity("Product not found", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFoundError(e: NoHandlerFoundException): ResponseEntity<String> {
        return ResponseEntity("Page not found", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericError(e: Exception): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}