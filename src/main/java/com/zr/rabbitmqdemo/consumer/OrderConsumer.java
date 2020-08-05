package com.zr.rabbitmqdemo.consumer;

import com.zr.rabbitmqdemo.dto.OrderTimeOutInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.zr.rabbitmqdemo.config.RabbitMqConfig.*;

/**
 * Description:
 * 订单类的消息消费者
 * 这里只是简单例子，省去消息处理中的业务部分，使用打印来代替
 *
 * @author zhangr
 * 2020/8/5 9:06
 */
@Component
@RabbitListener(queues = {CANCEL_ORDER_QUEUE, TIMEOUT_ORDER_QUEUE})
@Slf4j
public class OrderConsumer {
    @RabbitHandler
    public void cancelOrderHandler(String orderId) {
        try {
            log.info("取消订单 订单编号：{}", orderId);
        } catch (Exception e) {
            log.error("取消订单失败 订单编号：{}", orderId);
        }
    }

    @RabbitHandler
    public void timeOutHandler(OrderTimeOutInfo timeOutInfo) {
        try {
            Thread.sleep(10000);
            log.info("订单超时：{}", timeOutInfo);
        } catch (Exception e) {
            log.error("订单超时消费信息 {},出现异常 {}", timeOutInfo, e.getMessage());
        }
    }
}
