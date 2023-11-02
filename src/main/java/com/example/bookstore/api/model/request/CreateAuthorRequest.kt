package com.example.bookstore.api.model.request

import com.example.bookstore.config.ValidationConfig
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class CreateAuthorRequest(

    var firstName:
    @NotBlank(message = "{firstName.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_NAME, message = "{firstName.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME, message = "{firstName.pattern}")
    String? = null,

    val lastName:
    @NotBlank(message = "{lastName.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_NAME, message = "{lastName.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME, message = "{lastName.pattern}")
    String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer::class)
    val birthDate:
    @Past(message = "{birthDate.past}")
    LocalDate? = null
)