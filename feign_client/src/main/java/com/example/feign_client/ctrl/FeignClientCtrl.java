package com.example.feign_client.ctrl;

import com.example.feign_client.service.IFeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignClientCtrl {

    private final IFeignClientService service;

    @Autowired
    public FeignClientCtrl(IFeignClientService service) {
        this.service = service;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(@RequestParam(value = "a")Integer a, @RequestParam(value = "b")Integer b) {
        return service.add(a, b);
    }
}
