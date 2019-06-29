package com.swapnilsankla.customerservice.controller

import com.swapnilsankla.customerservice.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(@Autowired val repository: CustomerRepository) {
    @GetMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun get() = repository.findAll()
}