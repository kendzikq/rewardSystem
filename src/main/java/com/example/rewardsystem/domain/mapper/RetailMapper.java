package com.example.rewardsystem.domain.mapper;

import com.example.rewardsystem.dataaccess.entity.PurchaseJpaEntity;
import com.example.rewardsystem.domain.valueobject.Purchase;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RetailMapper {

    Purchase map(PurchaseJpaEntity entity);
    List<Purchase> map(List<PurchaseJpaEntity> entity);
}
