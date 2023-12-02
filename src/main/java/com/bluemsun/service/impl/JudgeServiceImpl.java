package com.bluemsun.service.impl;

import com.bluemsun.dao.JudgeDao;
import com.bluemsun.entity.Judge;
import com.bluemsun.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Autowired
    JudgeDao judgeDao;

    @Override
    public boolean login(Judge judge) {
        if("".equals(judge.getName()) || judge.getName()==null
        || "".equals(judge.getPassword()) || judge.getPassword()==null){
            return false;
        } else {
            int r = judgeDao.selectOne(judge);
            return r == 1;
        }
    }
}
