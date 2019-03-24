package com.example.first.serCtl;

import com.example.first.dal.bettlsql.EntityManager;
import com.example.first.entity.UserPO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "users")
public class userService implements EntityManager<UserPO> {
    @Cacheable(key ="'po'")
    public  UserPO ooo(){
        UserPO po=new UserPO();
        po.setId(999107954785386496L);
        po= this.getSQLManager().templateOne(po);
        System.out.println(po.getName());
        return po;
    }
}
