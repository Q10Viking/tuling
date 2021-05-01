package org.hzz.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class TopicListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("spring_topic_queue: "+new String(message.getBody()));
    }
}
