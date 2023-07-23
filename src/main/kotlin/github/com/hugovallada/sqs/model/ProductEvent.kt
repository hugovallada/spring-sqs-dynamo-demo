package github.com.hugovallada.sqs.model

data class ProductEvent(
    val productId: Long,
    val code: String,
    val username: String
)
