package com.test.user.controller;

import com.test.user.config.RabbitMQConfig;
import com.test.user.model.User;
import com.test.user.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/publish")
public class PublisherController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private AuthService authService;

    @GetMapping("/send-user-info/{id}")
    public String sendUserInfo(@PathVariable Long id) {
        final String correlationId = UUID.randomUUID().toString();
        log.info("getting a single user started in sendUserInfo of PublisherController");
        User user = authService.getUser(id);
        log.info("getting a single user complete in sendUserInfo of PublisherController");
        // Create a message subject
        Message newMessage = MessageBuilder.withBody(user.toString().getBytes()).build();
        //The publisher sends a message
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage, m-> {
            m.getMessageProperties().setCorrelationId(correlationId);
            return m;
        });

        return "message send successfully!";
    }
}
