package com.bluemsun.controller;

import com.bluemsun.entity.Judge;
import com.bluemsun.entity.dto.ResultDto;
import com.bluemsun.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            HttpSession session = request.getSession();
            session.setAttribute("judge", judge);
        }
        return rt;
    }
}
