package com.example.eureka_service1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientCtrl {


    private final static transient Logger logger = LoggerFactory.getLogger(ClientCtrl.class);

    @Value("${server.port}")
    private String port;

    @Autowired
    private LoadBalancerClient balancerClient;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "test") String name) {
        return "你好, " + name + " ,我是服务2，我的端口号是:" + port;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        //List<String> services = client.getServices();
        //for (String service : services) {
        //    List<ServiceInstance> instances = client.getInstances(service);
        //    for (ServiceInstance instance : instances) {
        //        System.out.println(instance);
        //    }
        //
        //}
        ServiceInstance service = balancerClient.choose("service");
        Integer r = a + b;
        logger.info("/add, host:" + service.getHost() + ", service_id:" + service.getServiceId() + ", result:" + r);
        return r;
    }
}
