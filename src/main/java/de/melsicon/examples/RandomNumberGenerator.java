package de.melsicon.examples;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@Singleton
@Requires(missingProperty="test")
public class RandomNumberGenerator {

    @Inject
    KafkaTopicProducer kafkaTopicProducer;

    @EventListener
    public void onStartupEvent(StartupEvent event) throws InterruptedException {
        Random rand = new Random();
        long counter = 1;
        while (true) {
            long number = rand.nextLong();
            log.info("{}. random number: {}", counter, number);
            kafkaTopicProducer.sendProduct("" + counter, number);
            counter++;
            Thread.sleep(5000);
        }
    }
}
