package com.example.rewardsystem.domain.mapper;

import com.example.rewardsystem.dataaccess.entity.PurchaseJpaEntity;
import com.example.rewardsystem.domain.valueobject.Purchase;
import com.example.rewardsystem.domain.valueobject.SimpleValueObject;
import com.example.rewardsystem.web.dto.PurchaseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RetailMapper {

    @Mapping(target = "amount", expression = "java( new com.example.rewardsystem.domain.valueobject.Amount(entity.getAmount()) )")
    Purchase map(PurchaseJpaEntity entity);

    List<Purchase> map(List<PurchaseJpaEntity> entity);

    PurchaseJpaEntity map(PurchaseRequest request);

    void mapUpdate(PurchaseRequest request, @MappingTarget PurchaseJpaEntity purchaseJpaEntity);

    default <T> T convertToValue(SimpleValueObject<T> valueObject) {
        return valueObject == null ? null : valueObject.getValue();
    }

    default <V extends SimpleValueObject<T>, T> V convertToSimpleValueObject(T value, @TargetType Class<V> valueObjectClass) {
        if (value != null) {
            try {
                return valueObjectClass.getDeclaredConstructor(value.getClass()).newInstance(value);
            } catch (ReflectiveOperationException ex) {
                throw new RuntimeException(String.format("Could not convert %s into %s", value.getClass(), valueObjectClass), ex);
            }
        }
        return null;
    }
}
