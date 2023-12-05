package com.bluemsun.service.impl;

import com.bluemsun.dao.CandidateDao;
import com.bluemsun.dao.CaseDiscussionDao;
import com.bluemsun.dao.TalkDao;
import com.bluemsun.dao.TheoreticalPresentationDao;
import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.Score;
import com.bluemsun.entity.Show;
import com.bluemsun.entity.dto.FinalScore;
import com.bluemsun.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    CandidateDao candidateDao;
    CaseDiscussionDao caseDiscussionDao;
    TalkDao talkDao;
    TheoreticalPresentationDao theoreticalPresentationDao;

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
            int key;
            int num;
            double writtenScore;
            double caseHigh;
            double caseLow;
            double caseScore;
            int hallId;
            double heartHigh;
            double heartLow;
            double heartScore;
            double theoryHigh;
            double theoryLow;
            double theoryScore;
            double score;
            key = candidate.getId();
            num = candidate.getNum();
            writtenScore = candidate.getScore_1();
            caseHigh = caseDiscussionDao.selectMaxScore(key);
            caseLow = caseDiscussionDao.selectMinScore(key);
            caseScore = candidate.getScore_2();
            hallId = candidate.getHall_id();
            heartHigh = talkDao.selectMaxScore(key);
            heartLow = talkDao.selectMinScore(key);
            heartScore = candidate.getScore_3();
            theoryHigh = theoreticalPresentationDao.selectMaxScore(key);
            theoryLow = theoreticalPresentationDao.selectMinScore(key);
            theoryScore = candidate.getScore_4();
            score = candidate.getScore_total();
            FinalScore finalScore = new FinalScore(key,num,writtenScore,caseHigh,caseLow,caseScore,hallId,heartHigh,heartLow,heartScore,theoryHigh,theoryLow,theoryScore,score);
            result.add(finalScore);
        }
        return result;

    }


}
