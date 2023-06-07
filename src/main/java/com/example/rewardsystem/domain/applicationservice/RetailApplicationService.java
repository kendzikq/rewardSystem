package com.example.rewardsystem.domain.applicationservice;

import com.example.rewardsystem.web.dto.CreatePurchaseRequest;
import com.example.rewardsystem.web.dto.RewordResponse;
import com.example.rewardsystem.web.dto.UpdatePurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetailApplicationService {

    public RewordResponse getReward(int userId) {
        return null;
    }

    public void createPurchase(int userId, CreatePurchaseRequest createPurchaseRequest) {

    }

    public void updatePurchase(int purchaseId, UpdatePurchaseRequest updatePurchaseRequest) {

    }
}
