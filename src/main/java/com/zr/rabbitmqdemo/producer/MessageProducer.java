package com.zr.rabbitmqdemo.producer;

import com.zr.rabbitmqdemo.dto.ArriveNoticeInfo;
import com.zr.rabbitmqdemo.dto.OrderTimeOutInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.zr.rabbitmqdemo.config.RabbitMqConfig.*;

/**
 * Description:
 *
 * @author zhangr
 * 2020/8/4 17:02
 */
@Component
public class MessageProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送取消订单消息
     */
    public void sendCancelOrderInfo(String orderId) {
        rabbitTemplate.convertAndSend(ORDER_EXCHANGE, "cancel", orderId);
    }

    /**
     * 发送订单超时消息
     */
    public void sendTimeOutInfo(OrderTimeOutInfo timeOutInfo) {
        rabbitTemplate.convertAndSend(ORDER_EXCHANGE, TIMEOUT_ORDER_ROUTING_KEY, timeOutInfo);
    }

    /**
     * 发送库存到货消息
     */
    public void sendArriveNoticeInfo(ArriveNoticeInfo arriveNoticeInfo) {
        rabbitTemplate.convertAndSend(STORAGE_EXCHANGE, ARRIVE_NOTICE_ROUTING_KEY, arriveNoticeInfo);
    }
}
