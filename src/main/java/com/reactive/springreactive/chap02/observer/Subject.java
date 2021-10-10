package com.reactive.springreactive.chap02.observer;

public interface Subject<T> {

    void registerObserver(Observer<T> observer);
    void unregisterObserver(Observer<T> observer);
    void notifyToObservers(T event);
}
