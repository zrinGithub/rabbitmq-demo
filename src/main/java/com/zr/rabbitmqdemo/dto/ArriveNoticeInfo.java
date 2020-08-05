package com.zr.rabbitmqdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * 商品到货信息
 *
 * @author zhangr
 * 2020/8/5 8:56
 */
@Data
@ToString
@ApiModel("商品到货信息")
public class ArriveNoticeInfo implements Serializable {
    private static final long serialVersionUID = -2332834816490892218L;

    @ApiModelProperty("sku id")
    private String skuId;

    @ApiModelProperty("spu id")
    private String spuId;

    @ApiModelProperty("库存数量")
    private Integer storageNum;
}
