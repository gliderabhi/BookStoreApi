package com.example.bookstore.services

import com.example.bookstore.api.model.request.CreateAuthorRequest
import com.example.bookstore.exceptions.NotFoundException
import com.example.bookstore.models.Author
import com.example.bookstore.repositories.AuthorRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
@Transactional
class AuthorService(
    val authorRepository: AuthorRepository
) {

    fun list(): List<Author> {
        return authorRepository.findAll()
    }

    fun findById(id: Int): Author {
        return authorRepository.findById(id)
            .orElseThrow { NotFoundException(exp_status = HttpStatus.NOT_FOUND) }
    }

    fun create(request: CreateAuthorRequest?): Int? {
        val author = Author()
        author.firstName = request?.firstName
        author.lastName = request?.lastName
        author.birthDate = request?.birthDate
        val createdAuthor = authorRepository.save(author)
        return createdAuthor.id
    }

    fun delete(id: Int) {
        if (!authorRepository.existsById(id)) {
            throw NotFoundException(exp_status = HttpStatus.NOT_FOUND)
        }
        authorRepository.deleteById(id)
    }

    fun getAuthorsByIds(authorIds: List<Int>): Set<Author> {
        return authorIds.stream()
            .map { id: Int -> findById(id) }
            .collect(Collectors.toSet())
    }
}