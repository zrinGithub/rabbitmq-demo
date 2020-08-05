package com.zr.rabbitmqdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * 订单超时信息
 *
 * @author zhangr
 * 2020/8/5 8:56
 */
@Data
@ToString
@ApiModel("订单超时信息")
public class OrderTimeOutInfo implements Serializable {
    private static final long serialVersionUID = 2720989695007283465L;
    
    @ApiModelProperty("订单id")
    private String orderId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("处理消息")
    private String message;
}
