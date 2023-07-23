package github.com.hugovallada.sqs.model

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey

class ProductEventKey(
    @field:DynamoDBHashKey
    val pk: String,
    @field:DynamoDBRangeKey
    val sk: String
)