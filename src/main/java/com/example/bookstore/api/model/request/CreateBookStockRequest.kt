package com.example.bookstore.api.model.request

import com.example.bookstore.config.ValidationConfig
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive

data class CreateBookStockRequest(
    var bookId: @NotNull(message = "{id.notNull}") @Positive(message = "{id.positive}") Int? = null,
    val isbn: @NotBlank(message = "{isbn.notBlank}") @Pattern(
        regexp = ValidationConfig.PATTERN_ISBN_10_13,
        message = "{isbn.valid}"
    ) String? = null
)