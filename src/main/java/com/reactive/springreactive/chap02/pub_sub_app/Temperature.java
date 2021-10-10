package com.reactive.springreactive.chap02.pub_sub_app;

public final class Temperature {

    private final Double value;

    public Temperature(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
