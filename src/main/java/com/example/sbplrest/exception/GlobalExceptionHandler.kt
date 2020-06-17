package com.example.sbplrest.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {

    private data class Details(
        override val timestamp: LocalDateTime,
        override val message: String,
        override val details: String
    ) : ErrorDetails

    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundException(e: ResourceNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val details = Details(LocalDateTime.now(), e.message!!, request.getDescription(false))
        return ResponseEntity(details, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun globalExceptionHandler(e: Exception, request: WebRequest): ResponseEntity<Any> {
        val details = Details(LocalDateTime.now(), e.message!!, request.getDescription(false))
        return ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR)
    }


}