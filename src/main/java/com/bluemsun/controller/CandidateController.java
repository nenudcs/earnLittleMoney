package com.bluemsun.controller;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.dto.ResultDto;

import com.bluemsun.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选手
 */
@RestController
@CrossOrigin

@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;


    @RequestMapping("/hello")
    public ResultDto<String> hello(){
        return new ResultDto<>(true, "hello", "hello");
    }

    @RequestMapping("/getAll")
    public ResultDto<List<Candidate>> getAll(){
        ResultDto<List<Candidate>> rt = new ResultDto<>();
        List<Candidate> candidates = candidateService.getaAllCandidate();
        rt.setResult(true);
        rt.setMsg("获取成功");
        rt.setData(candidates);
        return rt;
    }

    @RequestMapping("/getOne")
    public ResultDto<Candidate> getOneScore(Integer id){
        ResultDto<Candidate> rt = new ResultDto<>();
        Candidate candidate = candidateService.getOne(id);
        rt.setResult(candidate!=null);
        rt.setMsg(candidate!=null ? "获取成功" : "获取失败，没有此选手");
        rt.setData(candidate);
        return rt;
    }

//    @RequestMapping("/getOneDetail")
//    public ResultDto<Object> getOneDetail(Integer id, Integer turn){
//
//    }
}
