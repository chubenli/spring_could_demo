package com.example.feign_client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class FeignClientHystrix implements IFeignClientService  {
    private Logger logger = LoggerFactory.getLogger(IFeignClientService.class);
    @Override
    public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        logger.info("feign断路器，服务不可用。。。");
        return -999999;
    }
}
