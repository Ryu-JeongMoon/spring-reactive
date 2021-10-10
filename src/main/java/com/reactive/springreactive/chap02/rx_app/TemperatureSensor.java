package com.reactive.springreactive.chap02.rx_app;

import io.reactivex.Observable;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class TemperatureSensor {

    private final Random random = new Random();

    private final Observable<Temperature> dataStream =
        Observable.range(0, Integer.MAX_VALUE)
            .concatMap(tick ->
                Observable.just(tick)
                    .delay(random.nextInt(5000), TimeUnit.MILLISECONDS)
                    .map(tickValue -> this.probe()))
            .publish()
            .refCount();

    private Temperature probe() {
        return new Temperature(16 + random.nextGaussian() * 10);
    }

    public Observable<Temperature> temperatureStream() {
        return dataStream;
    }
}
