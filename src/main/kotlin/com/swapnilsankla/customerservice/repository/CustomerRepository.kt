package com.swapnilsankla.customerservice.repository

import com.swapnilsankla.customerservice.model.Customer
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface CustomerRepository: ReactiveMongoRepository<Customer, String> {
    fun findByCustomerId(customerId: String): Mono<Customer>
}
