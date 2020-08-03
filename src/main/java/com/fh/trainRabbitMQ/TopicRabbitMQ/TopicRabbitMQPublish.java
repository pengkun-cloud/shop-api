//package com.fh.trainRabbitMQ.TopicRabbitMQ;
//
//import com.fh.common.ServerResponse;
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("topicPublish")
//public class TopicRabbitMQPublish {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @RequestMapping("topic")
//    public ServerResponse topic(){
//        String msg1 = "mmmm";
//        rabbitTemplate.convertAndSend("topicExchange", "com.fh.com", msg1);
//        String msg2 = "nnnn";
//        rabbitTemplate.convertAndSend("topicExchange", "fh.com", msg2);
//        String msg3 = "ssss";
//        rabbitTemplate.convertAndSend("topicExchange", "xx", msg3);
//        return ServerResponse.success();
//    }
//
//
//
//    @RabbitHandler
//    @RabbitListener(queues = "queueM")
//    public void receiveE(Channel channel, String msg, Message message){
//        System.out.println(msg);
//    }
//
//
//    @RabbitHandler
//    @RabbitListener(queues = "queueN")
//    public void receiveF(Channel channel, String msg, Message message){
//        System.out.println(msg);
//
//    }
//
//
//    @RabbitHandler
//    @RabbitListener(queues = "queueS")
//    public void receiveG(Channel channel, String msg, Message message){
//        System.out.println(msg);
//    }
//
//}
