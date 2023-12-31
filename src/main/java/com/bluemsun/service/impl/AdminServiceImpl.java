package com.bluemsun.service.impl;

import com.bluemsun.dao.*;
import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.CaseDiscussion;
import com.bluemsun.entity.Show;
import com.bluemsun.entity.Talk;
import com.bluemsun.entity.dto.CandidateAdminDto;
import com.bluemsun.entity.dto.CandidateDetailScore;
import com.bluemsun.entity.dto.FinalScore;
import com.bluemsun.entity.dto.JudgeAdminDto;
import com.bluemsun.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    CandidateDao candidateDao;
    @Autowired
    CaseDiscussionDao caseDiscussionDao;
    @Autowired
    TalkDao talkDao;
    @Autowired
    TheoreticalPresentationDao theoreticalPresentationDao;
    @Autowired
    JudgeDao judgeDao;

    @Override
    public List<Candidate> showResult(Show show) {
        List<Candidate> result = new ArrayList<>();
        if(show.getTurn() == 2){
            // 案例讨论
            if(show.getCandidateId() != null){
                // 展示某一个选手
                Candidate candidate = candidateDao.selectOne(show.getCandidateId());
                result.add(candidate);
            } else {
                // 展示一组选手
                result = candidateDao.selectByGroupId(show.getGroupId(), show.getHallId());
            }
        } else if(show.getTurn() == 3){
            // 理论宣讲
            result.add(candidateDao.selectOne(show.getCandidateId()));
        } else if(show.getTurn() == 4){
            // 谈心谈话
            result.add(candidateDao.selectOne(show.getCandidateId()));
        }
        return result;
    }

    @Override
    public List<FinalScore> getFinalScore() {
        List<Candidate> candidateList = candidateDao.selctAll();
        List<FinalScore> result = new ArrayList<>();
        Iterator<Candidate> iterator = candidateList.iterator();

        while (iterator.hasNext()) {
            Candidate candidate = iterator.next();
            Integer key;
            Integer num;
            Double writtenScore;
            Double caseHigh;
            Double caseLow;
            Double caseScore;
            Integer hallId = 1;
            Double heartHigh;
            Double heartLow;
            Double heartScore;

            Double score;
            key = candidate.getId();
            num = candidate.getNum();
            writtenScore = candidate.getScore_1();
            caseHigh = caseDiscussionDao.selectMaxScore(key);
            caseLow = caseDiscussionDao.selectMinScore(key);
            caseScore = candidate.getScore_2();
            heartHigh = talkDao.selectMaxScore(key);
            heartLow = talkDao.selectMinScore(key);
            heartScore = candidate.getScore_4();
            score = candidate.getScore_total();
            FinalScore finalScore = new FinalScore(key,num,writtenScore,caseHigh,caseLow,caseScore,hallId,heartHigh,heartLow,heartScore,score);
            result.add(finalScore);
        }
        return result;

    }

    @Override
    public List<CandidateDetailScore> getCandidateScore(Integer turn) {
        List<CandidateDetailScore> result = new ArrayList<>();
        Integer NN = 0;
        if(turn == 3){
            List<Candidate> candidateList = candidateDao.selctAll();
            Iterator<Candidate> iterator = candidateList.iterator();
            while (iterator.hasNext()) {
                Candidate candidate = iterator.next();
                Integer num;    // 选手序号
                String name;
                List<Object> scores = new ArrayList<>();
                Double high;
                Double low;
                Double total;
                num = ++NN;
                name = candidate.getName();
                List<Talk> list = talkDao.selectCandidateinfo(candidate.getId());
                Iterator<Talk> iterator1 = list.iterator();
                while (iterator1.hasNext()){
                    Talk talk = iterator1.next();
                    scores.add(talk);
                }
                high = talkDao.selectMaxScore(candidate.getId());
                low = talkDao.selectMinScore(candidate.getId());
                total = candidate.getScore_4();
                CandidateDetailScore candidateDetailScore = new CandidateDetailScore(num,name,scores,high,low,total);
                result.add(candidateDetailScore);
            }
        }else if(turn == 2){
            List<Candidate> candidateList = candidateDao.selctAll();
            Iterator<Candidate> iterator = candidateList.iterator();
            while (iterator.hasNext()) {
                Candidate candidate = iterator.next();
                Integer num;    // 选手序号
                String name;
                List<Object> scores = new ArrayList<>();
                Double high;
                Double low;
                Double total;
                num = ++NN;
                name = candidate.getName();
                List<CaseDiscussion> list = caseDiscussionDao.selectCandidateinfo(candidate.getId());
                Iterator<CaseDiscussion> iterator1 = list.iterator();
                while (iterator1.hasNext()){
                    CaseDiscussion caseDiscussion = iterator1.next();
                    scores.add(caseDiscussion);
                }
                high = caseDiscussionDao.selectMaxScore(candidate.getId());
                low = caseDiscussionDao.selectMinScore(candidate.getId());
                total = candidate.getScore_2();
                CandidateDetailScore candidateDetailScore = new CandidateDetailScore(num,name,scores,high,low,total);
                result.add(candidateDetailScore);
            }
        }


        return result;

    }

    @Override
    public CandidateAdminDto showOverCandidate(CandidateAdminDto candidateAdminDto) {
        /*
        1. 根据hallId获取评委总数
        2. 根据
         */
        int turn = candidateAdminDto.getTurn();
        if(turn == 2){
            // 案例讨论
            // 查看选手是哪个会场的，然后获取评委数量
            int judgeNum = judgeDao.getJudgeNum(candidateAdminDto.getHallId());
            // 原始分数统计完成，查看这是否是最后一位选手
            // 如果是最后一位选手，将分数归一化
            int allNumCase = caseDiscussionDao.selectAllNum(candidateAdminDto.getHallId());
            int allCandidate = candidateDao.selectAllNum(candidateAdminDto.getHallId());
            candidateAdminDto.setCandidateTotalNum(allCandidate);
            candidateAdminDto.setCandidateOverNum(allNumCase/judgeNum);

        } else if(turn == 3){
            // 理论宣讲
            // 默认处于会场1
            int judgeNum = judgeDao.getJudgeNum(1);
            int allNumTheory = theoreticalPresentationDao.selectAllNum();
            int allCandidate = candidateDao.selectAllNumByPromote(1);   // 只算通过初赛的选手
            candidateAdminDto.setCandidateTotalNum(allCandidate);
            candidateAdminDto.setCandidateOverNum(allNumTheory/judgeNum);

        } else if(turn == 4){
            // 谈心谈话
            // 默认处于会场1
            int judgeNum = judgeDao.getJudgeNum(1);
            int allNumTalk = talkDao.selectAllNum();
            int allCandidate = candidateDao.selectAllNumByPromote(1);
            candidateAdminDto.setCandidateTotalNum(allCandidate);
            candidateAdminDto.setCandidateOverNum(allNumTalk/judgeNum);
        }
        return candidateAdminDto;
    }

    @Override
    public int calculate() {
        // 计算决赛成绩
        List<Candidate> candidates = candidateDao.selectAllPass();
//        for(Candidate c : candidates) {
//            if (c.getScore_1() == null || c.getScore_2() == null || c.getScore_4() == null) {
//                // 分数没有统计完，无法计算决赛成绩
//                return -1;
//            }
//        }
        for(Candidate c : candidates) {
            if (c.getScore_1() == null || c.getScore_2() == null || c.getScore_4() == null) {
                // 分数没有统计完，无法计算决赛成绩
                return -1;
            }
            Double score1 = c.getScore_1(); // 基础知识
            Double score2 = c.getScore_2(); // 案例讨论

            Double score4 = c.getScore_4(); // 谈心谈话


            DecimalFormat df = new DecimalFormat("#.00");
            String str = df.format(score1 * 0.2 + score2 * 0.4 + score4 * 0.4);
            c.setScore_total(Double.parseDouble(str));
            int i = candidateDao.setScoreTotal(c);
            if (i != 1) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    public JudgeAdminDto showOverJudge(JudgeAdminDto judgeAdminDto) {
        /*
        1、先获取总评委数
        2、再根据turn，去不同的活动表中通过candidateId获取多少评委已经打分
         */
        int turn = judgeAdminDto.getTurn();
        if(turn == 2){
            // 案例讨论
            int judgeNum = judgeDao.getJudgeNum(judgeAdminDto.getHallId());
            int judgedNum = caseDiscussionDao.getJudgedNum(judgeAdminDto.getCandidateId());
            judgeAdminDto.setJudgedNum(judgedNum);  // 已评分的评委
            judgeAdminDto.setJudgeAllNum(judgeNum); // 总评委

        } else if(turn == 3){
            // 理论宣讲
            // 默认处于会场1
            int judgeNum = judgeDao.getJudgeNum(1);
            int judgedNum = theoreticalPresentationDao.getJudgedNum(judgeAdminDto.getCandidateId());
            judgeAdminDto.setJudgedNum(judgedNum);  // 已评分的评委
            judgeAdminDto.setJudgeAllNum(judgeNum); // 总评委

        } else if(turn == 4){
            // 谈心谈话
            // 默认处于会场1
            int judgeNum = judgeDao.getJudgeNum(1);
            int judgedNum = talkDao.getJudgedNum(judgeAdminDto.getCandidateId());
            judgeAdminDto.setJudgedNum(judgedNum);  // 已评分的评委
            judgeAdminDto.setJudgeAllNum(judgeNum); // 总评委
        }
        return judgeAdminDto;

    }


}
