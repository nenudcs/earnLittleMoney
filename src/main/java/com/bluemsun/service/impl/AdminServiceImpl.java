package com.bluemsun.service.impl;

import com.bluemsun.dao.CandidateDao;
import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.Show;
import com.bluemsun.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    CandidateDao candidateDao;

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
}
