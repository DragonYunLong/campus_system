package com.yunlong.control;


import com.yunlong.api.IHelpService;
import com.yunlong.api.IRedisService;
import com.yunlong.api.IUserService;
import com.yunlong.model.TypeA;
import com.yunlong.model.TypeD;
import com.yunlong.result.Result;
import com.yunlong.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/HelpController")
public class HelpController {

    @Autowired
    @Qualifier("HelpService")
    IHelpService helpService;

    @Autowired
    @Qualifier("UserService")
    IUserService userService;

    @Autowired
    @Qualifier("RedisService")
    IRedisService redisService;

    @RequestMapping("/insertA")
    @ResponseBody
    public Result insertA(String sessionid,String title,String content,int person,int money){
        int userid = (int)userService.selectUserId(sessionid).getData();
        return helpService.insertTypeA(userid,title,content,person,money);
    }

    @RequestMapping("/selectTypeAs")
    @ResponseBody
    public Result selectTypeAs(){
        return helpService.selectTypeAs();
    }

    @RequestMapping("/pickHelp")
    @ResponseBody
    public Result pickHelp(String type,String sessionid,int typeid){
        int userid = (int)userService.selectUserId(sessionid).getData();
        return helpService.pickHelp(type,userid,typeid);
    }

    @RequestMapping("/getPickHelp")
    @ResponseBody
    public Result getPickHelp(String sessionid){
        int userid = (int)userService.selectUserId(sessionid).getData();
        return helpService.selectPickHelp(userid);
    }

    @RequestMapping("/getPublishHelp")
    @ResponseBody
    public Result getPublishHelp(String sessionid){
        int userid = (int)userService.selectUserId(sessionid).getData();
        return helpService.getPublishHelp(userid);
    }

    @RequestMapping("/publishD")
    @ResponseBody
    public Result publishD(int type,String image,String descript,String sessionid){
        int userid = (int)userService.selectUserId(sessionid).getData();
        return helpService.insertTypeD(type,image,descript,userid);
    }

    @RequestMapping("/getType5Ds")
    @ResponseBody
    public Result getType5Ds(){
        return helpService.selectType5Ds();
    }

    @RequestMapping("/getTypeDs")
    @ResponseBody
    public Result getTypeDs(){
        return helpService.selectTypeDs();
    }

}
