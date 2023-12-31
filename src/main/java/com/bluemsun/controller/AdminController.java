package com.bluemsun.controller;

import com.bluemsun.entity.Candidate;
import com.bluemsun.entity.CandidateKey;
import com.bluemsun.entity.Show;
import com.bluemsun.entity.dto.*;
import com.bluemsun.service.AdminService;
import com.bluemsun.utils.CandidateCaseDetailScoreComparator;
import com.bluemsun.utils.CandidateDetailScoreTalkComparator;
import com.bluemsun.utils.CaseComparator;
import com.bluemsun.utils.TalkComparator;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public ResultDto<List<CandidateKey>> show(@RequestBody Show show){
        ResultDto<List<CandidateKey>> rt = new ResultDto<>();
        List<Candidate> candidates = adminService.showResult(show);
        if(candidates.size() == 0){
            rt.setResult(false);
            rt.setMsg("获取失败");
        } else {
            rt.setResult(true);
            rt.setMsg("获取成功");
            // 应前端要求，将id换为key
            List<CandidateKey> l = new ArrayList<>();
            for(Candidate c : candidates){
                l.add(new CandidateKey(c));
            }
            rt.setData(l);
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

    @RequestMapping("/showCandidateAll")
    public ResultDto<List<CandidateDetailScore>> getCandidateAll(@RequestBody CandidateAdminDto candidateAdminDto){
        ResultDto<List<CandidateDetailScore>> rt = new ResultDto<>();
        List<CandidateDetailScore> candidateDetailScores = adminService.getCandidateScore(candidateAdminDto.getTurn());
        if(candidateAdminDto.getTurn() == 2){
            // 按照案例讨论的选手顺序排序
            candidateDetailScores.sort(new CandidateCaseDetailScoreComparator());
        } else if(candidateAdminDto.getTurn() == 3){
            // 按照谈心谈话的选手顺序排序
            candidateDetailScores.sort(new CandidateDetailScoreTalkComparator());
        }
        if(candidateDetailScores.size() == 0){
            rt.setResult(false);
            rt.setMsg("获取失败");
        } else {
            rt.setResult(true);
            rt.setMsg("获取成功");
            rt.setData(candidateDetailScores);
        }
        return rt;
    }


    @RequestMapping("/showOverCandidate")
    public ResultDto<CandidateAdminDto> showOverCandidate(@RequestBody CandidateAdminDto candidateAdminDto){
        ResultDto<CandidateAdminDto> rt = new ResultDto<>();
        adminService.showOverCandidate(candidateAdminDto);
        if(candidateAdminDto.getCandidateTotalNum()!=null && candidateAdminDto.getCandidateOverNum()!=null){
            rt.setResult(true);
            rt.setData(candidateAdminDto);
            rt.setMsg("获取成功");
        } else {
            rt.setResult(false);
            rt.setMsg("获取失败");
        }

        return rt;
    }


    @RequestMapping("/calculate")
    public ResultDto<Object> calculateHalfOrTotalScore(){
        ResultDto<Object> rt = new ResultDto<>();
        int success = adminService.calculate();

        if(success==1){
            rt.setResult(true);
            rt.setMsg("计算成功");
        } else if(success == -1){
            rt.setResult(false);
            rt.setMsg("有选手分数未统计完成，只计算统计完成的选手");
        } else{
            rt.setResult(false);
            rt.setMsg("数据库存入失败");
        }
        return rt;
    }


    @RequestMapping("/showOverJudge")
    public ResultDto<JudgeAdminDto> showOverJudge(@RequestBody JudgeAdminDto judgeAdminDto){
        ResultDto<JudgeAdminDto> rt = new ResultDto<>();
        adminService.showOverJudge(judgeAdminDto);
        if(judgeAdminDto.getJudgeAllNum()==null || judgeAdminDto.getJudgedNum()==null){
            rt.setResult(false);
            rt.setMsg("获取失败");

        } else {
            rt.setResult(true);
            rt.setMsg("获取成功");
            rt.setData(judgeAdminDto);
        }
        return rt;
    }

    @RequestMapping("/clear")
    public ResultDto<Object> clear(){
      ResultDto<Object> rt = new ResultDto<>();

      return rt;
    }
}
