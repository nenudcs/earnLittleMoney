package com.bluemsun.dao;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.CaseDiscussion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CaseDiscussionDao {

    @Insert("insert into t_case_discussion(candidate_id, judge_id, score_1, score_2) " +
            "values(#{candidateId}, #{judgeId}, #{score1}, #{score2})")
    int insetOne(CaseDiscussion caseDiscussion);

    @Select("select count(*) from t_case_discussion where candidate_id = #{candidateId}")
    int getJudgedNum(Integer candidateId);

    @Select("select id, candidate_id as candidateId, judge_id as judgeId, score_1 as score1, score_2 as score2 " +
            "from t_case_discussion where candidate_id = #{candidateId}")
    List<CaseDiscussion> getScoreByCandidateId(Integer candidateId);

    @Update("update t_candidate set score_2_origin = #{originScore} where id = #{candidateId}")
    int updateOriginScore(Double originScore, Integer candidateId);
}
