package com.khryn.test

import com.khryn.test.model.Customer
import com.khryn.test.service.CustomerService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customer")
class CustomerController(val customerService: CustomerService) {

    @GetMapping
    fun getAll(): List<Customer> = customerService.getAllCustomers()

    @GetMapping("/findBy")
    fun getByEmailOrPhoneNumber(@RequestParam data: String): Customer = customerService.getByEmailOtPhoneNumber(data)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: Customer) = customerService.createCustomer(customer)

    @DeleteMapping
    fun delete(@RequestParam id: Long) = customerService.deleteCustomer(id)
}