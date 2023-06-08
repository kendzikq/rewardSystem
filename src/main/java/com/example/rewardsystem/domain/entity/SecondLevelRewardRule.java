package com.example.rewardsystem.domain.entity;

import com.example.rewardsystem.domain.valueobject.Amount;
import com.example.rewardsystem.domain.valueobject.Purchase;

class SecondLevelRewardRule extends RewardRule {

    private static final Amount SECOND_LEVEL_AMOUNT_THRESHOLD = new Amount(100);
    private static final int SECOND_LEVEL_MULTIPLIER = 2;

    @Override
    long calculateReward(Purchase purchase) {
        var amount = purchase.getAmount();
        return amount
                .subtract(SECOND_LEVEL_AMOUNT_THRESHOLD)
                .min(Amount.ZERO)
                .multiply(SECOND_LEVEL_MULTIPLIER)
                .getValue()
                .longValue()
                + super.checkNextRule(purchase);
    }
}
