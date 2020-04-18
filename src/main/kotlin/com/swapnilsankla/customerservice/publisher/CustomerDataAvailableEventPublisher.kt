package com.swapnilsankla.customerservice.publisher

import com.swapnilsankla.customerservice.model.Customer
import com.swapnilsankla.tracestarter.CustomKafkaTemplate
import com.swapnilsankla.tracestarter.Trace
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CustomerDataAvailableEventPublisher(@Autowired val kafkaTemplate: CustomKafkaTemplate) {

    fun publish(customer: Customer, trace: Trace) {
        kafkaTemplate.publish(
                topic = "customerDataAvailableForLoanProcessing",
                data = customer,
                trace = trace
        )
    }
}