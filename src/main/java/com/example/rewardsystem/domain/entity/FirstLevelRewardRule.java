package com.example.rewardsystem.domain.entity;

import com.example.rewardsystem.domain.valueobject.Amount;
import com.example.rewardsystem.domain.valueobject.Purchase;


class FirstLevelRewardRule extends RewardRule {

    private static final Amount FIRST_LEVEL_AMOUNT_THRESHOLD = new Amount(50);
    private static final Amount FIRST_LEVEL_MAX_VALUE = new Amount(100);
    private static final int FIRST_LEVEL_MULTIPLIER = 1;

    @Override
    long calculateReward(Purchase purchase) {
        var amount = purchase.getAmount();
        return amount
                .max(FIRST_LEVEL_MAX_VALUE)
                .subtract(FIRST_LEVEL_AMOUNT_THRESHOLD)
                .min(Amount.ZERO)
                .multiply(FIRST_LEVEL_MULTIPLIER)
                .getValue()
                .longValue()
                + super.checkNextRule(purchase);
    }
}
