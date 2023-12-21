package com.bluemsun.utils;

import com.bluemsun.entity.Candidate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 案例讨论选手顺序
public class CaseComparator implements Comparator<Candidate> {
    private List<String> order = Arrays.asList(
            "孙羽",
            "张婧宁",
            "臧博",
            "张震之",
            "李星喆",
            "王珺",
            "曾艳",
            "孙艺铭",
            "方鑫",
            "刘莹",
            "王翘楚",
            "陈欣",
            "姚佳",
            "杨帅",
            "王悦人",
            "李思逸",
            "薛冰",
            "李娜",
            "周思瑞",
            "刘咏梅",
            "翁灏",
            "赵睿",
            "刘雨婷",
            "李靖雯",
            "于智雯",
            "周帅",
            "张东阳",
            "白天伟",
            "毕铭"
    );


    @Override
    public int compare(Candidate o1, Candidate o2) {
        return order.indexOf(o1.getName()) - order.indexOf(o2.getName());
    }
}
