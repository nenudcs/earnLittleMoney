package com.bluemsun.dao;

import com.bluemsun.entity.CaseDiscussion;
import com.bluemsun.entity.TheoreticalPresentation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TheoreticalPresentationDao {

    @Insert("insert into t_theoretical_presentation(candidate_id, judge_id, score_1, score_2, score_3, score_4, score_5) " +
            "values(#{candidateId}, #{judgeId}, #{score1}, #{score2}, #{score3}, #{score4}, #{score5})")
    int insetOne(TheoreticalPresentation theoreticalPresentation);

    @Select("select count(*) from t_theoretical_presentation where candidate_id = #{candidateId}")
    int getJudgedNum(Integer candidateId);

    @Select("select id, candidate_id as candidateId, judge_id as judgeId, score_1 as score1, score_2 as score2, score_3 as score3, score_4 as score4, score_5 as score5 " +
            "from t_theoretical_presentation where candidate_id = #{candidateId}")
    List<TheoreticalPresentation> getScoreByCandidateId(Integer candidateId);

    @Update("update t_candidate set score_3 = #{score} where id = #{candidateId}")
    int updateScore3ByCandidateId(Double score, Integer candidateId);
}
