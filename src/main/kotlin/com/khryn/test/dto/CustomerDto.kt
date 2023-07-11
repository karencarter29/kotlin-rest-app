package com.khryn.test.dto

import java.time.LocalDate

data class CustomerDto (
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val app: String,
    val date: LocalDate
)