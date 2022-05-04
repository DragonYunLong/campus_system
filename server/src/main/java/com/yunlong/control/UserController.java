package com.yunlong.control;

import com.yunlong.api.IUserService;
import com.yunlong.model.Info;
import com.yunlong.model.User;
import com.yunlong.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/UserController")
public class UserController {
    @Autowired
    @Qualifier("UserService")
    IUserService userService;

    @RequestMapping("/getUserId")
    @ResponseBody
    public Result getUserId(String sessionid){
        return Result.success(userService.selectUserId(sessionid));
    }

    @RequestMapping("/getUsername")
    @ResponseBody
    public Result getUsername(String sessionid){
        return Result.success(userService.selectUsername(sessionid));
    }

    @RequestMapping("/register")
    @ResponseBody
    public Result insertUser(String username, String password,String email){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return userService.insertUser(user);
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result login( String username, String password){
        return userService.login(username,password);
    }

    @RequestMapping("/selectPersonByUsername")
    @ResponseBody
    public Result selectPersonByUsername(String username){

        return userService.selectPersonByUsername(username);
    }

    @RequestMapping("/selectPerson")
    @ResponseBody
    public Result selectPerson(String sessionid){

        return userService.selectPerson(sessionid);
    }

    @RequestMapping("/selectPersons")
    @ResponseBody
    public Result selectPersons(){

        return userService.selectPersons();
    }

    @RequestMapping("/updateName")
    @ResponseBody
    public Result updateName(String name,String sessionid){
        return userService.updateName(name,sessionid);
    }

    @RequestMapping("/updateInfo")
    @ResponseBody
    public Result updateInfo(String realname, String sex, String birth,String school, String sessionid){
        Info info = new Info();
        info.setName(realname);
        info.setSex(sex);
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
            info.setBirth(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        info.setSchool(school);
        return userService.updateInfo(info,sessionid);
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public Result getUser(String sessionid){
        return userService.getUser(sessionid);
    }

    @RequestMapping("/selectUsers")
    @ResponseBody
    public Result selectUsers(){
        return userService.selectUsers();
    }
}
