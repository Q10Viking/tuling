package org.hzz.topic;

import com.rabbitmq.client.*;
import org.hzz.utils.RabbitConstant;
import org.hzz.utils.RabbitUtils;

import java.io.IOException;

public class Sina {
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitConstant.QUEUE_SINA,false,false,false,null);
        //queueBind用于将队列与交换机绑定
        //参数1：队列名 参数2：交互机名  参数三：路由key（暂时用不到)
        channel.queueBind(RabbitConstant.QUEUE_SINA,RabbitConstant.EXCHANGE_WEATHER_TOPIC,"*.*.*.20201127");

        channel.basicQos(1);
        channel.basicConsume(RabbitConstant.QUEUE_SINA,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("新浪："+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
/**
 新浪：中国湖南长沙20201127天气数据
 新浪：中国湖北武汉20201127天气数据
 新浪：中国湖南株洲20201127天气数据
 新浪：美国加州洛杉矶20201127天气数据
 */