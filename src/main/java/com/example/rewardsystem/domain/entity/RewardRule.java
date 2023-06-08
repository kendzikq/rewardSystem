package com.example.rewardsystem.domain.entity;

import com.example.rewardsystem.domain.valueobject.Purchase;

abstract class RewardRule {

    private RewardRule next;

    public RewardRule linkWith(RewardRule next) {
        this.next = next;
        return next;
    }

    abstract long calculateReward(Purchase purchase);

    public long checkNextRule(Purchase purchase) {
        if (next == null) {
            return 0;
        }
        return next.calculateReward(purchase);
    }
}
