package com.zr.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/23 16:09
 */
@Configuration
public class RabbitMqClassConfig {
    private final static String TOPIC_QUEUE_NAME = "topic_queue";
    private final static String FANOUT_QUEUE_NAME = "fanout_queue";
    private final static String TOPIC_EXCHANGE = "topic_exchange";
    private final static String FANOUT_EXCHANGE = "fanout_exchange";
    public final static String TOPIC_ROUTE_KEY_A = "keyA.*";

    @Bean
    public Queue topicQueue() {
        return new Queue(TOPIC_QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange topExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Queue fanoutQueue() {
        return new Queue(FANOUT_QUEUE_NAME, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding topExchangeBinging() {
        return BindingBuilder.bind(topicQueue()).to(topExchange()).with(TOPIC_ROUTE_KEY_A);
    }

    @Bean
    public Binding fanoutExchangeBinging() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }
}
