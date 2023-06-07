package com.example.rewardsystem.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RewordResponse {

    private Map<String, Long> pointsPerMonth;
    private Long totalPoints;


    public RewordResponse(Map<String, Long> pointsPerMonth) {
        this.pointsPerMonth = pointsPerMonth;
    }

    public RewordResponse(Long totalPoints) {
        this.totalPoints = totalPoints;
    }
}
