package com.bluemsun.entity;

import lombok.Data;

@Data
public class Talk{
    private Integer id;
    private Integer candidateId;
    private Integer judgeId;
    private Double score1;
    private Double score2;
    private Double score3;
    private Double score4;
    private Double scoreTotal; // 总分

    public void setScores(Double[] ids){
        setScore1(ids[0]);
        setScore2(ids[1]);
        setScore3(ids[2]);
        setScore4(ids[3]);

        setScoreTotal(score1 + score2 + score3 + score4);
    }

    public Talk(Integer id, Integer candidateId, Integer judgeId, Double score1, Double score2, Double score3, Double score4, Double scoreTotal) {
        this.id = id;
        this.candidateId = candidateId;
        this.judgeId = judgeId;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
        this.scoreTotal = scoreTotal;
    }

    public Talk(Integer candidateId, Integer judgeId, Double score1, Double score2, Double score3, Double score4) {
        this.candidateId = candidateId;
        this.judgeId = judgeId;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
    }

    public Talk(Integer id, Integer candidateId, Integer judgeId, Double score1, Double score2, Double score3, Double score4) {
        this.id = id;
        this.candidateId = candidateId;
        this.judgeId = judgeId;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
    }

    public Talk() {
    }
}
