package com.example.feign_client.ctrl;

import com.example.feign_client.service.IFeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignClientCtrl {

    @Autowired
    private IFeignClientService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Integer add() {
        return service.add(10, 20);
    }
}
