package com.bluemsun.entity;

import lombok.Data;

@Data
public class Show {
    private Integer turn; // 哪一个活动
    private Integer candidateId; // 选手id
    private Integer hallId; // 哪一个会场（只有案例讨论有）
    private Integer groupId; // 那一组（只有案例讨论有）

    public Show(Integer turn, Integer candidateId, Integer hallId, Integer groupId) {
        this.turn = turn;
        this.candidateId = candidateId;
        this.hallId = hallId;
        this.groupId = groupId;
    }

    public Show() {
    }
}
