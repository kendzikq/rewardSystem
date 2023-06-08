package com.example.rewardsystem.dataaccess.repository;

import com.example.rewardsystem.dataaccess.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
}
