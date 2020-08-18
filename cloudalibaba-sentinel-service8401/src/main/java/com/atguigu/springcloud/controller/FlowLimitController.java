package com.atguigu.springcloud.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "....testB");
        return "-----testB";
    }

    @GetMapping("/testD")
    public String testD() {
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("testD 测试RT");
//        log.info("testD 异常比例");
//        int age = 10 / 0;
        return "------testD";
    }
}
