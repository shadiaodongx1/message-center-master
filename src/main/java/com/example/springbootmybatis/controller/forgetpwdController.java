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
public class forgetpwdController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailServer mailServer;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("/toforgetpwd")
    public String forgetpwd(@RequestParam("username") String username,
                              @RequestParam("email") String email,
                             Model model, HttpServletRequest httpServletRequest, HttpSession session,
                             HttpServletResponse httpServletResponse) {

        System.out.println(username);
        System.out.println(email);

        user user = userMapper.queryUserByNE(username,email);


        if(user!=null)
        {
            session.setAttribute("userwho",user.getUsername());

            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();

            httpServletRequest.getSession().setAttribute("vrifyCode", createText);

            String madilcodeId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");

            mailServer.sendMailServer(email, "邮箱验证码","你的验证码是:" + madilcodeId);

            userMapper.updateEmailcode(username,madilcodeId);

            return "resetpwd";
        }
       else
        {
            model.addAttribute("forgetpwdmsg", "用户名或邮箱错误");

            return "forgetpwd";
        }
    }


    @RequestMapping("/toresetpwd")
    public String resetpwd(
            //@RequestParam("username") String username,
                            @RequestParam("vrifyCode") String emailcode,
                             @RequestParam("password") String password,
                             @RequestParam("password1") String password1,
                             Model model, HttpServletRequest httpServletRequest, HttpSession session,
                             HttpServletRequest request) {
//        System.out.println(username + " is binding mail");



        String username = (String) session.getAttribute("userwho");
        session.invalidate();
//        user user = userMapper.queryUserByName(username);

        boolean mailcodeflag;
        String mailcodeId = userMapper.queryCodeByName(username);
//        String parameter = httpServletRequest.getParameter("vrifyCode");

        System.out.println(username + " is changing password");

        System.out.println("Session vrifyCode " + mailcodeId + " form vrifyCode " + emailcode);

        if (!mailcodeId.equalsIgnoreCase(emailcode)) {
            mailcodeflag = false;
        } else {
            mailcodeflag = true;
        }

            if(password.length()>=8
                    && PwdCheckUtil.checkContainCase(password)
                    && PwdCheckUtil.checkContainDigit(password)
                    && PwdCheckUtil.checkContainSpecialChar(password)
            )
            {

                if (!mailcodeflag)
                {
                    model.addAttribute("resetpwdmsg","验证码输入错误");
                    return "resetpwd";
                }
                else{
                    if (password.equals(password1)){

                        String md5password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

                        userMapper.updatePassword(username,md5password);

                        model.addAttribute("resetpwdmsg","密码已重置，请重新登录");
                        return "resetpwd";

                    }
                    else {
                        model.addAttribute("resetpwdmsg","两次密码不一致，请重新输入");
                        return "resetpwd";
                    }}


            }
            else
            {

                model.addAttribute("resetpwdmsg","密码需要有特殊字符，字母，数字，且长度大于8位");
                return "resetpwd";
            }



    }

}
