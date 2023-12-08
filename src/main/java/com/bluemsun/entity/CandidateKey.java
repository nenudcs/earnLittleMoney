package com.bluemsun.entity;

import lombok.Data;

/**
 * /admin/show请求中返回的id需要换成key
 */
@Data
public class CandidateKey {
    private Integer key;
    private String name;
    private Integer num;    // 序号
    private Double score_1; // 基础知识分数
    private Double score_2; // 案例讨论归一化分数
    private Double score_2_origin; // 案例讨论原始分数
    private Double score_3; // 理论宣讲分数
    private Double score_4; // 谈心谈话分数
    private Double score_half; // 初赛分数
    private Double score_total; // 决赛分数
    private Integer hall_id; // 会场编号，0或者1
    private Integer promote; // 是否晋级，0：未晋级，1：晋级
    private Integer groupId; // 属于那一组（数字相同的为一组）

    public CandidateKey(Candidate candidate) {
        key = candidate.getId();
        name = candidate.getName();
        num = candidate.getNum();
        score_1 = candidate.getScore_1();
        score_2 = candidate.getScore_2();
        score_3 = candidate.getScore_3();
        score_4 = candidate.getScore_4();
        score_half = candidate.getScore_half();
        score_total = candidate.getScore_total();
        hall_id = candidate.getHall_id();
        promote = candidate.getPromote();
        groupId = candidate.getGroupId();

    }

    public CandidateKey() {
    }
}
