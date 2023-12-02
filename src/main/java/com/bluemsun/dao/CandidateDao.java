package com.bluemsun.dao;

import com.bluemsun.entity.Candidate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CandidateDao {

    @Select("select id,num,name,score_1,score_2,score_3,score_4,score_half,score_total,hall_id,promote " +
            "from t_candidate")
    List<Candidate> selctAll();

    @Select("select * from t_candidate where id=#{id}")
    Candidate selectOne(Integer id);
}
