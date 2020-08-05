package com.zr.rabbitmqdemo.service.impl;

import com.zr.rabbitmqdemo.dto.ArriveNoticeInfo;
import com.zr.rabbitmqdemo.producer.MessageProducer;
import com.zr.rabbitmqdemo.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author zhangrRabbitListener
 * 2020/7/23 16:34
 */
@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private MessageProducer producer;

    @Override
    public String arriveNotice(ArriveNoticeInfo arriveNoticeInfo) {
        //库存到货业务处理，省略
        //发送消息进行其他业务（短信提醒啊或者其他不需要同步实时的）
        producer.sendArriveNoticeInfo(arriveNoticeInfo);
        return "库存到货处理完成";
    }
}
