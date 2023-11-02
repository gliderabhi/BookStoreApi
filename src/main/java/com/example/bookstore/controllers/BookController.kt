package com.example.bookstore.controllers

import com.example.bookstore.api.model.mappers.MapBookToBookResponse
import com.example.bookstore.api.model.request.CreateBookRequest
import com.example.bookstore.api.model.request.UpdateBookRequest
import com.example.bookstore.api.model.response.BookResponse
import com.example.bookstore.api.model.response.CreateEntityResponse
import com.example.bookstore.models.Book
import com.example.bookstore.services.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull


@RestController
@RequestMapping(value = ["/book"])
class BookController(
    val bookService: BookService
) {
    @get:RequestMapping(method = [RequestMethod.GET])
    val bookList: List<Any>
        get() {
            val list: List<Book> = bookService.getBookList()
            return list.map { MapBookToBookResponse().mapBookToBookResponse(it) }
        }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun getBook(@PathVariable(name = "id") id: Int?): BookResponse {
        val book: Book = bookService.getBookById(id ?: 0)
        return MapBookToBookResponse().mapBookToBookResponse(book)
    }

    @RequestMapping(method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody request: @Valid CreateBookRequest?): CreateEntityResponse {
        val id: Int? = request?.let { bookService.createBook(it) }
        return CreateEntityResponse(id)
    }

    @RequestMapping(method = [RequestMethod.PATCH])
    fun updateBook(@RequestBody updateBookRequest: @Valid UpdateBookRequest?) {
        if (updateBookRequest != null) {
            bookService.updateBook(updateBookRequest)
        }
    }

    @RequestMapping(method = [RequestMethod.DELETE])
    fun deleteBook(@RequestParam(name = "id") id: @NotNull(message = "{id.notNull}") Int?) {
        if (id != null) {
            bookService.deleteBook(id)
        }
    }
}

