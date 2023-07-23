package github.com.hugovallada.sqs.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy::class)
data class SnsMessage(
    val message: String,
    val type: String,
    val topicArn: String,
    val timestamp: String,
    val messageId: String
)
