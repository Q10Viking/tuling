package org.hzz;

import org.hzz.config.rabbitmq.TopicConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void topic(){
        rabbitTemplate.convertAndSend(TopicConfig.EXCHANGE_NAME,"hzz.hello.world","spring boot hello topic");
    }
}
