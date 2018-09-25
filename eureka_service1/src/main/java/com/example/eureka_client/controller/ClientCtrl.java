package com.example.eureka_client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientCtrl {
    @Value("${server.port}")
    private String port;

   // private final static transient Logger logger = LoggerFactory.getLogger(ClientCtrl.class);
    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "test") String name) {
        return "你好, " + name + " ,我是服务1，我的端口号是:" + port;
    }
}
