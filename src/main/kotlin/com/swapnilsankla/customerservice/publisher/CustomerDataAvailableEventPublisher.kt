package com.swapnilsankla.customerservice.publisher

import com.fasterxml.jackson.databind.ObjectMapper
import com.swapnilsankla.customerservice.listener.NeedLoanEventListener
import com.swapnilsankla.customerservice.model.Customer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.header.internals.RecordHeader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class CustomerDataAvailableEventPublisher(@Autowired val kafkaTemplate: KafkaTemplate<String, String>,
                                          @Autowired val objectMapper: ObjectMapper) {

    private val logger = Logger.getLogger(CustomerDataAvailableEventPublisher::class.simpleName)

    fun publish(customer: Customer, traceId: ByteArray) {
        val message = buildMessage(customer, traceId)
        logger.info("publishing message $message for customer $customer")
        kafkaTemplate.send(message)
    }

    private fun buildMessage(customer: Customer, traceId: ByteArray): ProducerRecord<String, String> {
        val message = ProducerRecord<String, String>(
                "customerDataAvailableForLoanProcessing",
                objectMapper.writeValueAsString(customer)
        )
        message.headers().remove("uber-trace-id")
        message.headers().add("uber-trace-id", traceId)

        return message
    }
}