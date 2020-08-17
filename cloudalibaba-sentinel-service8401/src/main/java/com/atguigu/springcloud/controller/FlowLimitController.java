package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author XuHao
 * @date 2020/8/17  16:05
 **/
@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "-----testA";
    }
    @GetMapping("/testB")
    public String testB(){
        return "-----testB";
    }
}
