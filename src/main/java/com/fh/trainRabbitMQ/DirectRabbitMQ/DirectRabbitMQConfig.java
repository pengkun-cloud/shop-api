//package com.fh.trainRabbitMQ.DirectRabbitMQ;
//
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DirectRabbitMQConfig {
//
//    @Bean
//    public DirectExchange directExchange(){
//        return new DirectExchange("directExchange");
//    }
//
//    @Bean
//    public Queue queueA(){
//        return new Queue("queueA");
//    }
//
//    @Bean
//    public Queue queueB(){
//        return new Queue("queueB");
//    }
//
//    @Bean
//    public Binding BindA() {
//        return BindingBuilder.bind(queueA()).to(directExchange()).with("ARoutingKey");
//    }
//
//    @Bean
//    public Binding BindB() {
//        return BindingBuilder.bind(queueB()).to(directExchange()).with("BRoutingKey");
//    }
//}
