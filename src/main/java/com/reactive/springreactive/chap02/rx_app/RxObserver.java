package com.reactive.springreactive.chap02.rx_app;

public interface RxObserver<T>{

    void onNext(T next);
    void onComplete();
    void onError(Exception e);
}
