package com.example.rewardsystem.domain.applicationservice;

import com.example.rewardsystem.dataaccess.entity.PurchaseJpaEntity;
import com.example.rewardsystem.dataaccess.repository.PurchaseJpaRepository;
import com.example.rewardsystem.dataaccess.repository.UserJpaRepository;
import com.example.rewardsystem.domain.entity.RewardCalculation;
import com.example.rewardsystem.domain.mapper.RetailMapper;
import com.example.rewardsystem.web.dto.CreatePurchaseResponse;
import com.example.rewardsystem.web.dto.PurchaseRequest;
import com.example.rewardsystem.web.dto.ResponseRewardMode;
import com.example.rewardsystem.web.dto.RewordResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetailApplicationService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM-yyyy");

    private final PurchaseJpaRepository purchaseJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final RetailMapper mapper;


    public ResponseEntity<RewordResponse> getRewardScore(
            long userId, ResponseRewardMode mode
    ) {
        if (!isUserValid(userId)) {
            log.warn("user: {} not found", userId);
            return ResponseEntity.notFound().build();
        }

        var purchases = purchaseJpaRepository.findAllByUser(userId);
        return switch (mode) {
            case TOTAL -> ResponseEntity.ok(new RewordResponse(getTotalRewardScore(purchases)));
            case MONTHLY -> ResponseEntity.ok(new RewordResponse(getMonthlyRewardScore(purchases)));
        };
    }

    @Transactional
    public ResponseEntity<CreatePurchaseResponse> createPurchase(
            long userId, PurchaseRequest request
    ) {
        if (!isUserValid(userId)) {
            log.warn("user: {} not found", userId);
            return ResponseEntity.notFound().build();
        }

        var userJpaEntity = userJpaRepository.findById(userId).orElseThrow();
        var purchaseJpaEntity = mapper.map(request);
        purchaseJpaEntity.setUser(userJpaEntity);
        purchaseJpaRepository.save(purchaseJpaEntity);

        log.info("purchase: {} created for a user: {} ", request, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreatePurchaseResponse(purchaseJpaEntity.getId()));
    }

    @Transactional
    public ResponseEntity<Void> updatePurchase(
            long purchaseId, PurchaseRequest request
    ) {
        var purchaseJpaEntity = purchaseJpaRepository.findById(purchaseId);
        if (purchaseJpaEntity.isEmpty()) {
            log.warn("purchase: {} not found", purchaseId);
            return ResponseEntity.notFound().build();
        }
        mapper.mapUpdate(request, purchaseJpaEntity.get());
        log.info("purchase: {} updated for purchaseId : {} ", request, purchaseId);
        return ResponseEntity.status(HttpStatus.OK).build();

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

    private boolean isUserValid(long userId) {
        return this.userJpaRepository.findById(userId).isPresent();
    }
}
