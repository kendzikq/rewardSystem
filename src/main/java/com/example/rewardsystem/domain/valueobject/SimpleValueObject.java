package com.example.rewardsystem.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public abstract class SimpleValueObject<T> implements Serializable {

    private final T value;

    protected SimpleValueObject(T value) {
        if (value == null) {
            throw new RuntimeException(String.format("Could not instantiate %s with a null value", getClass()));
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
}
