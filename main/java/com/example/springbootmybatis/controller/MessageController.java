package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.mapper.EntrustmentMapper;
import com.example.springbootmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;

import static jdk.nashorn.internal.objects.Global.print;

@Controller
public class MessageController {

    @Autowired
    EntrustmentMapper entrustmentMapper;

    @RequestMapping("/liuyan")
    public String liuyan(HttpSession session, @RequestParam("message") String entrustment){
//        System.out.println(session.getAttribute("userwho"));
        String username = (String) session.getAttribute("userwho");
        System.out.println(entrustment);

        //使用Date获取当前时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式（年-月-日-时-分-秒）
        String createTime = dateFormat.format(now);//格式化然后放入字符串中

        String newentrustment= entrustment  +"\n该委托发布于: "  + createTime ;

        entrustmentMapper.addEntrustment(username,newentrustment,"未被接受");
        return "dashboard";
    }
}
