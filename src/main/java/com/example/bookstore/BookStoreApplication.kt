package com.example.bookstore

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class BookStoreApplication

fun main(args: Array<String>) {
    runApplication<BookStoreApplication>(*args)
}