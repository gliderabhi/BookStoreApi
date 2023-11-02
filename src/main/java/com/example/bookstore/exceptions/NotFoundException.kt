package com.example.bookstore.exceptions

import org.springframework.http.HttpStatus

data class NotFoundException(
    var exp_status: HttpStatus = HttpStatus.NOT_FOUND,
) : RuntimeException(), ICustomException  {
    override fun getStatus(): HttpStatus {
        return exp_status
    }

}