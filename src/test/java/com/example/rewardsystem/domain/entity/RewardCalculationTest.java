package com.example.rewardsystem.domain.entity;

import com.example.rewardsystem.domain.valueobject.Amount;
import com.example.rewardsystem.domain.valueobject.Item;
import com.example.rewardsystem.domain.valueobject.Purchase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RewardCalculationTest {

    private static final Item FRIDGE = new Item("FRIDGE");
    private static final Item TV = new Item("TV");
    private static final Item WASHING_MACHINE = new Item("WASHING_MACHINE");


    @Test
    void testCalculatePoints() {

        // given
        var rewardCalculation = new RewardCalculation();
        List<Purchase> purchases = new ArrayList<>();

        purchases.add(new Purchase(FRIDGE, new Amount(120)));
        purchases.add(new Purchase(TV, new Amount(80)));
        purchases.add(new Purchase(WASHING_MACHINE, new Amount(30)));

        // when
        rewardCalculation.calculatePoints(purchases);

        // then
        assertEquals(120, rewardCalculation.returnPoints());
    }

    @Test
    void testCalculatePointsWithEmptyPurchases() {

        //given
        var rewardCalculation = new RewardCalculation();
        List<Purchase> purchases = new ArrayList<>();

        // when
        rewardCalculation.calculatePoints(purchases);

        // then
        assertEquals(0, rewardCalculation.returnPoints());
    }

    @Test
    void testCalculatePointsWithZeroAmountPurchases() {

        // given
        var rewardCalculation = new RewardCalculation();
        List<Purchase> purchases = new ArrayList<>();

        purchases.add(new Purchase(FRIDGE, new Amount(0)));
        purchases.add(new Purchase(TV, new Amount(0)));
        purchases.add(new Purchase(WASHING_MACHINE, new Amount(0)));

        // when
        rewardCalculation.calculatePoints(purchases);

        // then
        assertEquals(0, rewardCalculation.returnPoints());
    }

    @ParameterizedTest
    @CsvSource({
            "30, 0",
            "50, 0",
            "51, 1",
            "49, 0",
            "100, 50",
            "101, 52",
            "99, 49",
            "120, 90",
            "50.01, 0",
            "50.99, 0",
            "100.01, 50",
            "100.99, 50",
    })
    void testCalculatePointsWithSinglePurchaseBelowThresholds(double purchaseAmount, long pointsResult) {

        // given
        var rewardCalculation = new RewardCalculation();
        List<Purchase> purchases = new ArrayList<>();

        purchases.add(new Purchase(FRIDGE, new Amount(purchaseAmount)));

        // when
        rewardCalculation.calculatePoints(purchases);

        // then
        assertEquals(pointsResult, rewardCalculation.returnPoints());
    }
}
