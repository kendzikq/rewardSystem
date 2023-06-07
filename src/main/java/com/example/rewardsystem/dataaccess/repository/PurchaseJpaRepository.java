package com.example.rewardsystem.dataaccess.repository;

import com.example.rewardsystem.dataaccess.entity.PurchaseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PurchaseJpaRepository extends JpaRepository<PurchaseJpaEntity, Long> {

    Collection<PurchaseJpaEntity> findAllByUser(Long user);

}
