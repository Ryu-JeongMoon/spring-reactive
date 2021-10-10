package com.reactive.springreactive.chap02;

import static org.mockito.Mockito.times;

import com.reactive.springreactive.chap02.observer.ConcreteObserverA;
import com.reactive.springreactive.chap02.observer.ConcreteObserverB;
import com.reactive.springreactive.chap02.observer.ConcreteSubject;
import com.reactive.springreactive.chap02.observer.Subject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ConcreteSubjectTest {

    @Test
    void observerHandleEventsFromSubject() {
        Subject<String> subject = new ConcreteSubject();
        ConcreteObserverA observerA = Mockito.spy(new ConcreteObserverA());
        ConcreteObserverB observerB = Mockito.spy(new ConcreteObserverB());

        subject.notifyToObservers("No listeners");

        subject.registerObserver(observerA);
        subject.notifyToObservers("Message for A");

        subject.registerObserver(observerB);
        subject.notifyToObservers("Message for A & B");

        subject.unregisterObserver(observerA);
        subject.notifyToObservers("Message for B");

        subject.unregisterObserver(observerB);
        subject.notifyToObservers("No listeners");

        Mockito.verify(observerB, times(1)).observe("Message for A & B");
        Mockito.verify(observerB, times(1)).observe("Message for B");
        Mockito.verifyNoMoreInteractions(observerB);
    }

    @Test
    void subjectLeveragesLambdas() {
        Subject<String> subject = new ConcreteSubject();

        subject.registerObserver(e -> System.out.println("A : " + e));
        subject.registerObserver(e -> System.out.println("B : " + e));
        subject.notifyToObservers("Message for A & B");
    }
}