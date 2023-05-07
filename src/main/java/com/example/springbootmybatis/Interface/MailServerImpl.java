package com.example.springbootmybatis.Interface;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.message.SimpleMessage;
import com.example.springbootmybatis.mapper.MailServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


/**
 * @author zhangheng:
 * @version 创建时间：2022年4月26日 20:13:14
 * @Description 类描述:
 */

@Service
@Controller
public class MailServerImpl implements MailServer {

    @Value("${spring.mail.username}")
    private String fromUser;

    @Autowired
    private JavaMailSender javaMailSender;

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }


    @Override
    public void sendMailServer(String sendUser, String title, String text) {
        //创建邮件的实体 用于封装发送邮件需要的信息
        SimpleMailMessage simpleMailMessage=new  SimpleMailMessage();
        //邮件的发送人
        simpleMailMessage.setFrom(fromUser);
        //邮件接收人
        simpleMailMessage.setTo(sendUser);
        //邮件的标题
        simpleMailMessage.setSubject(title);
        //邮件的内容
        simpleMailMessage.setText(text);
        //发送邮件
        javaMailSender.send(simpleMailMessage);
    }

}