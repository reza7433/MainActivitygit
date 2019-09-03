package com.example;

public class SampleGenericClass<T> {

    private T sample;

    public void add(T sample) {
        this.sample = sample;

    }

    public T get() {
        return sample;

    }
}