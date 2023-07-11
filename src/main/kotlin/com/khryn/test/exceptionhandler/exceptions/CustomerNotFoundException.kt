package com.khryn.test.exceptionhandler.exceptions

class CustomerNotFoundException(message: String) : RuntimeException(message) {
}