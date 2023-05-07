package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.mapper.EntrustmentMapper;
import com.example.springbootmybatis.mapper.UserMapper;
import com.example.springbootmybatis.pojo.user;
import com.example.springbootmybatis.pojo.entrustment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    UserMapper mapper;

    @Autowired
    EntrustmentMapper mapper2;

    @RequestMapping("/comment")
    public String comment(@RequestParam("eid") Integer eid,
                          @RequestParam("comment") String comment,
                          HttpSession session){


        //使用Date获取当前时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式（年-月-日-时-分-秒）
        String createTime = dateFormat.format(now);//格式化然后放入字符串中

        //这条评论来自哪个用户
        String userwho = (String) session.getAttribute("userwho");
        String commentwho = "这条评论来自: " + userwho +" " + createTime;



        //获取过去的评论内容
        entrustment entrustment = mapper2.queryentrustmentByEid(eid);
        String passcomment = entrustment.getComment();


        //将这次评论的内容和过去别人评论的内容相加
        String sum = passcomment +" " + comment +"->" +commentwho;

        int i = mapper2.updateComment(eid,sum);

        System.out.println(i);

        return "dashboard";
    }
}
