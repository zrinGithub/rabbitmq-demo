package com.zr.rabbitmqdemo.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/23 16:09
 */
@Configuration
@Slf4j
public class RabbitMqConfig {
    //队列：取消订单
    public final static String CANCEL_ORDER_QUEUE = "cancel_order_queue";
    public final static String CANCEL_ORDER_ROUTING_KEY = "cancel";
    //队列：订单超时提示
    public final static String TIMEOUT_ORDER_QUEUE = "timeout_order_queue";
    public final static String TIMEOUT_ORDER_ROUTING_KEY = "timeout";
    //队列：库存到货提示
    public final static String ARRIVE_NOTICE_QUEUE = "arrive_notice_queue";
    public final static String ARRIVE_NOTICE_ROUTING_KEY = "arrive";
    //交换器：订单中心
    public final static String ORDER_EXCHANGE = "order_exchange";
    //交换器：库存中心
    public final static String STORAGE_EXCHANGE = "storage_exchange";

    //----------------------------定义Exchange--------------------------------------------
    //我们使用直接发送的方式，默认接收方轮询处理
    @Bean
    public DirectExchange orderExchange() {
        return ExchangeBuilder.directExchange(ORDER_EXCHANGE).durable(true).build();
    }

    @Bean
    public DirectExchange storageExchange() {
        return ExchangeBuilder.directExchange(STORAGE_EXCHANGE).durable(true).build();
    }

    //----------------------------定义Queue--------------------------------------------
    @Bean
    public Queue cancelOrderQueue() {
        return new Queue(CANCEL_ORDER_QUEUE);
    }

    @Bean
    public Queue timeoutOrderQueue() {
        return new Queue(TIMEOUT_ORDER_QUEUE);
    }

    @Bean
    public Queue arriveNoticeQueue() {
        return new Queue(ARRIVE_NOTICE_QUEUE);
    }

    //----------------------------定义Bind--------------------------------------------
    @Bean
    public Binding cancelOrderBind() {
        return BindingBuilder.bind(cancelOrderQueue()).to(orderExchange()).with(CANCEL_ORDER_ROUTING_KEY);
    }

    @Bean
    public Binding timeoutOrderBind() {
        return BindingBuilder.bind(timeoutOrderQueue()).to(orderExchange()).with(TIMEOUT_ORDER_ROUTING_KEY);
    }

    @Bean
    public Binding arriveNoticeBind() {
        return BindingBuilder.bind(arriveNoticeQueue()).to(storageExchange()).with(ARRIVE_NOTICE_ROUTING_KEY);
    }


    @Resource
    private CachingConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        this.connectionFactory.setPublisherConfirms(true);
        this.connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory);
        //消息路径 producer->rabbitmq broker cluster->exchange->queue->consumer
        //confirm只能保证消息到达broker或者cluster，
        //确认回调函数
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                log.info("消息投递到exchange成功：({}),ack({}),cause({})", new Object[]{JSONObject.toJSONString(correlationData), Boolean.valueOf(ack), cause}));
        //开启强制委托模式
        rabbitTemplate.setMandatory(true);
        //未投递到queue退回
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
                log.info("消息投递到queue失败：({}),route({}),replyCode({}),replyText({}),message:{}", new Object[]{exchange, routingKey, Integer.valueOf(replyCode), replyText, message}));
        return rabbitTemplate;
    }

}
