package com.bluemsun.entity.dto;

import lombok.Data;

/**
 * 用于返回某个选手评委打分情况
 */
@Data
public class JudgeAdminDto {
    private Integer hallId; // 会场
    private Integer turn;   // 活动
    private Integer candidateId; // 选手id
    private Integer judgeAllNum;    // 评委总数
    private Integer judgedNum;  // 已评分评委数量

    public JudgeAdminDto() {
    }

    public JudgeAdminDto(Integer hallId, Integer turn, Integer candidateId, Integer judgeAllNum, Integer judgedNum) {
        this.hallId = hallId;
        this.turn = turn;
        this.candidateId = candidateId;
        this.judgeAllNum = judgeAllNum;
        this.judgedNum = judgedNum;
    }
}
