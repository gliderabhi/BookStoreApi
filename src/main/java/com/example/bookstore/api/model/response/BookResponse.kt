package com.example.bookstore.api.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

data class BookResponse(

    private var id: Int = 0,
    private
    val name: String? = null,
    private
    val price: Double = 0.0,
    private
    val publishedYear: Int = 0,
    private
    val category: String? = null,
    private
    val description: String? = null,

    @JsonIgnoreProperties("books")
    private val authors: Set<AuthorResponse>? = null,

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    private val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    private val updatedAt: LocalDateTime? = null
)