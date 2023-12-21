package com.bluemsun.utils;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.dto.CandidateDetailScore;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 案例讨论选手顺序
public class CaseComparator implements Comparator<Candidate> {
    private List<String> order = Arrays.asList(
            "薛冰",
            "张东阳",
            "周帅",
            "赵睿",
            "白天伟",
            "方鑫",
            "姚佳",
            "周思瑞",
            "刘咏梅",
            "陈欣",
            "李思逸",
            "李娜",
            "刘雨婷",
            "于智雯",
            "张婧宁",
            "毕铭"

    );


    @Override
    public int compare(Candidate o1, Candidate o2) {
        return order.indexOf(o1.getName()) - order.indexOf(o2.getName());
    }

    public int compare(CandidateDetailScore o1, CandidateDetailScore o2) {
        return order.indexOf(o1.getName()) - order.indexOf(o2.getName());
    }
}
