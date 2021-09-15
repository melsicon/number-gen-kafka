package de.melsicon.examples;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface KafkaTopicProducer {

        @Topic("random-number-1-v1")
        void sendProduct(@KafkaKey String key, long value);
}
