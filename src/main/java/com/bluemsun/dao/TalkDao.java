package com.bluemsun.dao;

import com.bluemsun.entity.CaseDiscussion;
import com.bluemsun.entity.Talk;
import com.bluemsun.entity.TheoreticalPresentation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TalkDao {
    @Insert("insert into t_talk(candidate_id, judge_id, score_1, score_total) " +
            "values(#{candidateId}, #{judgeId}, #{score1}, #{scoreTotal})")
    int insetOne(Talk talk);

    @Update("update t_talk set score_1 = #{score1} , score_total = #{scoreTotal} where candidate_id = #{candidateId} and judge_id = #{judgeId} ")
    int updateScore(Talk talk);

    @Select("select count(*) from t_talk where candidate_id = #{candidateId} and judge_id = #{judgeId} and is_confirmed = 1")
    int getConfirmed(Talk talk);

    @Update("update t_talk set is_confirmed = 1 where candidate_id = #{candidateId} and judge_id = #{judgeId}")
    int setIsConfirmed1(Talk talk);

    @Select("select id, candidate_id as candidateId, judge_id as judgeId, score_1 as score1 " +
            "from t_talk where candidate_id = #{candidateId}")
    List<Talk> getScoreByCandidateId(Integer candidateId);


    @Select("select count(*) from t_talk where candidate_id = #{candidateId}")
    int getJudgedNum(Integer candidateId);

    @Update("update t_candidate set score_4 = #{score} where id = #{candidateId}")
    int updateScore4ByCandidateId(Double score, Integer candidateId);

    @Select("select MAX(score_total) " +
            "from t_talk where candidate_id = #{candidateId}")
    Double selectMaxScore(Integer candidateId);

    @Select("select MIN(score_total) " +
            "from t_talk where candidate_id = #{candidateId}")
    Double selectMinScore(Integer candidateId);

    @Select("select count(*) from t_talk")
    int selectAllNum();

    @Select("select count(*) from t_talk where candidate_id = #{candidateId} and judge_id = #{judgeId}")
    int selectIsJudged(Talk talk);

    @Select("select id, candidate_id as candidateId, judge_id as judgeId, score_1 as score1, score_total as scoreTotal from t_talk where judge_id = #{judgeId}")
    List<Talk> selectJudgeinfo(Integer judgeId);

    @Select("select id, candidate_id as candidateId, judge_id as judgeId, score_1 as score1, score_total as scoreTotal from t_talk where candidate_id = #{candidateId}")
    List<Talk> selectCandidateinfo(Integer candidateId);
}
