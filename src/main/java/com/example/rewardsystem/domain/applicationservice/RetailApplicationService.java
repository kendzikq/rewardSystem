package com.example.rewardsystem.domain.applicationservice;

import com.example.rewardsystem.dataaccess.entity.PurchaseJpaEntity;
import com.example.rewardsystem.dataaccess.repository.PurchaseJpaRepository;
import com.example.rewardsystem.domain.entity.RewardCalculation;
import com.example.rewardsystem.domain.mapper.RetailMapper;
import com.example.rewardsystem.web.dto.CreatePurchaseRequest;
import com.example.rewardsystem.web.dto.ResponseRewardMode;
import com.example.rewardsystem.web.dto.RewordResponse;
import com.example.rewardsystem.web.dto.UpdatePurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetailApplicationService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM-yyyy");

    private final PurchaseJpaRepository purchaseJpaRepository;
    private final RetailMapper mapper;


    public RewordResponse getRewardScore(
            long userId, ResponseRewardMode responseRewardMode
    ) {
        var purchases = purchaseJpaRepository.findAllByUser(userId);
        return switch (responseRewardMode) {
            case MONTHLY -> new RewordResponse(getMonthlyRewardScore(purchases));
            case TOTAL -> new RewordResponse(getTotalRewardScore(purchases));
        };
    }

    private Long getTotalRewardScore(
            List<PurchaseJpaEntity> jpaPurchases
    ) {
        var rewardCalculation = new RewardCalculation();
        var purchases = mapper.map(jpaPurchases);
        rewardCalculation.calculatePoints(purchases);
        return rewardCalculation.returnPoints();
    }

    private Map<String, Long> getMonthlyRewardScore(
            Collection<PurchaseJpaEntity> purchases
    ) {
        Map<String, List<PurchaseJpaEntity>> purchasesPerDateMap = purchases.stream()
                .collect(Collectors.groupingBy(entity -> entity.getDate().format(DATE_TIME_FORMATTER)));

        return purchasesPerDateMap.entrySet()
                .stream()
                .map(entry -> {
                            var rewardCalculation = new RewardCalculation();
                            rewardCalculation.calculatePoints(mapper.map(entry.getValue()));
                            return Map.entry(
                                    entry.getKey(),
                                    rewardCalculation.returnPoints());
                        }
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void createPurchase(long userId, CreatePurchaseRequest createPurchaseRequest) {

    }

    public void updatePurchase(long purchaseId, UpdatePurchaseRequest updatePurchaseRequest) {

    }
}
