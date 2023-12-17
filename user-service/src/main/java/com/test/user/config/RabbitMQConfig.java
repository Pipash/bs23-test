package com.test.user.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String MESSAGE_QUEUE = "msg_queue";
    public static final String REPLY_MESSAGE_QUEUE = "reply_msg_queue";
    public static final String EXCHANGE = "exchange";
    /** *
     * Set sending RPCQueue message
     Configure the Send Message Queue*/
    @Bean
    Queue msgQueue() {

        return new Queue(MESSAGE_QUEUE);
    }
    /** *
     * Return Queue Configuration
     */
    @Bean
    Queue replyQueue() {

        return new Queue(REPLY_MESSAGE_QUEUE);
    }
    /** *
     * Switch setting
     */
    @Bean
    TopicExchange exchange() {

        return new TopicExchange(EXCHANGE);
    }
    /** *
     * Queuing and Switch Link Request
     */
    @Bean
    Binding msgBinding() {

        return BindingBuilder.bind(msgQueue()).to(exchange()).with(MESSAGE_QUEUE);
    }
}
