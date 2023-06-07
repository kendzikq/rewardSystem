package com.example.rewardsystem.domain.entity;

import com.example.rewardsystem.domain.valueobject.Amount;
import com.example.rewardsystem.domain.valueobject.Purchase;
import com.example.rewardsystem.domain.valueobject.RewardCalculationId;

import java.util.Collection;

public class RewardCalculation extends AggregateRoot<RewardCalculationId> {

    private static final Amount ZERO_AMOUNT = Amount.ZERO;
    private static final Amount FIRST_LEVEL_AMOUNT_THRESHOLD = new Amount(50);
    private static final Amount SECOND_LEVEL_AMOUNT_THRESHOLD = new Amount(100);
    private static final int FIRST_LEVEL_MULTIPLIER = 1;
    private static final int SECOND_LEVEL_MULTIPLIER = 2;

    private long points;


    public void calculatePoints(
            Collection<Purchase> purchases
    ) {
        points = purchases.stream()
                .map(this::calculatePoints)
                .mapToLong(p -> p)
                .sum();
    }

    public long returnPoints() {
        return points;
    }

    private long calculatePoints(
            Purchase purchase
    ) {
        var amount = purchase.getAmount();

        var secondLevelAmount = amount
                .subtract(SECOND_LEVEL_AMOUNT_THRESHOLD)
                .min(ZERO_AMOUNT);

        var firstLevelAmount = amount
                .subtract(secondLevelAmount)
                .subtract(FIRST_LEVEL_AMOUNT_THRESHOLD)
                .min(ZERO_AMOUNT);

        return secondLevelAmount
                .multiply(SECOND_LEVEL_MULTIPLIER)
                .add(firstLevelAmount.multiply(FIRST_LEVEL_MULTIPLIER))
                .getValue()
                .longValue();
    }
}
