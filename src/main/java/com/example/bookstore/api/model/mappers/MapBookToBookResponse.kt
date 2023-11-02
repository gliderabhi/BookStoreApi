package com.example.bookstore.api.model.mappers

import com.example.bookstore.api.model.response.BookResponse
import com.example.bookstore.models.Book

class MapBookToBookResponse {
    fun mapBookToBookResponse(book: Book): BookResponse {
        return BookResponse(
            id = book.id ?: 0,
            name = book.name,
            createdAt = book.createdAt,
            description = book.description,
            updatedAt = book.updatedAt,
            publishedYear = book.publishedYear,
            price = book.price,
            authors = book.bookAuthors?.map { MapAuthorAndAuthorResponse().mapAuthorToAuthorResponse(it) }?.toSet(),
            )
    }
}