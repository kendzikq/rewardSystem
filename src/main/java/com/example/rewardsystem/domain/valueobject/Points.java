package com.example.rewardsystem.domain.valueobject;

public class Points extends SimpleValueObject<Integer> {

    public static Points ZERO = new Points(0);

    public Points(Integer value) {
        super(value);
    }
}
