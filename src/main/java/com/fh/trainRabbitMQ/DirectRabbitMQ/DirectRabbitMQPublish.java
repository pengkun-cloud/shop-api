//package com.fh.trainRabbitMQ.DirectRabbitMQ;
//
//import com.fh.common.ServerResponse;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("directPublish")
//public class DirectRabbitMQPublish {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    //生产者生产消息
//    @RequestMapping("direct")
//    public ServerResponse directExchange(){
//        String msgA = "直接交换器A";
//        rabbitTemplate.convertAndSend("directExchange","ARoutingKey",msgA);
//        String msgB = "直接交换器B";
//        rabbitTemplate.convertAndSend("directExchange","BRoutingKey",msgB);
//        return ServerResponse.success();
//    }
//
//}
