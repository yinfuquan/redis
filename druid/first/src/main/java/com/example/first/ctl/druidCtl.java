package com.example.first.ctl;

import com.example.first.entity.UserPO;
import com.example.first.serCtl.userService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;

@Controller
public class druidCtl {
@Autowired
private userService userService;
    @Autowired
    private RedisTemplate<String, String> S;
    @RequestMapping("t")
    public String  druid(HttpServletResponse response)
    {
       boolean tt= S.hasKey("ii");
        UserPO po=   userService.ooo();
      List<UserPO> p=UserPO.Dao.$.all();
        return  "t.html";
    }
}
