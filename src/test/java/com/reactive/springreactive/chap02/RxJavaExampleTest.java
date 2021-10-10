package com.reactive.springreactive.chap02;

import io.reactivex.Observable;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class RxJavaExampleTest {

    @Test
    void simpleRxJavaWorkFlow() {
        Observable<String> observable = Observable.create(
            sub -> {
                sub.onNext("Hello, Reactive World!");
                sub.onComplete();
            }
        );
    }

    @Test
    void simpleRxJavaWorkflowWithLambdas() {
        Observable.create(
            sub -> {
                sub.onNext("Hello, Reactive World!");
                sub.onComplete();
            }
        ).subscribe(
            System.out::println,
            System.out::println,
            () -> System.out.println("Done!")
        );
    }

    @Test
    void creatingRxStreams() {
        Observable.just("1", "2", "3", "4");
        Observable.fromArray(new String[]{"A", "B", "C"});
        Observable.fromIterable(Collections.emptyList());

        Observable<String> hello = Observable.fromCallable(() -> "Hello");
        Future<String> future = Executors.newCachedThreadPool().submit(() -> "World");
        Observable<String> world = Observable.fromFuture(future);
        Observable.concat(hello, world, Observable.just("!")).forEach(System.out::print);
    }

    // 비동기로 다른 thread 에서 실행하므로 main thread 재우지 않으면 그냥 끝나버림
    @Test
    void creatingAsyncSequence() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe(e -> System.out.println("Received : " + e));
        Thread.sleep(5000);
    }

    @Test
    void zipOperation() {
        Observable.zip(
            Observable.just("A", "B", "C"),
            Observable.just("1", "2", "3"),
            (x, y) -> x + y
        ).forEach(System.out::println);
    }


}
