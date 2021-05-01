package org.hzz.consumer;

import org.hzz.config.rabbitmq.HelloWorldConfig;
import org.hzz.config.rabbitmq.TopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerReceiver {

    @RabbitListener(queues = HelloWorldConfig.QUEUE_HELLO_WORLD)
    public void helloWorldReceive(String msg){
        System.out.println("简单模式接收： "+msg);
    }

    @RabbitListener(queues = TopicConfig.QUEUE1_NAME)
    public void topicQueue1(String msg){
        System.out.println("Topic模式queue1接收： "+msg);
    }

    @RabbitListener(queues = TopicConfig.QUEUE2_NAME)
    public void topicQueue2(String msg){
        System.out.println("Topic模式queue2接收： "+msg);
    }

}
