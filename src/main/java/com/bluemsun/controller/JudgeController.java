package com.bluemsun.controller;

import com.bluemsun.entity.Judge;
import com.bluemsun.entity.Score;
import com.bluemsun.entity.dto.ResultDto;
import com.bluemsun.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public ResultDto<Object> login(@RequestBody Judge judge, HttpServletRequest request){
        ResultDto<Object> rt = new ResultDto<>();
        boolean success = judgeService.login(judge);
        rt.setResult(success);
        rt.setMsg(success ? "登录成功" : "登录失败");

        if(success){
            // 将当前评委信息存入session
            HttpSession session = request.getSession();
            session.setAttribute("judge", judge);

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
        HttpSession session = request.getSession();
        Judge judge = (Judge) session.getAttribute("judge");
        score.setJudgeId(judge.getId());

        boolean success = judgeService.score(score);
        rt.setResult(success);
        if(success){
            rt.setMsg("打分成功");

            // 打分成功后，查看当前选手是否已经被所有评委评分完毕，如果评分完毕，则统计总分
//            ServletContext application = request.getServletContext();
            int isDone = judgeService.isDone(score.getTurn(), score.getCandidateId());
            if(isDone == 1){
                rt.setMsg("打分成功，分数统计成功");
            } else if(isDone == -1) {
                rt.setMsg("打分成功, 分数统计失败");
            } else if(isDone == 2){
                rt.setMsg("打分你成功，分数统计成功，归一化完成");
            } else if(isDone == -2){
                rt.setMsg("打分成功，分数统计成功，归一化失败");
            }
        } else {
            rt.setMsg("打分失败");
        }
        return rt;
    }
}
