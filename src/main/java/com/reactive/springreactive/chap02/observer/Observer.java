package com.reactive.springreactive.chap02.observer;

public interface Observer<T> {

    void observe(T event);
}
