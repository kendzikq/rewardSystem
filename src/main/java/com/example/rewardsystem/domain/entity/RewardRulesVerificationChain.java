package com.example.rewardsystem.domain.entity;

import com.example.rewardsystem.domain.valueobject.Purchase;

class RewardRulesVerificationChain {

    private final RewardRule chain;

    RewardRulesVerificationChain(RewardRule initRule, RewardRule... rules) {
        if (rules.length == 0 || initRule == null) {
            throw new RuntimeException("Initializing chain with empty rules");
        }
        this.chain = initRule;
        for (var rule : rules) {
            this.chain.linkWith(rule);
        }
    }

    long calculateReward(Purchase purchase) {
        return chain.calculateReward(purchase);
    }
}
