package github.com.hugovallada.sqs.service

import com.fasterxml.jackson.databind.ObjectMapper
import github.com.hugovallada.sqs.model.Envelope
import github.com.hugovallada.sqs.model.ProductEvent
import github.com.hugovallada.sqs.model.SnsMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service
import javax.jms.TextMessage

@Service
class ProductEventConsumer(private val mapper: ObjectMapper) {
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
    }

}