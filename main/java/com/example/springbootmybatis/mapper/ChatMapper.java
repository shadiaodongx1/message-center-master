package com.example.springbootmybatis.mapper;
import com.example.springbootmybatis.pojo.ChatMessage;

import org.apache.ibatis.annotations.Mapper;




import java.util.List;

@Mapper
public interface ChatMapper {
    List<ChatMessage> queryChatRecord();


}
