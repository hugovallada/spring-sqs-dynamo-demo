package github.com.hugovallada.sqs.config

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import github.com.hugovallada.sqs.repository.ProductEventLogRepository
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = [ProductEventLogRepository::class])
class DynamoConfig {
    @Value("\${aws.region}")
    private lateinit var region: String

    @Bean
    @Primary
    fun dynamoMapperConfig(): DynamoDBMapperConfig = DynamoDBMapperConfig.DEFAULT

    @Bean
    @Primary
    fun dynamoMapper(amazonDynamoDB: AmazonDynamoDB, config: DynamoDBMapperConfig): DynamoDBMapper =
        DynamoDBMapper(amazonDynamoDB, config)


    @Bean
    @Primary
    fun amazonDynamoDb(): AmazonDynamoDB = AmazonDynamoDBClientBuilder
        .standard()
        .withCredentials(DefaultAWSCredentialsProviderChain())
        .withRegion(region)
        .build()
}