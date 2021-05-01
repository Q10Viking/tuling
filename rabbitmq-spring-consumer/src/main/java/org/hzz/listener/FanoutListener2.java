package org.hzz.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class FanoutListener2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("spring_fanout_queue_2: "+new String(message.getBody()));
    }
}
