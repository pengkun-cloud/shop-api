//package com.fh.trainRabbitMQ.TopicRabbitMQ;
//
//import com.sun.org.apache.regexp.internal.RE;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TopicRabbitMQConfig {
//
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange("topicExchange");
//    }
//
//
//    @Bean
//    public Queue queueM(){
//
//        return new Queue("queueM", true);
//    }
//
//    @Bean
//    public Queue queueN(){
//
//        return new Queue("queueN", true);
//    }
//
//    @Bean
//    public Queue queueS(){
//
//        return new Queue("queueS", true);
//    }
//
//    @Bean
//    public Binding bindingQueueM(){
//        return BindingBuilder.bind(queueM()).to(topicExchange()).with("*.fh.*");
//    }
//
//    @Bean
//    public Binding bindingQueueN(){
//        return BindingBuilder.bind(queueN()).to(topicExchange()).with("*.fh.com");
//    }
//
//    @Bean
//    public Binding bindingQueueS(){
//        return BindingBuilder.bind(queueS()).to(topicExchange()).with("*.xx.*");
//    }
//
//}
