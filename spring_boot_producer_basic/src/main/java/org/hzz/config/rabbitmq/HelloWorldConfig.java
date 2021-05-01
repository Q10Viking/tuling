package org.hzz.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {
    public static final String QUEUE_HELLO_WORLD = "queue_hello_world";

    @Bean
    public Queue helloWorldQueue(){
        return QueueBuilder.durable(QUEUE_HELLO_WORLD).build();
    }
}
