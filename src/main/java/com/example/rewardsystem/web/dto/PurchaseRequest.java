
package com.example.rewardsystem.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class PurchaseRequest {
    @NotNull
    private String item;
    @NotNull
    @Positive
    private Double amount;
    @NotNull
    private LocalDate date;
}
