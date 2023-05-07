package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.Interface.MailServerImpl;
import com.example.springbootmybatis.mapper.MailServer;
import com.example.springbootmybatis.mapper.UserMapper;
import com.example.springbootmybatis.pojo.user;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

@Controller
public class BindMailController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailServer mailServer;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("/tosendmail")
    public String tosendmail(@RequestParam("email") String email,
                             Model model, HttpServletRequest httpServletRequest, HttpSession session,
                             HttpServletResponse httpServletResponse) {


        //生产验证码字符串并保存到session中
        String createText = defaultKaptcha.createText();
        httpServletRequest.getSession().setAttribute("vrifyCode", createText);

        String madilcodeId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");

        mailServer.sendMailServer(email, "邮箱验证码","你的验证码是:" + madilcodeId);

        String userwho = (String) session.getAttribute("userwho");

        userMapper.updateEmailcode(userwho,madilcodeId);

        model.addAttribute("sendmailmsg", "验证码已发送到邮箱");

        return "operate/BindMail";
}


    @RequestMapping("/tobindmail")
    public String tobindmail(@RequestParam("username") String username,@RequestParam("email") String email,
                             Model model, HttpServletRequest httpServletRequest, HttpSession session,
                             HttpServletRequest request) {
//        System.out.println(username + " is binding mail");

        model.addAttribute("bindmailmsg", "验证码已发送到邮箱");

        boolean mailcodeflag;
        String mailcodeId = userMapper.queryCodeByName(username);
        String parameter = httpServletRequest.getParameter("vrifyCode");
        System.out.println("Session vrifyCode " + mailcodeId + " form vrifyCode " + parameter);

        if (!mailcodeId.equalsIgnoreCase(parameter)) {
            mailcodeflag = false;
        } else {
            mailcodeflag = true;
        }

        Object loginUser = request.getSession().getAttribute("userwho");

        String userwho = (String) session.getAttribute("userwho");


        if (loginUser != null && mailcodeflag && userwho.equals(username)) {

            System.out.println("username " + username + " email " + email);
            userMapper.updateMail(username,email);
            model.addAttribute("bindmailmsg", "绑定邮箱成功");
            return "operate/BindMail";   //绑定成功

        } else if (!mailcodeflag) {



            model.addAttribute("bindmailmsg", "验证码错误");
            return "operate/BindMail";
        }
        else if (!userwho.equals(username)) {
            model.addAttribute("bindmailmsg", "用户名输入错误");
            return "operate/BindMail";}
        else {
            model.addAttribute("bindmailmsg", "没有权限请先登录");
            return "redirect:/index.html";
        }
    }


}
