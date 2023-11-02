package com.example.bookstore.api.model.mappers

import com.example.bookstore.api.model.response.AuthorResponse
import com.example.bookstore.models.Author

class MapAuthorAndAuthorResponse {

    fun mapAuthorToAuthorResponse(
        author: Author
    ): AuthorResponse {
        return AuthorResponse(
            id = author.id ?: 0,
            firstName = author.firstName,
            lastName = author.lastName,
            createdAt = author.createdAt,
            updatedAt = author.updatedAt,
            books = author.authorBooks?.map { MapBookToBookResponse().mapBookToBookResponse(it) }?.toSet(),
            birthDate = author.birthDate
        )
    }
}