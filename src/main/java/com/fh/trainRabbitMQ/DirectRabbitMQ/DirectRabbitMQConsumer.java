//package com.fh.trainRabbitMQ.DirectRabbitMQ;
//
//
//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.impl.AMQImpl;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//
//import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
//import org.springframework.stereotype.Component;
//
//import java.io.UnsupportedEncodingException;
//
//
//@Component
//public class DirectRabbitMQConsumer {
//
//    @RabbitListener(queues = "queueA")
//    @RabbitHandler
//    public void receiverA(Channel channel, String msgA, Message message){
//        System.out.println("收到消息："+msgA);
//    }
//
//    @RabbitListener(queues = "queueA")
//    @RabbitHandler
//    public void receiver(Channel channel, String msg, Message message){
//        System.out.println("收到消息："+msg);
//    }
//
//    @RabbitListener(queues = "queueB")
//    @RabbitHandler
//    public void receiverB(Channel channel, String msgB, Message message){
//        System.out.println("收到消息："+msgB);
//    }
//
//}
