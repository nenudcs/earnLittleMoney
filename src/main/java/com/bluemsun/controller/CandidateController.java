package com.bluemsun.controller;

import com.bluemsun.entity.dto.ResultDto;

import org.springframework.web.bind.annotation.*;

/**
 * 选手
 */
@RestController
@CrossOrigin
public class CandidateController {

    @RequestMapping("/hello")
    public ResultDto<String> hello(){
        return new ResultDto<>(true, "hello", "hello");
    }


}
