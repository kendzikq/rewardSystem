package com.example.rewardsystem.web;


import com.example.rewardsystem.domain.applicationservice.RetailApplicationService;
import com.example.rewardsystem.web.dto.CreatePurchaseRequest;
import com.example.rewardsystem.web.dto.ResponseRewardMode;
import com.example.rewardsystem.web.dto.RewordResponse;
import com.example.rewardsystem.web.dto.UpdatePurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/retail")
@RestController
@RequiredArgsConstructor
public class RetailController {

    private final RetailApplicationService rewardApplicationService;

    @GetMapping("/reward/user/{userId}")
    RewordResponse getReward(
            @PathVariable long userId,
            @RequestParam(name = "mode") ResponseRewardMode responseRewardMode
    ) {
        return rewardApplicationService.getRewardScore(userId, responseRewardMode);
    }

    @PostMapping("/purchase/user/{userId}")
    void createPurchase(
            @PathVariable long userId,
            @RequestBody CreatePurchaseRequest createPurchaseRequest
    ) {
        rewardApplicationService.createPurchase(userId, createPurchaseRequest);
    }

    @PutMapping("/purchase/{purchaseId}")
    void updatePurchase(
            @PathVariable long purchaseId,
            @RequestBody UpdatePurchaseRequest updatePurchaseRequest
    ) {
        rewardApplicationService.updatePurchase(purchaseId, updatePurchaseRequest);
    }

}
