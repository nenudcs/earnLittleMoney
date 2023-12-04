package com.bluemsun.dao;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.Judge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface JudgeDao {

    @Select("select * from t_judges where name=#{name} and password=#{password}")
    Judge selectOne(Judge judge);

    @Update("update t_judges set isLogin = 1 where id = #{id}")
    int set1(Judge judge);

    @Update("update t_judges set isLogin = 0 where id = #{id}")
    int set0(Judge judge);

    @Select("select count(*) from t_judges where hall_id=#{hallId} and isLogin=1")
    int getJudgeNum(Integer hallId);
}
