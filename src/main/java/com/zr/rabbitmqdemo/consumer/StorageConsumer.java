package com.zr.rabbitmqdemo.consumer;

import com.zr.rabbitmqdemo.dto.ArriveNoticeInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.zr.rabbitmqdemo.config.RabbitMqConfig.ARRIVE_NOTICE_QUEUE;

/**
 * Description:
 * 库存类信息消费者
 * 这里只是简单例子，省去消息处理中的业务部分，使用打印来代替
 *
 * @author zhangr
 * 2020/8/5 9:06
 */
@Component
@Slf4j
public class StorageConsumer {
    @RabbitListener(queues = {ARRIVE_NOTICE_QUEUE})
    public void arriveNotice(ArriveNoticeInfo arriveNoticeInfo) {
        try {
            log.info("库存到货提醒 {}", arriveNoticeInfo);
            Thread.sleep(5000);
        } catch (Exception e) {
            log.error("库存到货提醒 {} 出现异常 {}", arriveNoticeInfo, e.getMessage());
        }
    }
}
