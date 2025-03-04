package com.example.ExternalApi.controller;

import com.example.ExternalApi.config.MQConfig;
import com.example.ExternalApi.dto.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RabbitMqController {

    private RabbitTemplate template;

    public RabbitMqController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/publish")
    public void publish(@RequestBody Message message) {
        template.convertAndSend(MQConfig.MY_EXCHANGE, MQConfig.MY_ROUTING_KEY, message);
    }

    @GetMapping("/receive")
    public List<Message> readMessagesFromQueue() {
        List<Message> messages = new ArrayList<>();
        Message message;
        while ((message = (Message) template.receiveAndConvert(MQConfig.MY_QUEUE)) != null) {
            messages.add(message);
        }
        return messages;
    }
}
