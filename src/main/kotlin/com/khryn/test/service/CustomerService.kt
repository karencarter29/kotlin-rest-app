package com.khryn.test.service

import com.khryn.test.exceptionhandler.exceptions.CustomerHasAlreadyExistException
import com.khryn.test.exceptionhandler.exceptions.CustomerNotFoundException
import com.khryn.test.exceptionhandler.exceptions.InvalidEmailOrPhoneNumberException
import com.khryn.test.model.Customer
import com.khryn.test.repository.CustomerRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository) {

    private val logger = KotlinLogging.logger {}

    private val phoneNumberRegex: Regex = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}\$".toRegex()
    private val emailRegex: Regex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex()

    fun getAllCustomers(): List<Customer> {
        logger.info { "Searching for all customers" }
        return customerRepository.findAll().toList()
    }

    fun getByEmailOtPhoneNumber(data: String): Customer {
        logger.info { "Searching for customer by $data" }
        if (phoneNumberRegex.matches(data) && data.length >= 10) {
            val customer: Customer? = customerRepository.findByPhoneNumber(data)
            if (customer != null) return customer
            throw CustomerNotFoundException("Customer with provided phone number does not exist")
        }

        if (emailRegex.matches(data)) {
            val customer: Customer? = customerRepository.findByEmail(data)
            if (customer != null) return customer
            throw CustomerNotFoundException("Customer with provided email does not exist")
        }
        throw InvalidEmailOrPhoneNumberException("Invalid email or phone number")
    }

    fun createCustomer(customer: Customer): Customer {
        if (!phoneNumberRegex.matches(customer.getPhoneNumber()) && customer.getPhoneNumber().length < 10)
            throw InvalidEmailOrPhoneNumberException("Invalid phone number format")

        if (!emailRegex.matches(customer.getEmail()))
            throw InvalidEmailOrPhoneNumberException("Invalid email format")

        if (customerRepository.findByEmail(customer.getEmail()) != null)
            throw CustomerHasAlreadyExistException("Customer with such email has already exist")

        if (customerRepository.findByPhoneNumber(customer.getPhoneNumber()) != null)
            throw CustomerHasAlreadyExistException("Customer with such phone number has already exist")

        logger.info { "Customer created with email : ${customer.getEmail()}" }
        return customerRepository.save(customer)
    }

    fun deleteCustomer(id: Long) {
        logger.info { "Deleting customer with id : $id" }
        customerRepository.deleteById(id)
    }
}