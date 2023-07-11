package com.khryn.test.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
data class Customer(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "firstName")
    val firstName: String,
    @Column(name = "lastName")
    val lastName: String,
    @Column(name = "phoneNumber")
    val phoneNumber: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "app")
    val app: String,
    @Column(name = "date")
    val date: LocalDate = LocalDate.now()
)