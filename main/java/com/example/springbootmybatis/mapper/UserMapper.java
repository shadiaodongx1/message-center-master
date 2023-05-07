package com.example.springbootmybatis.mapper;


import com.example.springbootmybatis.pojo.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//这个注解表示了这是一个mybatis的mapper类
@Mapper
public interface UserMapper {

    List<user> queryUserList();

    user queryUserById(int id);

    user queryUserByName(String username);

    user queryUserByNP(String username,String password);

    user queryUserByNE(String username,String email);

    user queryUserByEc(String emailcode);


//    user queryUserByPwd(String password);

    //获取邮箱验证码
    String queryCodeByName(String username);

    //更新邮箱验证码
    int updateEmailcode(String username,String emailcode);

    //添加一个用户
    int addUser(user user);

    //绑定邮箱
    int updateMail(String username,String email);

    //
    int addUser1(String username,String password);

    //对留言内容进行更新
    int updateMessage(String username,String message);

    //修改密码
    int updatePassword(String username,String password);

    //进行评论
//     int updateComment(String name,String comment);

     //删除留言和评论
    int deleteComMes(int id);

    //删除用户
    int deleteUser(int id);

    //分页查询
//    List<user> queryUserListByPage();
}
