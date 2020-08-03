//package com.fh.trainRabbitMQ.FanoutRabbitMQ;
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
//@RestController
//@RequestMapping("fanoutPublish")
//public class FanoutRabbitMQPublish {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @RequestMapping("fanout")
//    public ServerResponse fanout(){
//        String msgFanout = "fanoutABC";
//        rabbitTemplate.convertAndSend("fanoutExchange","aaa", msgFanout);
//        return ServerResponse.success();
//    }
//
//    @RabbitListener(queues = "fanout.c")
//    @RabbitHandler
//    public void receiveC(Channel channel, String msgFanout, Message message){
//        System.out.println(msgFanout);
//
//    }
//
//    @RabbitListener(queues = "fanout.d")
//    @RabbitHandler
//    public void receiveD(Channel channel, String msgFanout, Message message){
//        System.out.println(msgFanout);
//
//    }
//
//    @RabbitListener(queues = "fanout.e")
//    @RabbitHandler
//    public void receiveE(Channel channel, String msgFanout, Message message){
//        System.out.println(msgFanout);
//
//    }
//
//}
