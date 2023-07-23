package github.com.hugovallada.sqs.repository

import github.com.hugovallada.sqs.model.ProductEventKey
import github.com.hugovallada.sqs.model.ProductEventLog
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface ProductEventLogRepository : CrudRepository<ProductEventLog, ProductEventKey> {

    fun findAllByPk(code: String): List<ProductEventLog>

    fun findAllByPkAndSkStartsWith(code: String, eventType: String): List<ProductEventLog>

}