package com.swapnilsankla.customerservice.repository

import com.swapnilsankla.customerservice.model.Customer
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface CustomerRepository: ReactiveMongoRepository<Customer, String>
