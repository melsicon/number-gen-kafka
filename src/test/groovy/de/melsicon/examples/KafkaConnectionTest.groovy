package de.melsicon.examples

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import jakarta.inject.Inject
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.spock.Testcontainers
import org.testcontainers.utility.DockerImageName
import spock.lang.Specification

import static java.util.concurrent.TimeUnit.SECONDS
import static org.awaitility.Awaitility.await

@MicronautTest
@Testcontainers
class KafkaConnectionTest extends Specification implements TestPropertyProvider{

    static final KafkaContainer kafka;

    @Inject
    KafkaTopicProducer kafkaTopicProducer

    static {
        kafka = new KafkaContainer(
                DockerImageName.parse("confluentinc/cp-kafka:latest"));
        kafka.start()
    }


    def "testIfConsumerReceivesNumber"() {
        given:
        var counter = "12"
        var number = "16"
        when:

        kafkaTopicProducer.sendProduct("" + counter, "" + number);
        then:
        await().atMost(5, SECONDS).until(() -> !TestConsumer.received.isEmpty());
        TestConsumer.received.getFirst()=="16"
    }

    @Override
    Map<String, String> getProperties() {
        return Collections.singletonMap(
                "kafka.bootstrap.servers", kafka.getBootstrapServers()
        )
    }
}
