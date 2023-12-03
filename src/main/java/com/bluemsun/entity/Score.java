package com.bluemsun.entity;

import lombok.Data;

@Data
public class Score {
    private Integer turn;   // 哪一个活动：1：基础知识，2：案例讨论，3：理论宣讲，4：谈心谈话
    private Integer candidateId;    // 选手id（主键）
    private Integer judgeId;
    private Double[] scores;   // 分数，数组
//    private Activity activity;    // 活动对象


    public Score(Integer turn, Integer candidateId, Integer judgeId, Double[] scores) {
        this.turn = turn;
        this.candidateId = candidateId;
        this.judgeId = judgeId;
        this.scores = scores;
    }

    public Score(Integer turn, Integer candidateId, Double[] scores) {
        this.turn = turn;
        this.candidateId = candidateId;
        this.scores = scores;
    }


    public Score() {
    }
}
