package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    /**
     * 成功操作
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        String info_ok = paymentHystrixService.paymentInfo_ok(id);
        log.info("*******************info_ok  " + info_ok);
        return info_ok;
    }

    /**
     * 超时操作
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/timeOut/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        int age = 10/0;
        String info_timeOut = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("********************info_timeOut  " + info_timeOut);
        return info_timeOut;
    }
    /**
     * 备用方法
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(Integer id){
        return "我是消费者80，对方系统繁忙请稍后再试！";

    }

    /**
     * 下面是全局fallback方法
     * @return
     */
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
