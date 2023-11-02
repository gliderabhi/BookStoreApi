package com.example.bookstore.repositories

import com.example.bookstore.models.BookStock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookStockRepository : JpaRepository<BookStock, Int> {
    fun getBookStocksByBookId(bookId: Int?): List<BookStock?>?
    fun getBookStocksByBookNameContainingIgnoreCase(bookName: String?): List<BookStock?>?
}