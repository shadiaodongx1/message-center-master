package com.example.springbootmybatis.service;
import com.example.springbootmybatis.mapper.UserMapper;
import com.example.springbootmybatis.pojo.user;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;   //注入dao层

    //pageNum 第几页 pageSize 每页有多少数据
    public List<user> queryUserListByPage(Integer pageNum,Integer pageSize){

        PageHelper.startPage(pageNum,pageSize);

        //findAll  查询出所有数据
        return userMapper.queryUserList();
    }

}
        //上面的函数