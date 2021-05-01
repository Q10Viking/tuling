package org.hzz.config.rabbitmq;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class TopicConfig {
    public static final String EXCHANGE_NAME = "boot_topic_exchange";
    public static final String QUEUE1_NAME = "boot_topic_queue1";
    public static final String QUEUE2_NAME = "boot_topic_queue2";

    //  声明交换机
    @Bean("bootExchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).build();
    }

    //  声明队列
    @Bean("bootQueue1")
    public Queue bootQueue1(){
        return QueueBuilder.durable(QUEUE1_NAME).build();
    }

    @Bean("bootQueue2")
    public Queue bootQueue2(){
        return QueueBuilder.durable(QUEUE2_NAME).build();
    }


    //  队列与交换价绑定
    @Bean
    public Binding bindExchangeToQueue1(
            @Qualifier("bootQueue1") Queue queue,
            @Qualifier("bootExchange") Exchange exchange

    ){
        return BindingBuilder.bind(queue).to(exchange).with("hzz.#").noargs();
    }

    @Bean
    public Binding bindExchangeToQueue2(
            @Qualifier("bootQueue2") Queue queue,
            @Qualifier("bootExchange") Exchange exchange

    ){
        return BindingBuilder.bind(queue).to(exchange).with("topic.*").noargs();
    }
}
