package com.zr.rabbitmqdemo.service;

import com.zr.rabbitmqdemo.dto.OrderTimeOutInfo;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/23 16:33
 */
public interface OrderService {
    //取消订单
    String cancelOrder(String orderId);

    //订单超时
    String timeOut(OrderTimeOutInfo timeOutInfo);
}
