package github.com.hugovallada.sqs.model

import github.com.hugovallada.sqs.enums.EventType

data class Envelope(
    val eventType: EventType,
    val data: String
)