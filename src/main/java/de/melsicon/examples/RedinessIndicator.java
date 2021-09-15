package de.melsicon.examples;

import io.micronaut.health.HealthStatus;
import io.micronaut.management.health.indicator.HealthIndicator;
import io.micronaut.management.health.indicator.HealthResult;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

//@Singleton
//@Liveness
public class RedinessIndicator implements HealthIndicator {

    @Override
    public Publisher<HealthResult> getResult() {
        return Flowable.just(HealthResult.builder("readiness").status(HealthStatus.UP).build());
    }
}
