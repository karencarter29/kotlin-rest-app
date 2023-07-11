package com.khryn.test.exceptionhandler

import com.khryn.test.exceptionhandler.exceptions.CustomerHasAlreadyExistException
import com.khryn.test.exceptionhandler.exceptions.CustomerNotFoundException
import com.khryn.test.exceptionhandler.exceptions.InvalidEmailOrPhoneNumberException
import com.khryn.test.model.ErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun handleInvalidEmailOrPhoneNumberException(ex: InvalidEmailOrPhoneNumberException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleCustomerHasAlreadyExistException(ex: CustomerHasAlreadyExistException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(HttpStatus.CONFLICT.value(), ex.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleCustomerNotFoundException(ex: CustomerNotFoundException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.message)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}