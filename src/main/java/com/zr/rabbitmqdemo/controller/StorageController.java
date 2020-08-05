package com.zr.rabbitmqdemo.controller;

import com.zr.rabbitmqdemo.dto.ArriveNoticeInfo;
import com.zr.rabbitmqdemo.service.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description:
 * 库存中心Controller
 *
 * @author zhangr
 * 2020/7/23 16:32
 */
@Api("库存中心")
@RestController
@RequestMapping("storage")
@Slf4j
public class StorageController {
    @Resource
    private StorageService storageService;

    @ApiOperation("库存到货")
    @PostMapping("v1/arriveNotice")
    public String arriveNotice(@ApiParam("库存到货信息") @RequestBody ArriveNoticeInfo arriveNoticeInfo) {
        return storageService.arriveNotice(arriveNoticeInfo);
    }

}
