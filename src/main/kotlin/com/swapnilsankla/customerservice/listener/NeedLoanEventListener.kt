package com.swapnilsankla.customerservice.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.swapnilsankla.customerservice.publisher.CustomerDataAvailableEventPublisher
import com.swapnilsankla.customerservice.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class NeedLoanEventListener(@Autowired val repository: CustomerRepository,
                            @Autowired val objectMapper: ObjectMapper,
                            @Autowired val customerDataAvailableEventPublisher: CustomerDataAvailableEventPublisher) {

    @KafkaListener(topics = ["needLoanEvent"])
    fun listen(needLoanEventString: String) {
        val needLoanEvent = objectMapper.readValue(needLoanEventString, NeedLoanEvent::class.java)
        Logger.getLogger(NeedLoanEventListener::class.simpleName)
                .info("needLoanEvent event received for customer ${needLoanEvent.customerId}")

        repository.
                findByCustomerId(needLoanEvent.customerId)
                .doOnSuccess(customerDataAvailableEventPublisher::publish)
                .subscribe()
    }
}