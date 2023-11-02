package com.example.bookstore.config

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.context.request.WebRequest
import java.util.HashMap

@Component
class ErrorAttributes : DefaultErrorAttributes() {

    override fun getErrorAttributes(webRequest: WebRequest?, includeStackTrace: Boolean): MutableMap<String, Any?> {
        val errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace)
        val attributesToReturn: MutableMap<String, Any?> = HashMap()
        attributesToReturn["message"] = errorAttributes["message"]
        return attributesToReturn
    }
}