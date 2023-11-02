package com.example.bookstore.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component

@Component
class MessageProvider {

    private var messageSource: MessageSource? = null

    @Autowired
    fun MessageProvider(messageSource: MessageSource?) {
        this.messageSource = messageSource
    }

    fun getMessage(messageCode: String?): String {
        return getMessage(messageCode, null)
    }

    fun getMessage(messageCode: String?, args: List<Any?>?): String {
        val locale = LocaleContextHolder.getLocale()
        return messageSource!!.getMessage(messageCode, args?.toTypedArray(), locale)
    }
}