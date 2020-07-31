package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        String info_ok = paymentHystrixService.paymentInfo_ok(id);
        log.info("*******************info_ok  "+info_ok);
        return info_ok;
    }

    @GetMapping("/consumer/payment/hystrix/timeOut/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String info_timeOut = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("********************info_timeOut  "+info_timeOut);
        return info_timeOut;
    }
}
