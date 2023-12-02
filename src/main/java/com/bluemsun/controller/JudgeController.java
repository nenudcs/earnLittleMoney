package com.bluemsun.controller;

import com.bluemsun.entity.Judge;
import com.bluemsun.entity.dto.ResultDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 评委
 */

@RequestMapping("/judge")
public class JudgeController {

    @RequestMapping("/login")
    public ResultDto<Object> login(@RequestBody Judge judge){
        ResultDto<Object> rt = new ResultDto<>();

        return rt;
    }
}
