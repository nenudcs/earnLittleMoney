package com.bluemsun;

import com.auth0.jwt.interfaces.Claim;
import com.bluemsun.dao.CaseDiscussionDao;
import com.bluemsun.entity.Judge;
import com.bluemsun.entity.Score;
import com.bluemsun.entity.Show;
import com.bluemsun.service.AdminService;
import com.bluemsun.service.CandidateService;
import com.bluemsun.service.JudgeService;
import com.bluemsun.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class CounselorCompetitionApplicationTests {
    @Autowired
    JudgeService judgeService;
    @Autowired
    CandidateService candidateService;

    @Autowired
    AdminService adminService;

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

    @Test
    public void showTest(){
        Show show = new Show();
        show.setTurn(2);
//        show.setCandidateId(1);
        show.setGroupId(1);
        show.setHallId(1);
        System.out.println(adminService.showResult(show));


    }

    @Test
    public void testJwt(){
        Integer id = 1;
        try {
            String token = JWTUtils.createToken(id);
            Map<String, Claim> stringClaimMap = JWTUtils.verifyToken(token);
            Integer id2 = stringClaimMap.get("id").asInt();

            System.out.println(id2);
            long tokenExpireDate = JWTUtils.getRemainingMinutes(token);
            System.out.println(tokenExpireDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
