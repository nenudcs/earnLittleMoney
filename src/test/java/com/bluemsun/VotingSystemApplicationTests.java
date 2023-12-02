package com.bluemsun;

import com.bluemsun.entity.Judge;
import com.bluemsun.service.JudgeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VotingSystemApplicationTests {
    @Autowired
    JudgeService judgeService;
    @Test
    public void loginTest(){
        System.out.println(judgeService.login(new Judge("111", "11")));
    }
}
