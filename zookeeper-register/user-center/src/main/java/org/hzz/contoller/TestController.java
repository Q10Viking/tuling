package org.hzz.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public Object test(){
        //  通过指定服务名称，而不是具体的IP地址类获取服务
        String forObject = restTemplate.getForObject("http://product-center/getInfo", String.class);
        return forObject;
    }
}
