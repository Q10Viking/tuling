package org.hzz.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class FanoutListener1 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("spring_fanout_queue_1: "+new String(message.getBody()));
    }
}
