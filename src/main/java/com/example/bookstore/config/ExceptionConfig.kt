package com.example.bookstore.config

import com.example.bookstore.api.model.response.GenericResponse
import com.example.bookstore.exceptions.ICustomException
import com.example.bookstore.exceptions.NotFoundException
import com.example.bookstore.exceptions.ValidationException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import java.util.stream.Collectors
import javax.validation.ConstraintViolation

@ControllerAdvice
class ExceptionConfig {

    private var messageProvider: MessageProvider? = null

    fun ExceptionConfig(messageProvider: MessageProvider?) {
        this.messageProvider = messageProvider
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseBody
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<GenericResponse> {
        val status = if (ex.exp_status == null) HttpStatus.NOT_FOUND else ex.exp_status
        return buildResponse(ex.message, status)
    }

    @ExceptionHandler(ValidationException::class)
    @ResponseBody
    fun handleValidationException(ex: ValidationException): ResponseEntity<GenericResponse> {
        val status = if (ex.exp_status == null) HttpStatus.BAD_REQUEST else ex.exp_status
        return buildResponse(ex.message, status)
    }

    @ExceptionHandler(RuntimeException::class)
    @ResponseBody
    fun handleRuntimeException(exp: RuntimeException): ResponseEntity<GenericResponse> {
        if (exp is ICustomException) {
            return buildResponse(exp.message, (exp as ICustomException).getStatus())
        }
        val message = messageProvider!!.getMessage("error.internalServerError")
        return buildResponse(message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseBody
    fun handleDataIntegrityViolationException(ex: Exception): ResponseEntity<GenericResponse> {
        if (ex.cause is ConstraintViolationException) {
            val message = messageProvider!!.getMessage("item.unableToDelete")
            return buildResponse(message, HttpStatus.BAD_REQUEST)
        }
        return handleGlobalException()
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<GenericResponse> {
        val fieldErrors = ex.bindingResult.fieldErrors
        val errorMessage = fieldErrors.stream()
            .map { obj: FieldError -> obj.defaultMessage }
            .collect(Collectors.joining(", "))
        return buildResponse(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException::class)
    @ResponseBody
    fun handleConstraintViolation(ex: javax.validation.ConstraintViolationException): ResponseEntity<GenericResponse> {
        val message = ex.constraintViolations
            .stream()
            .map { obj: ConstraintViolation<*> -> obj.message }
            .collect(Collectors.joining(", "))
        return buildResponse(message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleGlobalException(): ResponseEntity<GenericResponse> {
        val message = messageProvider!!.getMessage("error.internalServerError")
        return buildResponse(message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(
        IllegalArgumentException::class,
        InvalidDataAccessApiUsageException::class
    )
    @ResponseBody
    fun handleArgumentException(ex: Exception): ResponseEntity<GenericResponse> {
        return buildResponse(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseBody
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException?): ResponseEntity<GenericResponse> {
        val message = messageProvider!!.getMessage("json.invalidFormat")
        return buildResponse(message, HttpStatus.FORBIDDEN)
    }

    private fun buildResponse(message: String?, status: HttpStatus): ResponseEntity<GenericResponse> {
        return ResponseEntity<GenericResponse>(GenericResponse(message), status)
    }
}