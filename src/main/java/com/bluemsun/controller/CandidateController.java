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
    public ResultDto<List<Candidate>> getOneScore(){
        ResultDto<List<Candidate>> rt = new ResultDto<>();
        List<Candidate> candidates = candidateService.getaAllCandidate();
        rt.setResult(true);
        rt.setMsg("获取成功");
        rt.setData(candidates);
        return rt;
    }

}
