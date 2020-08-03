//package com.fh.testRabbitMQ.testPublish;
//
//
//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
//public class TestPublish {
//
//    public static void main(String[] args) {
//        test();
//    }
//
//    public static void test(){
//        // 创建连接工厂
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("192.168.205.128");
//        factory.setPort(AMQP.PROTOCOL.PORT);    // 5672
//        factory.setUsername("pengkun");
//        factory.setPassword("123456");
//        try {
//            // 新建一个长连接
//            Connection connection = factory.newConnection();
//
//            // 创建一个通道(一个轻量级的连接)
//            Channel channel = connection.createChannel();
//
//            // 声明一个队列
//            String QUEUE_NAME = "hello";
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//            // 发送消息到队列中
//            String message = "Hello RabbitMQ!";
//            // 注意：exchange如果不需要写成空字符串，routingKey和队列名称保持一致
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
//            // 关闭资源
//            channel.close();
//            connection.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
