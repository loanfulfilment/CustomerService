package com.swapnilsankla.customerservice.publisher

import com.fasterxml.jackson.databind.ObjectMapper
import com.swapnilsankla.customerservice.listener.NeedLoanEventListener
import com.swapnilsankla.customerservice.model.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class CustomerDataAvailableEventPublisher(@Autowired val kafkaTemplate: KafkaTemplate<String, String>,
                                          @Autowired val objectMapper: ObjectMapper) {

    fun publish(customer: Customer) {
        Logger.getLogger(CustomerDataAvailableEventPublisher::class.simpleName).info("raising event $customer")

            kafkaTemplate.send("customerDataAvailableForLoanProcessing",
                objectMapper.writeValueAsString(customer))
    }
}