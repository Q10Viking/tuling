package org.hzz.service;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.hzz.entity.Order;
import org.hzz.entity.Product;
import org.hzz.mapper.OrderMapper;
import org.hzz.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public void reduceStock(Integer id){
        //  获取库存
        Product product = productMapper.getProduct(id);
        //  模拟耗时业务
        sleep(5000*1000);
        if (product.getStock() <=0 ){
            throw new RuntimeException("out of stock");
        }

        // 减库存
        int i = productMapper.deductStock(id);
        if(i==1){
            Order order = new Order();
            order.setUserId(UUID.randomUUID().toString());
            order.setPid(id);
            orderMapper.insert(order);
        }else{
            throw new RuntimeException("deduct stock fail, retry.");
        }
    }

    /**
     * 模拟耗时业务处理
     * @param wait
     */
    public void sleep(long  wait){
        try {
            TimeUnit.MILLISECONDS.sleep( wait );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

