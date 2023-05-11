package com.example.springbootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChatMessage {
    private Long   id;
    private String sender;
    private String receiver;
    private String content;
    private Date   sendTime;
}
