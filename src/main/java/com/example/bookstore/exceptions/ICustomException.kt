package com.example.bookstore.exceptions

import org.springframework.http.HttpStatus

interface ICustomException {
    fun getStatus(): HttpStatus
}

