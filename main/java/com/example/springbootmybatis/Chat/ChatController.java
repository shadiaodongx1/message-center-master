package com.example.springbootmybatis.Chat;

import com.example.springbootmybatis.pojo.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, @Payload ChatMessage message, Principal principal) {
        String from = principal.getName();
        message.setSender(from);
        message.setReceiver(to);
        chatService.addChatRecord(to,message);
        messagingTemplate.convertAndSendToUser(to, "/topic/chat", message);
    }


    @GetMapping("/messages/{from}/{to}")
    @ResponseBody
    public List<ChatMessage> getMessages(@PathVariable String from, @PathVariable String to) {
        return chatService.getChatRecords(from, to);
    }


}

