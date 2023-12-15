package com.bluemsun.entity;

import lombok.Data;

@Data
public class CaseDiscussion{
    private Integer id;
    private Integer candidateId;
    private Integer judgeId;
    private Double score1;  //  提问分数
    private Double score2;  //  作答分数
    private Double scoreTotal;  // 总分
    private Integer isConfirmed;//提交，默认为0，提交为1

    public void setScores(Double[] ids){
        setScore1(ids[0]);
        setScore2(ids[1]);
        setScoreTotal(score1 + score2);
    }

    public CaseDiscussion(Integer id, Integer candidateId, Integer judgeId, Double score1, Double score2, Double scoreTotal) {
        this.id = id;
        this.candidateId = candidateId;
        this.judgeId = judgeId;
        this.score1 = score1;
        this.score2 = score2;
        this.scoreTotal = scoreTotal;
    }

    public CaseDiscussion(Integer candidateId, Integer judgeId, Double score1, Double score2) {
        this.candidateId = candidateId;
        this.judgeId = judgeId;
        this.score1 = score1;
        this.score2 = score2;
    }

    public CaseDiscussion(Integer id, Integer candidateId, Integer judgeId, Double score1, Double score2) {
        this.id = id;
        this.candidateId = candidateId;
        this.judgeId = judgeId;
        this.score1 = score1;
        this.score2 = score2;
    }

    public CaseDiscussion() {
    }
}
