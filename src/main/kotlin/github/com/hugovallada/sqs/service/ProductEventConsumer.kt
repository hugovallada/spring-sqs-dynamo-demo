package github.com.hugovallada.sqs.service

import com.fasterxml.jackson.databind.ObjectMapper
import github.com.hugovallada.sqs.model.*
import github.com.hugovallada.sqs.repository.ProductEventLogRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import javax.jms.TextMessage

@Service
class ProductEventConsumer(private val mapper: ObjectMapper, private val repository: ProductEventLogRepository) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)


    @JmsListener(destination = "\${aws.sqs.queue.product.events.name}")
    fun receivedProductEvent(textMessage: TextMessage) {
        val snsMessage: SnsMessage = mapper.readValue(textMessage.text, SnsMessage::class.java)
        val envelope: Envelope = mapper.readValue(snsMessage.message, Envelope::class.java)

        val event = mapper.readValue(envelope.data, ProductEvent::class.java)

        logger.info(
            "Product event received - Event: {} - ProductId: {} -",
            envelope.eventType, event.productId
        )

        val eventLog = buildProductEventLog(envelope, event)
        repository.save(eventLog)

        textMessage.acknowledge()
    }

    private fun buildProductEventLog(envelope: Envelope, event: ProductEvent): ProductEventLog {
        val timestamp = Instant.now().epochSecond

        return ProductEventLog(
            id = ProductEventKey(pk = event.code, sk = "${envelope.eventType}_$timestamp"),
            eventType = envelope.eventType,
            productId = event.productId,
            username = event.username,
            timestamp = timestamp,
            ttl = Instant.now().plus(Duration.ofMinutes(10)).epochSecond
        )
    }


}