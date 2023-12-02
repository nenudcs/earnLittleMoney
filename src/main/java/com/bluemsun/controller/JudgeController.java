package com.bluemsun.controller;

import com.bluemsun.entity.Judge;
import com.bluemsun.entity.dto.ResultDto;
import com.bluemsun.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 评委
 */

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
        return rt;
    }
}
