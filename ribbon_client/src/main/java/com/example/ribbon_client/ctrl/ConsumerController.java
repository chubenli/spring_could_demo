package com.example.ribbon_client.ctrl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerController {

    private RestTemplate restTemplate;

    @Autowired
    public ConsumerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/add/{a}/{b}", method = RequestMethod.POST)
    public String add(@PathVariable Integer a, @PathVariable Integer b) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", a);
        map.put("b", b);
        Integer integer = 0;
        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity("http://service/add?a={a}&b={b}", integer, Integer.class, map);
        JSONObject json = new JSONObject();
        json.put("result",responseEntity.getBody());
        json.put("statusCode",responseEntity.getStatusCode());
        json.put("headers",responseEntity.getHeaders());
        json.put("hasBody",responseEntity.hasBody());
        return json.toJSONString();
    }
}
