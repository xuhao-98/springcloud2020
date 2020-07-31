package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    public String paymentInfo_ok(Integer id) {
        return "线程池 " + Thread.currentThread().getName() + "  paymentInfo_ok,id:  " + id + "\t" + "许昊牛逼！";
    }

    /**
     * 超时
     *
     * @param id
     * @return
     */
    public String paymentInfo_TimeOut(Integer id) {
        //int age = 10/0;
        int timeNum = 5;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName() + " id:  " + id + "\t" + "耗时:" + timeNum;
    }
}
