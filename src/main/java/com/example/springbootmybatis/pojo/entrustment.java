package com.example.springbootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class entrustment {
    private int eid;
    private String username;
    private String entrustment;
    private String cond;
    private String comment;
}
