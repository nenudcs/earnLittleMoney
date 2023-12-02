package com.bluemsun;

import com.bluemsun.dao.CandidateDao;
import com.bluemsun.entity.Judge;
import com.bluemsun.service.CandidateService;
import com.bluemsun.service.JudgeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CounselorCompetitionApplicationTests {
    @Autowired
    JudgeService judgeService;
    @Autowired
    CandidateService candidateService;
    @Test
    public void loginTest(){
        System.out.println(judgeService.login(new Judge("111", "11")));
    }

    @Test
    public void getAllTest(){
        System.out.println(candidateService.getaAllCandidate());
    }

    @Test
    public void getOne(){
        System.out.println(candidateService.getOne(1));
    }
}
