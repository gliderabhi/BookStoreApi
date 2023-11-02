package com.example.bookstore.services

import com.example.bookstore.api.model.request.CreateBookRequest
import com.example.bookstore.api.model.request.UpdateBookRequest
import com.example.bookstore.exceptions.NotFoundException
import com.example.bookstore.models.Author
import com.example.bookstore.models.Book
import com.example.bookstore.repositories.BookRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.function.Function

@Service
@Transactional
class BookService(
    val bookRepository: BookRepository,
    val authorService: AuthorService
) {

    fun getBookList(): List<Book> {
        return bookRepository.findAll()
    }

    fun getBookById(id: Int): Book {
        return bookRepository.findById(id)
            .orElseThrow { NotFoundException(exp_status = HttpStatus.NOT_FOUND) }
    }

    fun updateBook(updateBookRequest: UpdateBookRequest) {
        val book = bookRepository.findById(updateBookRequest.id)
            .orElseThrow { NotFoundException(exp_status = HttpStatus.NOT_FOUND) }
        val authors = updateBookRequest.authorIds?.let { authorService.getAuthorsByIds(it) }?.toSet()
        book.name = updateBookRequest.name
        book.category = updateBookRequest.category
        book.description = updateBookRequest.description
        book.price = updateBookRequest.price ?: 0.0
        book.publishedYear = updateBookRequest.publishedYear ?: 0
        if (authors != null) {
            book.bookAuthors = authors
        }
        bookRepository.save(book)
    }

    fun createBook(createBookRequest: CreateBookRequest): Int? {
        val authors: Set<Author>? = createBookRequest.authorIds
            ?.let { authorService.getAuthorsByIds(it) }
            ?.toSet()
        val book = Book()
        book.name = createBookRequest.name
        book.category = createBookRequest.category
        book.price = createBookRequest.price
        book.publishedYear = createBookRequest.publishedYear
        book.description = createBookRequest.description
        book.bookAuthors = authors
        val createdBook = bookRepository.save(book)
        return createdBook.id
    }

    fun deleteBook(id: Int) {
        if (!bookRepository.existsById(id)) {
            throw NotFoundException(exp_status = HttpStatus.NOT_FOUND)
        }
        bookRepository.deleteById(id)
    }
}