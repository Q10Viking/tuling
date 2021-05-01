package org.hzz.controller;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.hzz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private OrderService orderService;

    @Value("${server.port}")
    private String port;

    @Autowired
    private CuratorFramework curatorFramework;

    @GetMapping("/hello")
    public Object hello(){
        return "hello World";
    }

    @PostMapping("/stock/deduct")
    public Object reduceStock(Integer id) throws Exception{
        System.out.println("product_"+id);
        //InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, "/product_"+id);
        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/product_"+id);
       try{
           //interProcessReadWriteLock.readLock().acquire();
            interProcessMutex.acquire();
            orderService.reduceStock(id);

       }catch (Exception e){
           if(e instanceof  RuntimeException){
               throw e;
           }
       }finally {
        interProcessMutex.release();
       }
        return "OK "+port;
    }
}
