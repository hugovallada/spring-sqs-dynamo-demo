package github.com.hugovallada.sqs.config

import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.DefaultAwsRegionProviderChain
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import javax.jms.Session

@Configuration
@EnableJms
class JMSConfig {
    @Value("\${aws.region}")
    lateinit var region: String

    private lateinit var sqsConnectionFactory: SQSConnectionFactory

    @Bean
    fun jmsListenerContainerFactory(): DefaultJmsListenerContainerFactory {
        sqsConnectionFactory = SQSConnectionFactory(
            ProviderConfiguration(), AmazonSQSClientBuilder.standard()
                .withRegion(region)
                .withCredentials(DefaultAWSCredentialsProviderChain())
                .build()
        )

        return DefaultJmsListenerContainerFactory().apply {
            setConnectionFactory(sqsConnectionFactory)
            setConcurrency("2")
            setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE)
        }
    }


}