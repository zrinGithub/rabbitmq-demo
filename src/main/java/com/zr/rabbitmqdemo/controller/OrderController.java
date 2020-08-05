package com.zr.rabbitmqdemo.controller;

import com.zr.rabbitmqdemo.dto.OrderTimeOutInfo;
import com.zr.rabbitmqdemo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description:
 * 订单中心Controller
 *
 * @author zhangr
 * 2020/7/23 16:32
 */
@Api("订单中心")
@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @ApiOperation("取消订单")
    @GetMapping("v1/cancel")
    public String cancelOrder(@ApiParam("订单中心") @RequestParam("orderId") String orderId) {
        return orderService.cancelOrder(orderId);
    }

    @ApiOperation("订单超时")
    @PostMapping("v1/timeOut")
    public String timeOut(@ApiParam("订单超时请求体") @RequestBody OrderTimeOutInfo timeOutInfo) {
        return orderService.timeOut(timeOutInfo);
    }
}
