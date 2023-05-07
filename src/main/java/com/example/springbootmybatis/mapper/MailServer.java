package com.example.springbootmybatis.mapper;


public interface MailServer {
    /**
     * @param sendUser 邮件接收人
     * @param title 邮件的标题
     * @param text  邮件的内容
     */
    void sendMailServer(String sendUser,String title,String text);


}