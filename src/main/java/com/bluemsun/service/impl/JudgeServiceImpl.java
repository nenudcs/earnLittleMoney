package com.bluemsun.service.impl;

import com.bluemsun.dao.CaseDiscussionDao;
import com.bluemsun.dao.JudgeDao;
import com.bluemsun.dao.TalkDao;
import com.bluemsun.dao.TheoreticalPresentationDao;
import com.bluemsun.entity.*;
import com.bluemsun.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Autowired
    JudgeDao judgeDao;

    @Autowired
    CaseDiscussionDao caseDiscussionDao;
    @Autowired
    TheoreticalPresentationDao theoreticalPresentationDao;
    @Autowired
    TalkDao talkDao;

    @Override
    public boolean login(Judge judge) {
        if("".equals(judge.getName()) || judge.getName()==null
        || "".equals(judge.getPassword()) || judge.getPassword()==null){
            return false;
        } else {
            Judge judge1 = judgeDao.selectOne(judge);
            if(judge1 != null) {
                judge.setId(judge1.getId());
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean score(Score score) {
        boolean result = false;
        if(score.getTurn() == 2){
            // 案例讨论
            CaseDiscussion caseDiscussion = new CaseDiscussion();
            caseDiscussion.setScores(score.getScores());
            caseDiscussion.setCandidateId(score.getCandidateId());
            caseDiscussion.setJudgeId(score.getJudgeId());
            int r = caseDiscussionDao.insetOne(caseDiscussion);
            if (r == 1) result = true;
        } else if(score.getTurn() == 3){
            // 理论宣讲
            TheoreticalPresentation theoreticalPresentation = new TheoreticalPresentation();
            theoreticalPresentation.setScores(score.getScores());
            theoreticalPresentation.setCandidateId(score.getCandidateId());
            theoreticalPresentation.setJudgeId(score.getJudgeId());
            int r = theoreticalPresentationDao.insetOne(theoreticalPresentation);
            if (r == 1) result = true;
        } else if(score.getTurn() == 4){
            // 谈心谈话
            Talk talk = new Talk();
            talk.setScores(score.getScores());
            talk.setCandidateId(score.getCandidateId());
            talk.setJudgeId(score.getJudgeId());
            int r = talkDao.insetOne(talk);
            if (r == 1) result = true;
        }
        return result;
    }

    /**
     * 0: 还有评委没有打分
     * 1： 所有评委都已打分，并且分数统计完成
     * -1： 所有评委都已打分，但是分数统计失败
     * @param turn
     * @param candidateId
     * @param judgeNum
     * @return
     */
    @Override
    public int isDone(Integer turn, Integer candidateId, Integer judgeNum) {
        int result = 0;
        if(turn == 2){
            // 案例讨论
            int r = caseDiscussionDao.getJudgedNum(candidateId);
            if(r == judgeNum) {
                // 统计案例讨论的总分
                /*
                * 这里先计算本项目的原始得分，当所有选手都被评完分之后再进行归一化
                * */
                Double score = caculate2(candidateId);
                int i = caseDiscussionDao.updateOriginScore(score, candidateId);
                if(i == 1){
                    result = 1;
                } else {
                    result = -1;
                }
            }
        } else if(turn == 3){
            // 理论宣讲
            int r = theoreticalPresentationDao.getJudgedNum(candidateId);
            if(r == judgeNum){
                // 统计理论宣讲分数
                Double score = caculate3(candidateId);
                int i = theoreticalPresentationDao.updateScore3ByCandidateId(score, candidateId);
                if(i == 1){
                    result = 1;
                } else {
                    result = -1;
                }
            }

        } else if(turn == 4){
            // 谈心谈话
            int r = talkDao.getJudgedNum(candidateId);
            if(r == judgeNum){
                // 统计谈心谈话的分数
                Double score = caculate4(candidateId);
                int i = talkDao.updateScore4ByCandidateId(score, candidateId);
                if(i == 1){
                    result = 1;
                } else {
                    result = -1;
                }
            }
        }
        return result;
    }

    public Double caculate4(Integer candidateId){
        List<Talk> talks = talkDao.getScoreByCandidateId(candidateId);
        List<Double> scores = new ArrayList<>();
        for(Talk c : talks){
            double score1 = c.getScore1();
            double score2 = c.getScore2();
            double score3 = c.getScore3();
            double score4 = c.getScore4();

            double score = score1 + score2 + score3 + score4;
            scores.add(score);
        }

        if(scores.size() > 5){
            Collections.sort(scores);
            scores = scores.subList(1, scores.size() - 1);
        }

        OptionalDouble average = scores.stream().mapToDouble(a -> a).average();
        if (average.isPresent()) {
            System.out.println("平均值: " + average.getAsDouble());
            return average.getAsDouble();
        } else {
            System.out.println("列表为空");
            return null;
        }
    }

    public Double caculate3(Integer candidateId){
        List<TheoreticalPresentation> theoreticalPresentations = theoreticalPresentationDao.getScoreByCandidateId(candidateId);
        List<Double> scores = new ArrayList<>();
        for(TheoreticalPresentation c : theoreticalPresentations){
            double score1 = c.getScore1();
            double score2 = c.getScore2();
            double score3 = c.getScore3();
            double score4 = c.getScore4();
            double score5 = c.getScore5();

            double score = score1 + score2 + score3 + score4 + score5;
            scores.add(score);
        }

        if(scores.size() > 5){
            Collections.sort(scores);
            scores = scores.subList(1, scores.size() - 1);
        }

        OptionalDouble average = scores.stream().mapToDouble(a -> a).average();
        if (average.isPresent()) {
            System.out.println("平均值: " + average.getAsDouble());
            return average.getAsDouble();
        } else {
            System.out.println("列表为空");
            return null;
        }
    }

    public Double caculate2(Integer candidateId){
        List<CaseDiscussion> caseDiscussions = caseDiscussionDao.getScoreByCandidateId(candidateId);
        List<Double> scores = new ArrayList<>();
        for(CaseDiscussion c : caseDiscussions){
            double score1 = c.getScore1();
            double score2 = c.getScore2();

            double score = score1 + score2;
            scores.add(score);
        }

        if(scores.size() > 5){
            Collections.sort(scores);
            scores = scores.subList(1, scores.size() - 1);
        }

        OptionalDouble average = scores.stream().mapToDouble(a -> a).average();
        if (average.isPresent()) {
            System.out.println("平均值: " + average.getAsDouble());
            return average.getAsDouble();
        } else {
            System.out.println("列表为空");
            return null;
        }
    }
}


