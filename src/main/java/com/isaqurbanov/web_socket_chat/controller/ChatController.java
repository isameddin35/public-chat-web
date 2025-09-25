package com.isaqurbanov.web_socket_chat.controller;

import com.isaqurbanov.web_socket_chat.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendPrivate")
    public void sendPrivate(@Payload ChatMessage chatMessage) {
        messagingTemplate.convertAndSend(
                "/queue/private." + chatMessage.getRecipient(),
                chatMessage
        );
    }
}