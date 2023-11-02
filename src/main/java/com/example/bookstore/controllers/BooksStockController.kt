package com.example.bookstore.controllers

import com.example.bookstore.api.model.request.CreateBookStockRequest
import com.example.bookstore.api.model.request.SearchByBookNameRequest
import com.example.bookstore.api.model.request.UpdateBookStockRequest
import com.example.bookstore.api.model.response.BookStockResponse
import com.example.bookstore.api.model.response.BookStockResponseWithBookCountResponse
import com.example.bookstore.api.model.response.CreateEntityResponse
import com.example.bookstore.models.BookStock
import com.example.bookstore.services.BookStockService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.function.Function
import java.util.stream.Collectors
import javax.validation.Valid
import javax.validation.constraints.NotNull


@RestController
@RequestMapping(value = ["/bookShelf"])
class BooksStockController(
    val bookStockService: BookStockService
) {

    @get:RequestMapping(method = [RequestMethod.GET])
    val bookStockList: List<Any>
        get() {
            val list: List<BookStock> = bookStockService.getBooksAtShelf()
            return list.stream()
                .map<Any>(Function<BookStock, Any> { BookStockResponse() })
                .collect(Collectors.toList<Any>())
        }

    @RequestMapping(value = ["/book/{bookId}"], method = [RequestMethod.GET])
    fun getBook(@PathVariable(name = "bookId") bookId: Int?): BookStockResponseWithBookCountResponse? {
        return bookId?.let { bookStockService.getBookInShelveByBookIdWithAvailableBookCount(it) }
    }

    @RequestMapping(value = ["/book/name"], method = [RequestMethod.POST])
    fun getBookByBookByBookName(@RequestBody bookNameRequest: @Valid SearchByBookNameRequest?): BookStockResponseWithBookCountResponse {
        return bookStockService.getBookInShelveByBookNameWithAvailableBookCount(bookNameRequest?.bookName)
    }

    // adding the physical book to shelf in store
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = [RequestMethod.POST])
    fun saveBookToShelf(@RequestBody request: @Valid CreateBookStockRequest?): CreateEntityResponse {
        val id: Int? = request?.let { bookStockService.addBookToTheShelfInStore(it) }
        return CreateEntityResponse(id)
    }

    @RequestMapping(method = [RequestMethod.PATCH])
    fun updateBookAtShelf(@RequestBody request: @Valid UpdateBookStockRequest?) {
        if (request != null) {
            bookStockService.updateBookInShelf(request)
        }
    }

    @RequestMapping(method = [RequestMethod.DELETE])
    fun deleteBookStock(@RequestParam(name = "id") id: @NotNull(message = "Id should not be null!") Int?) {
        if (id != null) {
            bookStockService.deleteBookInShelve(id)
        }
    }
}

