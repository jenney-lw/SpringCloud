package com.lw.springcloud.eurekaserver.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitmqServer {

    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws Exception {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2.通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        //3.通过connection创建channel
        Channel channel = connection.createChannel();

        //4.通过channel发送数据
        for (int i = 0; i < 10; i++) {
            String msg = "RabbitMQ:" + (i+1);
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }

        //5.关闭连接
        channel.close();
        connection.close();

    }

}
