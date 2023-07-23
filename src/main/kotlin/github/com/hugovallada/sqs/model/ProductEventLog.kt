package github.com.hugovallada.sqs.model

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum
import github.com.hugovallada.sqs.enums.EventType
import org.springframework.data.annotation.Id

@DynamoDBTable(tableName = "product-events")
class ProductEventLog(
    @field:Id
    val id: ProductEventKey,
    val ttl: Long,
    val productId: Long,
    val username: String,
    val timestamp: Long,
    @field:DynamoDBTypeConvertedEnum
    val eventType: EventType
)