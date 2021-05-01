package org.hzz;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

public class AckListener implements ChannelAwareMessageListener {
    public static int i = 1;
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.printf("监听到: (%d)--> %s\n",i++,message);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try{
            //  处理业务
            int d = 5/0;
            channel.basicAck(deliveryTag,false);
        }catch(Exception e){
            //拒绝签收
             /*
            第三个参数：requeue：重回队列。如果设置为true，则消息重新回到queue，broker会重新发送该消息给消费端
             */
            channel.basicNack(deliveryTag,false,false);
        }
    }
}
