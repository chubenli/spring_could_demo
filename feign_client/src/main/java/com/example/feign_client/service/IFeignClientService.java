package com.example.feign_client.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service")
public interface IFeignClientService {
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}
