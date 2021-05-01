package org.hzz.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class DirectListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("spring_direct_queue: "+new String(message.getBody()));
    }
}
