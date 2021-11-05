package de.melsicon.examples

import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import java.util.concurrent.ConcurrentLinkedDeque
import static io.micronaut.configuration.kafka.annotation.OffsetReset.EARLIEST

@KafkaListener(offsetReset = EARLIEST)
class TestConsumer {

        static final Collection<String> received = new ConcurrentLinkedDeque<>()

        @Topic("random-number-1-v1")
        void readTopic(String randomNumber) {
            received.add(randomNumber)
    }
}
