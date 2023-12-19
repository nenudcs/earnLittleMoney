package com.bluemsun.dao;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.CaseDiscussion;
import com.bluemsun.entity.Talk;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Mapper
public interface CaseDiscussionDao {

    @Insert("insert into t_case_discussion(candidate_id, judge_id, score_1, score_2, score_total) " +
            "values(#{candidateId}, #{judgeId}, #{score1}, #{score2}, #{scoreTotal})")
    int insetOne(CaseDiscussion caseDiscussion);

    @Update("update t_case_discussion set score_1 = #{score1} ,score_2 = #{score2}, score_total = #{scoreTotal} where candidate_id = #{candidateId} and judge_id = #{judgeId} ")
    int updateScore(CaseDiscussion caseDiscussion);

    @Select("select count(*) from t_case_discussion where candidate_id = #{candidateId} and judge_id = #{judgeId} and is_confirmed = 1")
    int getConfirmed(CaseDiscussion caseDiscussion);

    @Update("update t_case_discussion set is_confirmed = 1 where candidate_id = #{candidateId} and judge_id = #{judgeId}")
    int setIsConfirmed1(CaseDiscussion caseDiscussion);


    @Select("select count(*) from t_case_discussion where candidate_id = #{candidateId}")
    int getJudgedNum(Integer candidateId);

    @Select("select id, candidate_id as candidateId, judge_id as judgeId, score_1 as score1, score_2 as score2 " +
            "from t_case_discussion where candidate_id = #{candidateId}")
    List<CaseDiscussion> getScoreByCandidateId(Integer candidateId);

    @Update("update t_candidate set score_2_origin = #{originScore} where id = #{candidateId}")
    int updateOriginScore(Double originScore, Integer candidateId);

    @Select("select count(cd.id) " +
            "from t_case_discussion cd left join t_candidate ca " +
            "on cd.candidate_id = ca.id where ca.hall_id=#{hallId}")
    int selectAllNum(Integer hallId);

    @Select("select MAX(score_total) " +
            "from t_case_discussion where candidate_id = #{candidateId}")
    Double selectMaxScore(Integer candidateId);

    @Select("select MIN(score_total) " +
            "from t_case_discussion where candidate_id = #{candidateId}")
    Double selectMinScore(Integer candidateId);


    @Select("select count(*) from t_case_discussion where candidate_id = #{candidateId} and judge_id = #{judgeId}")
    int selectIsJudged(CaseDiscussion caseDiscussion);

    @Select("select id, candidate_id as candidateId, judge_id as judgeId, score_1 as score1, score_2 as score2, score_total as scoreTotal, is_confirmed as isConfirmed from t_case_discussion where judge_id = #{judgeId}")
    List<CaseDiscussion> selectJudgeinfo(Integer judgeId);

    @Select("select id, candidate_id as candidateId, judge_id as judgeId, score_1 as score1, score_2 as score2, score_total as scoreTotal, is_confirmed as isConfirmed from t_case_discussion where candidate_id = #{candidateId}")
    List<CaseDiscussion> selectCandidateinfo(Integer candidateId);
}
