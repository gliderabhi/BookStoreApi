package com.example.bookstore.api.model.request

import com.example.bookstore.config.ValidationConfig
import org.springframework.data.annotation.Id
import javax.validation.constraints.*

data class UpdateBookRequest(
    var name: @NotBlank(message = "{bookName.notBlank}") @Size(
        max = ValidationConfig.MAX_LENGTH_BOOK_NAME,
        message = "{bookName.size}"
    ) @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT, message = "{name.pattern}") String? = null,


    val price: @PositiveOrZero(message = "{price.positiveOrZero}") Double? = null,


    val publishedYear: @Positive(message = "{publishedYear.positive}") @Max(
        value = 2100,
        message = "{publishedYear.max}"
    ) Int? = null,


    val authorIds: List<Int>? = null,


    val category: @NotBlank(message = "{category.notBlank}") @Pattern(regexp = ValidationConfig.PATTERN_NAME) @Size(
        max = ValidationConfig.MAX_LENGTH_NAME
    ) String? = null,


    val description: @NotBlank(message = "{description.notBlank}") @Size(max = ValidationConfig.MAX_LENGTH_DEFAULT) @Pattern(
        regexp = ValidationConfig.PATTERN_FREE_TEXT
    ) String? = null,

    val updateBookId: Int = 0
) : BasicRequest(updateBookId)