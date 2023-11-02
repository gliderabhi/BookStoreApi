package com.example.bookstore.api.model.request

import com.example.bookstore.config.ValidationConfig
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class UpdateBookStockRequest(
    var isbn: @NotBlank(message = "{isbn.notBlank}") @Pattern(
        regexp = ValidationConfig.PATTERN_ISBN_10_13,
        message = "{isbn.valid}"
    ) String? = null,
    val updateStockId: Int

) : BasicRequest(updateStockId)