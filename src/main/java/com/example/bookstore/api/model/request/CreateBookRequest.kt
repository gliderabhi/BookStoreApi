package com.example.bookstore.api.model.request

import com.example.bookstore.config.ValidationConfig
import javax.validation.constraints.*

data class CreateBookRequest(
     var name: @NotBlank(message = "{bookName.notBlank}") @Size(
        max = ValidationConfig.MAX_LENGTH_BOOK_NAME,
        message = "{bookName.size}"
    ) @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT, message = "{name.pattern}") String? = null,

    
    val price: @PositiveOrZero(message = "{price.positiveOrZero}") Double = 0.0,

    
    val publishedYear: @Positive(message = "{publishedYear.positive}") Int = 0,

    
    val authorIds: List<Int>? = null,


    
    val category: @NotBlank(message = "{category.notBlank}") @Pattern(regexp = ValidationConfig.PATTERN_NAME) @Size(
        max = ValidationConfig.MAX_LENGTH_NAME
    ) String? = null,

    
    val description: @NotBlank(message = "{description.notBlank}") @Size(
        max = ValidationConfig.MAX_LENGTH_DEFAULT,
        message = "{description.size}"
    ) @Pattern(
        regexp = ValidationConfig.PATTERN_FREE_TEXT,
        message = "{description.pattern}"
    ) String? = null
)