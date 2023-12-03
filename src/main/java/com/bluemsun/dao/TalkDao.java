package com.bluemsun.dao;

import com.bluemsun.entity.Talk;
import com.bluemsun.entity.TheoreticalPresentation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TalkDao {
    @Insert("insert into t_talk(candidate_id, judge_id, score_1, score_2, score_3, score_4) " +
            "values(#{candidateId}, #{judgeId}, #{score1}, #{score2}, #{score3}, #{score4}}")
    int insetOne(Talk talk);

    @Select("select id, candidate_id as candidateId, judge_id as judgeId, score_1 as score1, score_2 as score2, score_3 as score3, score_4 as score4 " +
            "from t_talk where candidate_id = #{candidateId}")
    List<Talk> getScoreByCandidateId(Integer candidateId);


    @Select("select count(*) from t_talk where candidate_id = #{candidateId}")
    int getJudgedNum(Integer candidateId);

    @Update("update t_candidate set score_4 = #{score} where id = #{candidateId}")
    int updateScore4ByCandidateId(Double score, Integer candidateId);
}
