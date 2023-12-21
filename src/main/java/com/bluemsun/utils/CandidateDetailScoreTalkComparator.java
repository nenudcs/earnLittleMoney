package com.bluemsun.utils;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.dto.CandidateDetailScore;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CandidateDetailScoreTalkComparator implements Comparator<CandidateDetailScore> {
    private List<String> order = Arrays.asList(
            "张东阳",
            "李思逸",
            "张婧宁",
            "薛冰",
            "赵睿",
            "李娜",
            "方鑫",
            "白天伟",
            "刘咏梅",
            "毕铭",
            "于智雯",
            "周帅",
            "刘雨婷",
            "陈欣",
            "姚佳",
            "周思瑞"


    );


    public int compare(CandidateDetailScore o1, CandidateDetailScore o2) {
        return order.indexOf(o1.getName()) - order.indexOf(o2.getName());
    }
}
