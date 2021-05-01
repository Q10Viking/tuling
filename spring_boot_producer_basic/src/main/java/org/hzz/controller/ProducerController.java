package org.hzz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzz.config.rabbitmq.HelloWorldConfig;
import org.hzz.config.rabbitmq.TopicConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "RabbitMQ工作模式")
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "简单模式",notes = "直接返送队列")
    @GetMapping("/helloworldSend")
    public Object helloWorldSend(String message){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);

        // 发送消息
        rabbitTemplate.convertSendAndReceive(HelloWorldConfig.QUEUE_HELLO_WORLD, new Message(message.getBytes(),messageProperties));
        return "message send: "+message;
    }


    @ApiOperation(value = "主题模式",notes = "根据路由模式送队列")
    @GetMapping("/topic")
    public Object topic(String message,String routingKey){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);

        if(StringUtils.isEmpty(routingKey)){
            routingKey = "hzz.empty";
        }

        // 发送消息
        rabbitTemplate.convertSendAndReceive(TopicConfig.EXCHANGE_NAME,routingKey, new Message(message.getBytes(),messageProperties));
        return "message send: "+message;
    }

}
