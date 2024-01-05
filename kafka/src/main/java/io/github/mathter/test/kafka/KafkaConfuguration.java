package io.github.mathter.test.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

import java.util.Map;

@Configuration
public class KafkaConfuguration {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConfuguration.class);

    @Value("${app.kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Bean
    public AdminClient kafkaAdminClient() {
        return AdminClient.create(Map.of("bootstrap.servers", this.bootstrapServers));
    }

    @KafkaListener(
            id = "myId",
            topics = "test"
    )
    public void listner(String payload) {
        LOG.debug("Received: {}", payload);
    }
}
