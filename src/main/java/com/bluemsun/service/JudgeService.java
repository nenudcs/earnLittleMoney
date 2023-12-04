package com.bluemsun.service;

import com.bluemsun.entity.Judge;
import com.bluemsun.entity.Score;

public interface JudgeService {
    boolean login(Judge judge);

    boolean score(Score score);

    int isDone(Integer turn, Integer candidateId);

}
