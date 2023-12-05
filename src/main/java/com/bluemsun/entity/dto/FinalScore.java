package com.bluemsun.entity.dto;

import lombok.Data;

@Data
public class FinalScore {
    private Integer key;    // 选手编号（主键）
    private Integer num;    // 选手序号
    private Double caseHigh; // 案例讨论最高分
    private Double caseLow; // 案例讨论最低分
    private Double caseScore; // 案例讨论总分
    private Double heartHigh; //
    private Double heartLow;
    private Double heartScore;
    private Double theoryHigh;
    private Double theoryLow;
    private Double theoryScore;
    private Double score;

    public FinalScore(Integer num, Double caseHigh, Double caseLow, Double caseScore, Double heartHigh, Double heartLow, Double heartScore, Double theoryHigh, Double theoryLow, Double theoryScore, Double score) {
        this.num = num;
        this.caseHigh = caseHigh;
        this.caseLow = caseLow;
        this.caseScore = caseScore;
        this.heartHigh = heartHigh;
        this.heartLow = heartLow;
        this.heartScore = heartScore;
        this.theoryHigh = theoryHigh;
        this.theoryLow = theoryLow;
        this.theoryScore = theoryScore;
        this.score = score;
    }

    public FinalScore(Integer key, Integer num, Double caseHigh, Double caseLow, Double caseScore, Double heartHigh, Double heartLow, Double heartScore, Double theoryHigh, Double theoryLow, Double theoryScore, Double score) {
        this.key = key;
        this.num = num;
        this.caseHigh = caseHigh;
        this.caseLow = caseLow;
        this.caseScore = caseScore;
        this.heartHigh = heartHigh;
        this.heartLow = heartLow;
        this.heartScore = heartScore;
        this.theoryHigh = theoryHigh;
        this.theoryLow = theoryLow;
        this.theoryScore = theoryScore;
        this.score = score;
    }
}
