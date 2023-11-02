package com.example.bookstore.api.model.response

data class BookStockResponseWithBookCountResponse(
    private var bookStocks: MutableList<Any>? = null,
    private val numberOfBooks: Int = 0
)