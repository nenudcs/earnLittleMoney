package com.bluemsun.dao;

import com.bluemsun.entity.Candidate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CandidateDao {

    @Select("select id,num,name,score_1,score_2,score_3,score_4,score_half,score_total,hall_id,promote " +
            "from t_candidate order by score_total desc")
    List<Candidate> selctAll();

    @Select("select * from t_candidate where promote = 1")
    List<Candidate> selectAllPass();

    @Select("select * from t_candidate where id=#{id}")
    Candidate selectOne(Integer id);

    @Select("select count(*) from t_candidate where hall_id = #{hallId}")
    int selectAllNum(Integer hallId);

    @Select("select * from t_candidate where hall_id = #{hallId}")
    List<Candidate> selectAllCandidate(Integer hallId);

    @Update("update t_candidate set score_2 = #{score_2} where id = #{id}")
    int setScore2(Candidate candidate);

    @Update("update t_candidate set score_half = #{score_half} where id = #{id}")
    int setScoreHalf(Candidate candidate);

    @Update("update t_candidate set score_total = #{score_total} where id = #{id}")
    int setScoreTotal(Candidate candidate);

    @Select("select * from t_candidate where groupId = #{groupId} and hall_id = #{hallId}")
    List<Candidate> selectByGroupId(Integer groupId, Integer hallId);

    @Select("select count(*) from t_candidate where promote = #{promote}")
    int selectAllNumByPromote(Integer promote);
}
