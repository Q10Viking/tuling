package org.hzz.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.hzz.utils.RabbitConstant;
import org.hzz.utils.RabbitUtils;

public class WeatherBureau {
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER,"",null,"北京今天26°，良好".getBytes());

        channel.close();
        connection.close();
    }
}
