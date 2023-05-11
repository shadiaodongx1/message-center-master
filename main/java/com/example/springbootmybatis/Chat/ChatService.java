package com.example.springbootmybatis.Chat;

import com.example.springbootmybatis.pojo.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private Map<String, List<ChatMessage>> chatRecordsMap = new HashMap<>();

    public void addChatRecord(String user, ChatMessage message) {
        List<ChatMessage> chatRecords = chatRecordsMap.getOrDefault(user, new ArrayList<>());
        chatRecords.add(message);
        chatRecordsMap.put(user, chatRecords);
    }

    public List<ChatMessage> getChatRecords(String user, String to) {
        return chatRecordsMap.getOrDefault(user, new ArrayList<>());
    }

}


