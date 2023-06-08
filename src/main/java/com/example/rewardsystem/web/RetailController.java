package com.example.rewardsystem.web;


import com.example.rewardsystem.domain.applicationservice.RetailApplicationService;
import com.example.rewardsystem.web.dto.CreatePurchaseResponse;
import com.example.rewardsystem.web.dto.PurchaseRequest;
import com.example.rewardsystem.web.dto.ResponseRewardMode;
import com.example.rewardsystem.web.dto.RewordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "/retail")
@RestController
@RequiredArgsConstructor
public class RetailController {

    private final RetailApplicationService rewardApplicationService;

    @GetMapping("/reward/user/{userId}")
    ResponseEntity<RewordResponse> getReward(
            @PathVariable long userId,
            @RequestParam(name = "mode") ResponseRewardMode mode
    ) {
        return rewardApplicationService.getRewardScore(userId, mode);
    }

    @PostMapping("/purchase/user/{userId}")
    ResponseEntity<CreatePurchaseResponse> createPurchase(
            @PathVariable long userId,
            @RequestBody @Valid PurchaseRequest request
    ) {
        return rewardApplicationService.createPurchase(userId, request);
    }

    @PutMapping("/purchase/{purchaseId}")
    ResponseEntity<Void> updatePurchase(
            @PathVariable long purchaseId,
            @RequestBody @Valid PurchaseRequest request
    ) {
        return rewardApplicationService.updatePurchase(purchaseId, request);
    }

}
