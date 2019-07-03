package com.swapnilsankla.customerservice.model

import org.springframework.data.mongodb.core.mapping.Document

@Document("customers")
data class Customer(val customerId: String,
                    val name: String,
                    val pan: String?,
                    val address: String?)
