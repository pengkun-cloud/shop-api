package com.fh.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class rabbitMQConfig {

    //普通交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }
    //死信交换机
    @Bean
    public DirectExchange ttlDirectExchange(){
        return new DirectExchange("ttlDirectExchange");
    }
    //普通队列
    @Bean
    public Queue queueA(){
        return new Queue("queueA", true);
    }
    //死信队列
    @Bean
    public Queue ttlQueueA(){
        return new Queue("ttlQueueA", true);
    }

    //绑定普通交换机
    @Bean
    public Binding BindA() {
        return BindingBuilder.bind(queueA()).to(directExchange()).with("ARoutingKey");
    }
    //绑定死信交换机
    @Bean
    public Binding BindTtlA() {
        return BindingBuilder.bind(ttlQueueA()).to(directExchange()).with("ttlARoutingKey");
    }


    @Bean
    public CustomExchange dlxExchange() {
        // 死信路由特殊参数
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct"); // 死信路由方式 可选 direct/topic/fanout
        //属性参数： 交换机名称 交换机类型 是否持久化 是否自动删除 配置参数
        return new CustomExchange("dlx-ex", "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue dlxQueue() {
        return new Queue("dlx", true);
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with("dlx").noargs();
    }

}
