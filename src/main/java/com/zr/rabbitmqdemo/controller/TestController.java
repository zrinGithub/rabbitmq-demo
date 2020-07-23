package com.zr.rabbitmqdemo.controller;

import com.zr.rabbitmqdemo.service.MsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author zhangr
 * 2020/7/23 16:32
 */
@Api("TestController")
@RestController
@Slf4j
public class TestController {
    @Resource
    private MsgService msgService;

    @ApiOperation("test")
    @GetMapping("test")
    public String test(@ApiParam("msg") @RequestParam("msg") String msg) {
        return msgService.test(msg);
    }
}
