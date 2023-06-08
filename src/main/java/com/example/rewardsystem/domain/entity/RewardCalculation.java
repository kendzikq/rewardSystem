package com.example.rewardsystem.domain.entity;

import com.example.rewardsystem.domain.valueobject.Purchase;
import com.example.rewardsystem.domain.valueobject.RewardCalculationId;

import java.util.Collection;

public class RewardCalculation extends AggregateRoot<RewardCalculationId> {

    private long points;

    public void calculatePoints(
            Collection<Purchase> purchases
    ) {
        var rulesChain = new RewardRulesVerificationChain(
                new SecondLevelRewardRule(),
                new FirstLevelRewardRule()
        );

        points = purchases.stream()
                .map(rulesChain::calculateReward)
                .mapToLong(p -> p)
                .sum();
    }

    public long returnPoints() {
        return points;
    }
}
