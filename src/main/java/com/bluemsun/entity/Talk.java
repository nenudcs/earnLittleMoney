package com.bluemsun.entity;

import lombok.Data;

@Data
public class Talk{
    private Integer id;
    private Integer candidateId;
    private Integer judgeId;
    private Double score1;
    private Double scoreTotal; // 总分

    public void setScores(Double[] ids){
        setScore1(ids[0]);

        setScoreTotal(score1);
    }

    public Talk(Integer id, Integer candidateId, Integer judgeId, Double score1, Double scoreTotal) {
        this.id = id;
        this.candidateId = candidateId;
        this.judgeId = judgeId;
        this.score1 = score1;
        this.scoreTotal = scoreTotal;
    }

    public Talk() {
    }
}
