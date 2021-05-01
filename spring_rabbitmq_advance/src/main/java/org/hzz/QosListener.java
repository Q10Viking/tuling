package org.hzz;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

public class QosListener implements ChannelAwareMessageListener {
    public static int i = 1;
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.printf("监听到: (%d)--> %s\n",i++,message);
        if(i==1){
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            channel.basicNack(deliveryTag,false,false);
        }else{
            i++;
        }
        Thread.sleep(1000);
    }
}
