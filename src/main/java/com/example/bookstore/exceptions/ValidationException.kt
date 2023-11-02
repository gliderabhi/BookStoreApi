package com.example.bookstore.exceptions

import org.springframework.http.HttpStatus

data class ValidationException(
    val exp_status : HttpStatus = HttpStatus.BAD_REQUEST
) : RuntimeException(), ICustomException {
    override fun getStatus(): HttpStatus {
        return exp_status
    }
}