package com.zr.rabbitmqdemo.service;

import com.zr.rabbitmqdemo.dto.ArriveNoticeInfo;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/23 16:33
 */
public interface StorageService {
    //库存到货信息
    String arriveNotice(ArriveNoticeInfo arriveNoticeInfo);
}
