package org.hzz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHelloWorld(){
       rabbitTemplate.convertAndSend("spring_queue","Hello Spring Queue world");
    }

    @Test
    public void testPubSub(){
        rabbitTemplate.convertAndSend("spring_fanout_exchange","","(1) Hello Spring fanout Queue world");
        rabbitTemplate.convertAndSend("spring_fanout_exchange","","(2) Hello Spring fanout Queue world");
        System.out.println("发送成功");
    }

    @Test
    public void testRoutingKey(){
        rabbitTemplate.convertAndSend("spring_direct_exchange","spring.direct.queue","Hello Spring direct Queue world");
    }

    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("spring_topic_exchange","hzz.hello.world","Hello Spring topic Queue world");

    }
}
