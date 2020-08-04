package com.zr.rabbitmqdemo.service.impl;

import com.zr.rabbitmqdemo.service.MsgService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author zhangrRabbitListener
 * 2020/7/23 16:34
 */
@Service
public class MsgServiceImpl implements MsgService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public String test(String msg) {
        Object receive = rabbitTemplate.convertSendAndReceive("topic_exchange", "keyA.A", msg);
        return receive == null ? "hello word!" : receive.toString();
    }
}
