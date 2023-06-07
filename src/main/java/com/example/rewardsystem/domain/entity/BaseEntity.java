package com.example.rewardsystem.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public abstract class BaseEntity<ID> {
    @Getter
    @Setter
    private ID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
