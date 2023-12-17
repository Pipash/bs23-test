package com.user.service.amqp.server.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.user.service.amqp.server.configs.RabbitMqConfig;

@Component
@Slf4j
public class UserServiceAMQPServerController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMqConfig.MESSAGE_QUEUE)
    public void process(Message message) {
        byte[] body = message.getBody();
        log.info("server "+new String(body));
    }
}
