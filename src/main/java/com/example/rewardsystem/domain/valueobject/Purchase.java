package com.example.rewardsystem.domain.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Purchase {

    private final Item item;
    private final Amount amount;

}
