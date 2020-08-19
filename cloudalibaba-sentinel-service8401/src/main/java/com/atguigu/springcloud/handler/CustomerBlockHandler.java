package com.atguigu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

import com.atguigu.springcloud.entity.CommonResult;

/**
 * @auther xuhao
 * @create 2020年8月19日15:22:56
 */
public class CustomerBlockHandler
{
    public static CommonResult handlerException(BlockException exception)
    {
        return new CommonResult(4444,"按客戶自定义,global handlerException----1");
    }
    public static CommonResult handlerException2(BlockException exception)
    {
        return new CommonResult(4444,"按客戶自定义,global handlerException----2");
    }
}
