package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    private CommonResult creat(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        log.info("********插入结果:" + i);
        if (i > 0) {
            return new CommonResult(200, "插入成功", i);
        } else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    private CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("*********查询结果:"+paymentById);
        if (paymentById != null){
            return new CommonResult(200, "查询成功", paymentById);
        }else {
            return new CommonResult(444, "查询失败"+id, null);
        }
    }
}
