package com.bluemsun.controller;

import com.auth0.jwt.interfaces.Claim;
import com.bluemsun.entity.Judge;
import com.bluemsun.entity.Score;
import com.bluemsun.entity.dto.ResultDto;
import com.bluemsun.service.JudgeService;
import com.bluemsun.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 评委
 */
@RestController
@CrossOrigin
@RequestMapping("/judge")
public class JudgeController {
    @Autowired
    JudgeService judgeService;

    @RequestMapping("/login")
    public ResultDto<Object> login(@RequestBody Judge judge){
        ResultDto<Object> rt = new ResultDto<>();
        boolean success = judgeService.login(judge);
        rt.setResult(success);
        rt.setMsg(success ? "登录成功" : "登录失败");

        if(success){
            try {
                String token = JWTUtils.createToken(judge.getId());
                Map<String, String> resMap = new HashMap<>();
                resMap.put("token", token);
                rt.setData(resMap);
            } catch (Exception e) {
                e.printStackTrace();
                rt.setResult(false);
                rt.setMsg("token获取失败");
            }

            // judgeNum：当前有多少个评委
//            ServletContext apllication = request.getServletContext();
//            try{
//                int judgeNum = (int) apllication.getAttribute("judgeNum");
//                judgeNum = judgeNum + 1;
//                apllication.setAttribute("judgeNum", judgeNum);
//            } catch(Exception e){
//                apllication.setAttribute("judgeNum", 1);
//            }

        }
        return rt;
    }

    @RequestMapping("/score")
    public ResultDto<Object> score(@RequestBody Score score, HttpServletRequest request){
        ResultDto<Object> rt = new ResultDto<>();

        String token = request.getHeader("token");
        try {
            Map<String, Claim> stringClaimMap = JWTUtils.verifyToken(token);
            score.setJudgeId(stringClaimMap.get("id").asInt());
        } catch (Exception e) {
            e.printStackTrace();
            rt.setMsg("token解析失败");
            rt.setResult(false);
            return rt;
        }


        int success = judgeService.score(score);
        rt.setResult(success==1);
        if(success==1){
            rt.setMsg("打分成功");

            // 打分成功后，查看当前选手是否已经被所有评委评分完毕，如果评分完毕，则统计总分
//            ServletContext application = request.getServletContext();
            int isDone = judgeService.isDone(score.getTurn(), score.getCandidateId());
            if(isDone == 1){
                rt.setMsg("打分成功，分数统计成功");
            } else if(isDone == -1) {
                rt.setMsg("打分成功, 分数统计失败");
            }
        } else if(success == 0){
            rt.setMsg("打分失败");
        } else if(success == -1){
            rt.setMsg("请勿重复打分");
        }
        return rt;
    }
    @RequestMapping("/isComfirmed")
    public ResultDto<Object> isComfirmed(@RequestBody Score score, HttpServletRequest request){
        ResultDto<Object> rt = new ResultDto<>();

        String token = request.getHeader("token");
        try {
            Map<String, Claim> stringClaimMap = JWTUtils.verifyToken(token);
            score.setJudgeId(stringClaimMap.get("id").asInt());
        } catch (Exception e) {
            e.printStackTrace();
            rt.setMsg("token解析失败");
            rt.setResult(false);
            return rt;
        }


        int success = judgeService.isConfirmed(score);
        rt.setResult(success==1);
        if(success==1){
            rt.setMsg("已提交，不能打分/修改");
        } else if(success == 0){
            rt.setMsg("未提交，可以打分/修改");
        }
        return rt;
    }
    @RequestMapping("/logout")
    public ResultDto<Object> logout(HttpServletRequest request){
        ResultDto<Object> rt = new ResultDto<>();
        String token = request.getHeader("token");
        try {
            Map<String, Claim> stringClaimMap = JWTUtils.verifyToken(token);
            Integer judgeId = stringClaimMap.get("id").asInt();
            boolean success = judgeService.logout(judgeId);
            rt.setResult(success);
            if(success){
                rt.setMsg("退出登录成功");
            } else{
                rt.setMsg("退出登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            rt.setMsg("token解析失败");
            rt.setResult(false);
            return rt;
        }


        return rt;
    }
}
