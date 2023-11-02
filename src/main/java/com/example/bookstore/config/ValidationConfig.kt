package com.example.bookstore.config

object ValidationConfig {

    /**
     * Matches for international phone numbers without any separator
     */
    const val PATTERN_PHONE_NUMBER = "^(00|\\+)[1-9]{1}([0-9]){9,16}$"

    /**
     * Matches for 10 or 13-digit ISBN numbers without any separator
     */
    const val PATTERN_ISBN_10_13 = "^(97([89]))?\\d{9}(\\d|X)$"

    /**
     * Matches for names, e.g. first name, last name on any language
     */
    const val PATTERN_NAME = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$"

    /**
     * Matches for free text fields, e.g. title, description on any language
     */
    const val PATTERN_FREE_TEXT = "^(?:\\p{L}\\p{M}*|[0-9]*|[\\/\\-+.,?!*();\"]|\\s)*$"

    /**
     * Matches for passwords
     */
    const val PATTERN_PASSWORD = "^[A-Za-z0-9]*$"

    /**
     * Max length of names, e.g. first name, last name
     */
    const val MAX_LENGTH_NAME = 60

    /**
     * Max length of name (book name)
     */
    const val MAX_LENGTH_BOOK_NAME = 100

    /**
     * Default max length
     */
    const val MAX_LENGTH_DEFAULT = 255

    /**
     * Max length of description
     */
    const val MIN_LENGTH_PASSWORD = 6
}