package com.bluemsun.dao;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.Judge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JudgeDao {

    @Select("select count(*) from t_judges where name=#{name} and password=#{password}")
    int selectOne(Judge judge);


}
