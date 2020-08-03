package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
//        int age = 10/0;
        int timeNum = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       return "线程池:  " + Thread.currentThread().getName() + " id:  " + id + "\t" + "耗时:" + timeNum;
//        return "线程池:  " + Thread.currentThread().getName() + " id:  " + id + "\t";
    }

    /**
     * 备用方法
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池:  " + Thread.currentThread().getName() + " id:  " + id + "\t" + "8001系统繁忙或者运行出错，请稍后";

    }
}
