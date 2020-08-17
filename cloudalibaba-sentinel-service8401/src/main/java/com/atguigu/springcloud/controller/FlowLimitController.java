package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author XuHao
 * @date 2020/8/17  16:05
 **/
@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
//        try {
//            TimeUnit.MICROSECONDS.sleep(7000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "-----testA";
    }
    @GetMapping("/testB")
    public String testB(){
        return "-----testB";
    }
}
