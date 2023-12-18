package com.bluemsun.service;

import com.bluemsun.entity.*;

import java.util.List;

public interface JudgeService {
    boolean login(Judge judge);

    int score(Score score);

    int isDone(Integer turn, Integer candidateId);

    boolean logout(Integer judgeId);

    int isConfirmed(Score score);

    List<Talk> getaAllTalkofThisJudge(Integer judgeId);

    List<CaseDiscussion> getaAllCaseDiscussionofThisJudge(Integer judgeId);

}
