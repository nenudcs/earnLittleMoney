package com.bluemsun.dao;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.Judge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JudgeDao {

    @Select("select * from t_judges where name=#{name} and password=#{password}")
    Judge selectOne(Judge judge);

}
