package com.bluemsun.service.impl;

import com.bluemsun.dao.CandidateDao;
import com.bluemsun.entity.Candidate;
import com.bluemsun.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    CandidateDao candidateDao;

    /**
     * 获取所有选手
     * @return
     */
    @Override
    public List<Candidate> getaAllCandidate() {
        return candidateDao.selctAll();
    }

    /**
     * 获取一个选手的分数
     * @return
     */
    @Override
    public Candidate getOne(Integer id) {
        return candidateDao.selectOne(id);

    }
}
