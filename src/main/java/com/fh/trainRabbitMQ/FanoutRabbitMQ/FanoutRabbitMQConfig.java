//package com.fh.trainRabbitMQ.FanoutRabbitMQ;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FanoutRabbitMQConfig {
//
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange("fanoutExchange");
//    }
//
//    @Bean
//    public Queue queueC(){
//
//        return new Queue("fanout.c");
//    }
//
//    @Bean
//    public Queue queueD(){
//
//        return new Queue("fanout.d");
//    }
//
//    @Bean
//    public Queue queueE(){
//        return new Queue("fanout.e");
//    }
//
//    @Bean
//    public Binding bindingQueueC(){
//
//        return BindingBuilder.bind(queueC()).to(fanoutExchange());
//    }
//
//
//    @Bean
//    public Binding bindingQueueD(){
//
//        return BindingBuilder.bind(queueD()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding bindingQueueE(){
//
//        return BindingBuilder.bind(queueE()).to(fanoutExchange());
//    }
//
//}
