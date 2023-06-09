package com.example.rewardsystem.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Amount extends SimpleValueObject<BigDecimal> implements Comparable<Amount> {

    public static final Amount ZERO = new Amount(0);

    public Amount(BigDecimal value) {
        super(setAmountScale(value));
    }

    public Amount(double value) {
        this(setAmountScale(BigDecimal.valueOf(value)));
    }

    @Override
    public int compareTo(Amount amount) {
        return getValue().compareTo(amount.getValue());
    }

    public Amount subtract(Amount amount) {
        return new Amount(getValue().subtract(amount.getValue()));
    }

    public Amount add(Amount amount) {
        return new Amount(getValue().add(amount.getValue()));
    }

    public Amount multiply(int multiplier) {
        return new Amount(getValue().multiply(BigDecimal.valueOf(multiplier)));
    }

    public Amount min(Amount min) {
        if (this.compareTo(min) < 0) {
            return min;
        }
        return this;
    }

    public Amount max(Amount max) {
        if (this.compareTo(max) > 0) {
            return max;
        }
        return this;
    }

    public Amount resetDecimal() {
        return new Amount(this.getValue().setScale(0, RoundingMode.DOWN));
    }

    private static BigDecimal setAmountScale(BigDecimal bigDecimal) {
        return bigDecimal.setScale(2, RoundingMode.HALF_DOWN);
    }
}
