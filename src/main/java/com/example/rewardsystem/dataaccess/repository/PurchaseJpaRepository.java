package com.example.rewardsystem.dataaccess.repository;

import com.example.rewardsystem.dataaccess.entity.PurchaseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseJpaRepository extends JpaRepository<PurchaseJpaEntity, Long> {

    @Query("""
            SELECT entity FROM PurchaseJpaEntity entity
            WHERE entity.user.id = :userId
            ORDER BY entity.date DESC
            """)
    List<PurchaseJpaEntity> findAllByUser(@Param("userId") Long userId);

}
