package com.example.first.ctl;

import com.example.first.entity.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RedisCtl {
    @Autowired
    private RedisTemplate redisTemplate;
    public  String  rset(){
         return "";

    }

}
