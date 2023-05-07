package com.example.springbootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class admin {
    private int adminid;
    private String adminname;
    private String adminpwd;
}
