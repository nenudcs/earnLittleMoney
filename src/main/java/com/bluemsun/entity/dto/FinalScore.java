package com.bluemsun.entity.dto;

import lombok.Data;

@Data
public class FinalScore {
    private Integer key;    // 选手编号（主键）
    private Integer num;    // 选手序号
    private Double writtenScore; //笔试成绩
    private Double caseHigh; // 案例讨论最高分
    private Double caseLow; // 案例讨论最低分
    private Double caseScore; // 案例讨论总分
    private Integer hallId=1; // 会场ID
    private Double heartHigh; //
    private Double heartLow;
    private Double heartScore;
//    private Double theoryHigh;
//    private Double theoryLow;
//    private Double theoryScore;
    private Double score;

    public FinalScore(Integer key, Integer num, Double writtenScore, Double caseHigh, Double caseLow, Double caseScore, Integer hallId, Double heartHigh, Double heartLow, Double heartScore, Double score) {
        this.key = key;
        this.num = num;
        this.writtenScore = writtenScore;
        this.caseHigh = caseHigh;
        this.caseLow = caseLow;
        this.caseScore = caseScore;
        this.hallId = hallId;
        this.heartHigh = heartHigh;
        this.heartLow = heartLow;
        this.heartScore = heartScore;
        this.score = score;
    }

    public FinalScore() {

    }
}
