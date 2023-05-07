package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.mapper.AdminMapper;
import com.example.springbootmybatis.mapper.UserMapper;
import com.example.springbootmybatis.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@Controller
public class changepwdController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/tochangepwd")
    public String tochangepwd(@RequestParam("username") String username, @RequestParam("password") String password,
                              @RequestParam("password1") String password1,@RequestParam("password2") String password2, Model model, HttpServletRequest httpServletRequest,
                               HttpSession session){
        System.out.println(username + " is changing password");

        if(password1.length()>=8
                && PwdCheckUtil.checkContainCase(password1)
                && PwdCheckUtil.checkContainDigit(password1)
                && PwdCheckUtil.checkContainSpecialChar(password1)
        )
        {
            String md5password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

            String md5password1 = DigestUtils.md5DigestAsHex(password1.getBytes(StandardCharsets.UTF_8));

            String md5password2 = DigestUtils.md5DigestAsHex(password2.getBytes(StandardCharsets.UTF_8));

            user user = userMapper.queryUserByNP(username, md5password);

            if (user!=null){

                if (md5password1.equals(md5password2)){
                userMapper.updatePassword(username,md5password1);

                model.addAttribute("chpwdmsg","密码修改成功，请重新登录");
                session.invalidate();
                System.out.println("logout");
                return "operate/changepwd";}
                else
                {
                    model.addAttribute("chpwdmsg","两次密码不一致");
                    return "operate/changepwd";
                }


            }
            else {
                model.addAttribute("chpwdmsg","用户或密码输入错误，请重试");
                return "operate/changepwd";
            }

        }
        else
        {
            model.addAttribute("chpwdmsg","密码需要有特殊字符，字母，数字，且长度大于8位");
        }
        return "operate/changepwd";
    }
}
