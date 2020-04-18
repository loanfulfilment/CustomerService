package com.swapnilsankla.customerservice.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.swapnilsankla.customerservice.publisher.CustomerDataAvailableEventPublisher
import com.swapnilsankla.customerservice.repository.CustomerRepository
import com.swapnilsankla.tracestarter.TraceIdExtractor
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.stereotype.Component

@Component
class NeedLoanEventListener(@Autowired val repository: CustomerRepository,
                            @Autowired val objectMapper: ObjectMapper,
                            @Autowired val customerDataAvailableEventPublisher: CustomerDataAvailableEventPublisher,
                            @Autowired val traceIdExtractor: TraceIdExtractor) {

    val logger = LoggerFactory.getLogger(NeedLoanEventListener::class.simpleName)

    @KafkaListener(topics = ["needLoanEvent"])
    fun listen(needLoanEventString: String, @Headers headers: Map<String, Any>) {
        val needLoanEvent = objectMapper.readValue(needLoanEventString, NeedLoanEvent::class.java)
        logger.info("needLoanEvent event received for customer ${needLoanEvent.customerId}")

        val trace = traceIdExtractor.fromKafkaHeaders(headers)

        repository.findByCustomerId(needLoanEvent.customerId)
                .doOnSuccess { customerDataAvailableEventPublisher.publish(it, trace) }
                .subscribe()
    }
}