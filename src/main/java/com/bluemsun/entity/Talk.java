package com.bluemsun.entity;

import lombok.Data;

@Data
public class Talk{
    private Integer id;
    private Integer candidateId;
    private Integer judgeId;
    private Double score1;
    private Double scoreTotal; // 总分
    private Integer isConfirmed;//提交，默认为0，提交为1

    public Talk(Integer id, Integer candidateId, Integer judgeId1, Double score1, Double scoreTotal, Integer isConfirmed) {
        this.id = id;
        this.candidateId = candidateId;
        this.judgeId = judgeId1;
        this.score1 = score1;
        this.scoreTotal = scoreTotal;
        this.isConfirmed = isConfirmed;
    }

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
