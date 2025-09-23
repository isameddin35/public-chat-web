package com.isaqurbanov.web_socket_chat.controller;

import com.isaqurbanov.web_socket_chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage chatEndpoint(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}
