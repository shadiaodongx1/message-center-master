package com.example.springbootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.StringTokenizer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class user {
    private int id;
    private String username;
    private String password;
    private String message;
    private String comment;
    private String email;
    private String emailcode;
}
