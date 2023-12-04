package com.bluemsun;

import com.bluemsun.dao.CandidateDao;
import com.bluemsun.dao.CaseDiscussionDao;
import com.bluemsun.entity.Judge;
import com.bluemsun.entity.Score;
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

    @Autowired
    CaseDiscussionDao caseDiscussionDao;
    @Test
    public void loginTest(){
        System.out.println(judgeService.login(new Judge("111", "111")));
    }

    @Test
    public void getAllTest(){
        System.out.println(candidateService.getaAllCandidate());
    }

    @Test
    public void getOne(){
        System.out.println(candidateService.getOne(1));
    }

    @Test
    public void insetOne(){
        Double[] s = {75.0, 88.0, 1.0, 2.0, 3.0};
        System.out.println(judgeService.score(new Score(3, 1, 3, s)));


    }

    @Test
    public void getJudedNum(){
        System.out.println(judgeService.isDone(4, 1));
    }

    @Test
    public void testGetAllNum(){
        System.out.println(caseDiscussionDao.selectAllNum(1));
    }
}
