package com.khryn.test.repository

import com.khryn.test.model.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CrudRepository<Customer, Long> {

    fun findByEmail(email: String): Customer?

    fun findByPhoneNumber(phoneNumber: String): Customer?
}