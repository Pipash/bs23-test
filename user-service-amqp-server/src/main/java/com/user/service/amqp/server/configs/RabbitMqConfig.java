package com.user.service.amqp.server.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String MESSAGE_QUEUE = "msg_queue";
    public static final String REPLY_MESSAGE_QUEUE = "reply_msg_queue";
    public static final String EXCHANGE = "exchange";
    public static final String REPLY_EXCHANGE = "reply_exchange";
    /** *
     * Configure the Send Message Queue
     */
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
    TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    TopicExchange replyTopicExchange() {
        return new TopicExchange(REPLY_EXCHANGE);
    }
    /** *
     * Queuing and Switch Link Request
     */
    @Bean
    Binding msgBinding() {
        return BindingBuilder.bind(msgQueue())
                .to(topicExchange())
                .with(MESSAGE_QUEUE);
    }
    /** *
     * Back to Queue and Switch Link
     */
    @Bean
    Binding replyBinding() {
        return BindingBuilder.bind(replyQueue())
                .to(replyTopicExchange())
                .with(REPLY_MESSAGE_QUEUE);
    }
}
