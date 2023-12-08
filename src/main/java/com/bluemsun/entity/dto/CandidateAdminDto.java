package com.bluemsun.entity.dto;

import lombok.Data;

/**
 *  用于返回有多少选手已经被打完分，
 */
@Data
public class CandidateAdminDto {
    private Integer hallId;
    private Integer turn;
    private Integer candidateTotalNum;
    private Integer candidateOverNum;

    public CandidateAdminDto() {
    }

    public CandidateAdminDto(Integer hallId, Integer turn, Integer candidateTotalNum, Integer candidateOverNum) {
        this.hallId = hallId;
        this.turn = turn;
        this.candidateTotalNum = candidateTotalNum;
        this.candidateOverNum = candidateOverNum;
    }
}
