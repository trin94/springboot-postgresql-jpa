package com.example.sbplrest.exception

import java.time.LocalDateTime

interface ErrorDetails {

    val timestamp: LocalDateTime

    val message: String

    val details: String

}