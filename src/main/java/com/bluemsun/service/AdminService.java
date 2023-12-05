package com.bluemsun.service;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.Show;
import com.bluemsun.entity.dto.FinalScore;

import java.util.List;

public interface AdminService {

    List<Candidate> showResult(Show show);
    List<FinalScore> getFinalScore();
}
