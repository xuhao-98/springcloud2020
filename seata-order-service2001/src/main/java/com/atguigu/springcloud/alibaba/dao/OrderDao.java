package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述：
 *
 * @author XuHao
 * @date 2020/8/25  14:30
 **/
@Mapper
public interface OrderDao
{
    //1 新建订单
    void create(Order order);

    //2 修改订单状态，从零改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
