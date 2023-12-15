package com.bluemsun.dao;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.CaseDiscussion;
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
    Double selectMaxScore(Integer maxCase);

    @Select("select MIN(score_total) " +
            "from t_case_discussion where candidate_id = #{candidateId}")
    Double selectMinScore(Integer minCase);


    @Select("select count(*) from t_case_discussion where candidate_id = #{candidateId} and judge_id = #{judgeId}")
    int selectIsJudged(CaseDiscussion caseDiscussion);
}
