package com.bluemsun.controller;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.Show;
import com.bluemsun.entity.dto.FinalScore;
import com.bluemsun.entity.dto.ResultDto;
import com.bluemsun.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员，获取选手的各种小分
 */
@RestController
@CrossOrigin

@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/show")
    public ResultDto<List<Candidate>> show(@RequestBody Show show){
        ResultDto<List<Candidate>> rt = new ResultDto<>();
        List<Candidate> candidates = adminService.showResult(show);
        if(candidates.size() == 0){
            rt.setResult(false);
            rt.setMsg("获取失败");
        } else {
            rt.setResult(true);
            rt.setMsg("获取成功");
            rt.setData(candidates);
        }
        return rt;
    }


    @RequestMapping("/showAll")
    public ResultDto<List<FinalScore>> getFinalScore(){
        ResultDto<List<FinalScore>> rt = new ResultDto<>();
        List<FinalScore> finalScores = adminService.getFinalScore();
        if(finalScores.size() == 0){
            rt.setResult(false);
            rt.setMsg("获取失败");
        } else {
            rt.setResult(true);
            rt.setMsg("获取成功");
            rt.setData(finalScores);
        }
        return rt;
    }

}
