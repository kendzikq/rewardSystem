package com.example.rewardsystem.domain.valueobject;

import java.util.UUID;

public class RewardCalculationId extends SimpleValueObject<UUID> {

    public static RewardCalculationId newInstance() {
        return new RewardCalculationId(UUID.randomUUID());
    }

    public RewardCalculationId(UUID value) {
        super(value);
    }

    public RewardCalculationId(String value) {
        this(UUID.fromString(value));
    }
}
