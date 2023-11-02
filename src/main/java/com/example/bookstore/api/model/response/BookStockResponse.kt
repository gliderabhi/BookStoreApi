package com.example.bookstore.api.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

data class BookStockResponse(
    private var id: Int = 0,
    private
    val isbn: String? = null,

    private
    val book: BookResponse? = null,

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    private val bookAddedToShelfAt: LocalDateTime? = null,

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    private val bookAtShelfUpdatedAt: LocalDateTime? = null
)