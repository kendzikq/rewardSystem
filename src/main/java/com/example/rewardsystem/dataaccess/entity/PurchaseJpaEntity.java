package com.example.rewardsystem.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "PURCHASE")
@Getter
@Setter
public class PurchaseJpaEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_seq")
    @SequenceGenerator(name = "purchase_seq", sequenceName = "purchase_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "ITEM", nullable = false)
    private String item;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false, referencedColumnName = "id")
    private UserJpaEntity user;
}
