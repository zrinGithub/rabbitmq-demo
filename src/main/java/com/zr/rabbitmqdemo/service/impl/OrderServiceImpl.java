package com.zr.rabbitmqdemo.service.impl;

import com.zr.rabbitmqdemo.dto.OrderTimeOutInfo;
import com.zr.rabbitmqdemo.producer.MessageProducer;
import com.zr.rabbitmqdemo.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author zhangrRabbitListener
 * 2020/7/23 16:34
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private MessageProducer producer;

    @Override
    public String cancelOrder(String orderId) {
        //取消订单业务处理，省略
        //发送消息进行其他业务（短信提醒啊或者其他不需要同步实时的）
        producer.sendCancelOrderInfo(orderId);
        return "取消订单完成";
    }

    @Override
    public String timeOut(OrderTimeOutInfo timeOutInfo) {
        //超时的业务处理，省略
        //发送消息进行其他业务（短信提醒啊或者其他不需要同步实时的）
        producer.sendTimeOutInfo(timeOutInfo);
        return "订单超时处理完成";
    }
}
