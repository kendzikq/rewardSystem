package com.example.rewardsystem.web;


import com.example.rewardsystem.domain.applicationservice.RetailApplicationService;
import com.example.rewardsystem.web.dto.CreatePurchaseRequest;
import com.example.rewardsystem.web.dto.RewordResponse;
import com.example.rewardsystem.web.dto.UpdatePurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/retail")
@RestController
@RequiredArgsConstructor
public class RetailController {

    private final RetailApplicationService rewardApplicationService;

    @GetMapping("/reward/user/{userId}")
    RewordResponse getReward(@PathVariable int userId) {
        return rewardApplicationService.getReward(userId);
    }

    @PostMapping("/purchase/user/{userId}")
    void createPurchase(
            @PathVariable int userId,
            @RequestBody CreatePurchaseRequest createPurchaseRequest
    ) {
        rewardApplicationService.createPurchase(userId, createPurchaseRequest);
    }

    @PutMapping("/purchase/{purchaseId}")
    void updatePurchase(
            @PathVariable int purchaseId,
            @RequestBody UpdatePurchaseRequest updatePurchaseRequest
    ) {
        rewardApplicationService.updatePurchase(purchaseId, updatePurchaseRequest);
    }

}
