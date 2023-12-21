package com.bluemsun.utils;

import com.bluemsun.entity.Candidate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 谈心谈话的选手顺序
public class TalkComparator implements Comparator<Candidate> {
    private List<String> order = Arrays.asList(
            "翁灏",
            "陈欣",
            "周思瑞",
            "曾艳",
            "方鑫",
            "刘咏梅",
            "李娜",
            "刘雨婷",
            "李思逸",
            "赵睿",
            "张婧宁",
            "王翘楚",
            "薛冰",
            "周帅",
            "王珺",
            "于智雯",
            "王悦人",
            "杨帅",
            "李靖雯",
            "孙艺铭",
            "张东阳",
            "白天伟",
            "臧博",
            "李星喆",
            "毕铭",
            "刘莹",
            "张震之",
            "姚佳",
            "孙羽"

    );

    @Override
    public int compare(Candidate o1, Candidate o2) {
        return order.indexOf(o1.getName()) - order.indexOf(o2.getName());
    }
}
