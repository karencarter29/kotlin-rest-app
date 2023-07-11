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
    private val id: Long,
    @Column(name = "firstName")
    private val firstName: String,
    @Column(name = "lastName")
    private val lastName: String,
    @Column(name = "phoneNumber")
    private val phoneNumber: String,
    @Column(name = "email")
    private val email: String,
    @Column(name = "app")
    private val app: String,
    @Column(name = "date")
    private val date: LocalDate = LocalDate.now()
) {
    fun getId(): Long = id
    fun getFirstName(): String = firstName
    fun getLastName(): String = lastName
    fun getPhoneNumber(): String = phoneNumber
    fun getEmail(): String = email
    fun getApp(): String = app
    fun getDate(): LocalDate = date
}
