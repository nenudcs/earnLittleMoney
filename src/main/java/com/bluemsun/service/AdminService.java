package com.bluemsun.service;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.Show;
import com.bluemsun.entity.dto.CandidateAdminDto;
import com.bluemsun.entity.dto.CandidateDetailScore;
import com.bluemsun.entity.dto.FinalScore;
import com.bluemsun.entity.dto.JudgeAdminDto;

import java.util.List;

public interface AdminService {

    List<Candidate> showResult(Show show);

    List<FinalScore> getFinalScore();

    CandidateAdminDto showOverCandidate(CandidateAdminDto candidateAdminDto);

    boolean calculate(Integer flag);

    JudgeAdminDto showOverJudge(JudgeAdminDto judgeAdminDto);

    List<CandidateDetailScore> getCandidateScore(Integer turn);
}
