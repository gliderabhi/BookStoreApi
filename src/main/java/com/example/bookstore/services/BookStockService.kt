package com.example.bookstore.services

import com.example.bookstore.api.model.request.CreateBookStockRequest
import com.example.bookstore.api.model.request.UpdateBookStockRequest
import com.example.bookstore.api.model.response.BookStockResponse
import com.example.bookstore.api.model.response.BookStockResponseWithBookCountResponse
import com.example.bookstore.exceptions.NotFoundException
import com.example.bookstore.exceptions.ValidationException
import com.example.bookstore.models.Book
import com.example.bookstore.models.BookStock
import com.example.bookstore.repositories.BookRepository
import com.example.bookstore.repositories.BookStockRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors
@Service
@Transactional
class BookStockService(
     val bookStockRepository: BookStockRepository,
     val bookRepository: BookRepository
) {

    fun getBooksAtShelf(): List<BookStock> {
        return bookStockRepository.findAll()
    }


    fun getBookInShelveInStoreByShelfId(id: Int): BookStock {
        return bookStockRepository.findById(id)
            .orElseThrow {
                NotFoundException(
                    exp_status = HttpStatus.NOT_FOUND
                )

            }
    }

    fun addBookToTheShelfInStore(createBookStockRequest: CreateBookStockRequest): Int? {
        val existingBook: Book? = createBookStockRequest.bookId?.let {
            bookRepository.findById(it)
                .orElseThrow { ValidationException(exp_status = HttpStatus.NOT_FOUND) }
        }
        val bookStock = BookStock()
        bookStock.book = existingBook
        bookStock.isbn = createBookStockRequest.isbn
        val savedBookInShelf: BookStock = bookStockRepository.save(bookStock)
        return savedBookInShelf.id
    }

    fun getBookInShelveByBookId(bookId: Int): List<BookStock?>? {
        if (!bookRepository.existsById(bookId)) {
            throw NotFoundException(exp_status = HttpStatus.NOT_FOUND)
        }
        return bookStockRepository.getBookStocksByBookId(bookId)
    }

    fun getBookStocksByBookByName(bookName: String?): List<BookStock?>? {
        val bookStocks: List<BookStock?>? = bookStockRepository.getBookStocksByBookNameContainingIgnoreCase(bookName)
        if (bookStocks?.isEmpty() == true) {
            throw NotFoundException(exp_status = HttpStatus.NOT_FOUND)
        }
        return bookStocks
    }

    fun getBookInShelveByBookIdWithAvailableBookCount(bookId: Int): BookStockResponseWithBookCountResponse {
        val bookStocks = getBookInShelveByBookId(bookId)
        return mapBookStockListToWrapperClass(bookStocks)
    }

    fun getBookInShelveByBookNameWithAvailableBookCount(bookName: String?): BookStockResponseWithBookCountResponse {
        val bookStocks = getBookStocksByBookByName(bookName)
        return mapBookStockListToWrapperClass(bookStocks)
    }

    private fun mapBookStockListToWrapperClass(bookStocks: List<BookStock?>?): BookStockResponseWithBookCountResponse {
        if (bookStocks?.isEmpty() == true) {
            throw NotFoundException(exp_status = HttpStatus.NOT_FOUND)
        }
        val bookStockResponseList: MutableList<Any>? = bookStocks?.stream()
            ?.map<Any> { BookStockResponse() }
            ?.collect(Collectors.toList<Any>())
        val numberOfBooks = bookStockResponseList?.size
        return BookStockResponseWithBookCountResponse(bookStockResponseList, numberOfBooks ?: 0)
    }

    fun updateBookInShelf(request: UpdateBookStockRequest) {
        val bookStock: BookStock = bookStockRepository.findById(request.id)
            .orElseThrow { NotFoundException(exp_status = HttpStatus.NOT_FOUND) }
        bookStock.isbn = request.isbn
        bookStockRepository.save(bookStock)
    }

    fun deleteBookInShelve(id: Int) {
        if (!bookStockRepository.existsById(id)) {
            throw NotFoundException(exp_status = HttpStatus.NOT_FOUND)
        }
        bookStockRepository.deleteById(id)
    }
}