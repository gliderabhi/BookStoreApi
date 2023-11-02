package com.example.bookstore.api.model.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

open class BasicRequest(
    @NotNull(message = "{id.notNull}")
    @Positive(message = "{id.positive}")
    val id: Int
)