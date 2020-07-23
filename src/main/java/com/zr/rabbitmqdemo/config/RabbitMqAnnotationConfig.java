package com.zr.rabbitmqdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/23 16:23
 */
@Component
@Slf4j
public class RabbitMqAnnotationConfig {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "topic_queue", durable = "true"),
                    exchange = @Exchange(value = "topic_exchange", type = ExchangeTypes.TOPIC),
                    key = "keyA.*")})
    public void receiveMqExchangeInfo(String msg) {
        log.info("接受到topic_queue的消息" + msg);
    }

}
