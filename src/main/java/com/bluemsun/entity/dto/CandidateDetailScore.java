package com.bluemsun.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class CandidateDetailScore {

    private Integer num;    // 选手序号
    private String name;
    private List scores;

    private Double high;
    private Double low;

    private Double total;


    public CandidateDetailScore(Integer num, String name, List scores, Double high, Double low, Double total) {
        this.num = num;
        this.name = name;
        this.scores = scores;
        this.high = high;
        this.low = low;
        this.total = total;
    }

    public CandidateDetailScore() {

    }
}