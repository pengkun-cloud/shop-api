//package com.fh.testRabbitMQ.testConsumer;
//
//import com.rabbitmq.client.*;
//
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
//public class TestConsumer {
//    public static void main(String[] args) {
//        test();
//    }
//
//    public static void test(){
//
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("192.168.205.128");
//        factory.setPort(AMQP.PROTOCOL.PORT);    // 5672
//        factory.setUsername("pengkun");
//        factory.setPassword("123456");
//
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
//            System.out.println("Consumer Wating Receive Message");
//
//            Consumer consumer = new DefaultConsumer(channel) {
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
//                                           byte[] body) throws IOException {
//                    String message = new String(body, "UTF-8");
//                    System.out.println("[Consumer] Received:'" + message + "'");
//                }
//            };  ;
//            // 订阅消息
//            channel.basicConsume(QUEUE_NAME, true, consumer);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//    }
//}
