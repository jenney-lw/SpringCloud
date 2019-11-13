package com.lw.springcloud.eurekaserver.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitmqClient {

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

        //4.声明一个消息队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //5.创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //6.设置channel
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        while (true) {
            //7.获取消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery(); //无消息时候一直阻塞
            String msg = new String(delivery.getBody());
            System.out.println("收到消息:" + msg);
        }

    }

}
