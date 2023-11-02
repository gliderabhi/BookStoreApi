package com.example.bookstore.api.model.request

import com.example.bookstore.config.ValidationConfig
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class SearchByBookNameRequest(
    var bookName: @NotBlank(message = "{bookName.notBlank}") @Size(
        max = ValidationConfig.MAX_LENGTH_BOOK_NAME,
        message = "{bookName.size}"
    ) @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT, message = "{name.pattern}") String? = null
)