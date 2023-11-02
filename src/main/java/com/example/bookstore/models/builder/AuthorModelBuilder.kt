package com.example.bookstore.models.builder

import com.example.bookstore.models.Author
import java.time.LocalDate
import java.time.LocalDateTime

class AuthorModelBuilder {
    private var id: Int? = null
    private var firstName: String? = null
    private var lastName: String? = null
    private var birthDate: LocalDate? = null
    private var createdAt: LocalDateTime? = null
    private var updatedAt: LocalDateTime? = null

    fun build(): Author {
        val author = Author()
        author.id = id
        author.firstName = firstName
        author.lastName = lastName
        author.birthDate = birthDate
        author.createdAt = createdAt
        author.updatedAt = updatedAt
        return author
    }

    fun withId(id: Int?): AuthorModelBuilder {
        this.id = id
        return this
    }

    fun withFirstName(firstName: String?): AuthorModelBuilder {
        this.firstName = firstName
        return this
    }

    fun withLastName(lastName: String?): AuthorModelBuilder {
        this.lastName = lastName
        return this
    }

    fun withBirthDate(birthDate: LocalDate?): AuthorModelBuilder {
        this.birthDate = birthDate
        return this
    }

    fun withCreatedAt(createdAt: LocalDateTime?): AuthorModelBuilder {
        this.createdAt = createdAt
        return this
    }

    fun withUpdatedAt(updatedAt: LocalDateTime?): AuthorModelBuilder {
        this.updatedAt = updatedAt
        return this
    }
}