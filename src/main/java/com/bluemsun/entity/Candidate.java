package com.bluemsun.entity;

import lombok.Data;

@Data
public class Candidate {
    private Integer id;
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

    public Candidate() {
    }

    public Candidate(Integer id, String name, Integer num, Double score_1, Double score_2, Double score_2_origin, Double score_3, Double score_4, Double score_half, Double score_total, Integer hall_id, Integer promote, Integer groupId) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.score_1 = score_1;
        this.score_2 = score_2;
        this.score_2_origin = score_2_origin;
        this.score_3 = score_3;
        this.score_4 = score_4;
        this.score_half = score_half;
        this.score_total = score_total;
        this.hall_id = hall_id;
        this.promote = promote;
        this.groupId = groupId;
    }

    public Candidate(Integer id, String name, Integer num, Double score_1, Double score_2, Double score_2_origin, Double score_3, Double score_4, Double score_half, Double score_total, Integer hall_id, Integer promote) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.score_1 = score_1;
        this.score_2 = score_2;
        this.score_2_origin = score_2_origin;
        this.score_3 = score_3;
        this.score_4 = score_4;
        this.score_half = score_half;
        this.score_total = score_total;
        this.hall_id = hall_id;
        this.promote = promote;
    }

    public Candidate(String name, Integer num, Double score_1, Double score_2, Double score_3, Double score_4, Double score_half, Double score_total, Integer hall_id, Integer promote) {
        this.name = name;
        this.num = num;
        this.score_1 = score_1;
        this.score_2 = score_2;
        this.score_3 = score_3;
        this.score_4 = score_4;
        this.score_half = score_half;
        this.score_total = score_total;
        this.hall_id = hall_id;
        this.promote = promote;
    }

    public Candidate(Integer id, String name, Integer num, Double score_1, Double score_2, Double score_3, Double score_4, Double score_half, Double score_total, Integer hall_id, Integer promote) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.score_1 = score_1;
        this.score_2 = score_2;
        this.score_3 = score_3;
        this.score_4 = score_4;
        this.score_half = score_half;
        this.score_total = score_total;
        this.hall_id = hall_id;
        this.promote = promote;
    }
}
