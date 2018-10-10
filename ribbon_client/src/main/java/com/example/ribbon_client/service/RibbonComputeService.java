package com.example.ribbon_client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RibbonComputeService {
    private final RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(RibbonComputeService.class);
    @Autowired
    public RibbonComputeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public ResponseEntity<Integer> addService(Integer a, Integer b) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", a);
        map.put("b", b);
        return restTemplate.getForEntity("http://service/add?a={a}&b={b}", Integer.class, map);
    }

    public ResponseEntity<Integer> addServiceFallback(Integer a, Integer b) {
        logger.info("ribbon断路器，服务不可用。。。");
        return new ResponseEntity<>((Integer)null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
