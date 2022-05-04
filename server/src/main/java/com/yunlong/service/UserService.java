package com.yunlong.service;

import com.yunlong.api.IRedisService;
import com.yunlong.api.IUserService;
import com.yunlong.mapper.InfoMapper;
import com.yunlong.mapper.UserMapper;
import com.yunlong.model.Info;
import com.yunlong.model.Person;
import com.yunlong.model.User;
import com.yunlong.result.Result;
import com.yunlong.session.SessionID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("UserService")
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    InfoMapper infoMapper;
    @Autowired
    @Qualifier("RedisService")
    IRedisService redisService;

    @Override
    public Result insertUser(User user) {
        User user1 = selectUserByUsername(user.getUsername());
        User user2 = selectUserByEmail(user.getEmail());
        if(user1 != null){
            return Result.error(412,"用户名已存在");
        }
        if(user2 != null){
            return Result.error(412,"该邮箱已被注册");
        }
        Info info = new Info();
        infoMapper.insertInfo(info);
        user.setInfoid(info.getId());
        userMapper.insertUser(user);
        return Result.success("True");
    }

    @Override
    public Result login(String username, String password){
        User user = selectUserByUsername(username);
        if(user == null){
            return Result.error(404,"用户名不存在");
        }
        if(!password.equals(user.getPassword())){
            return Result.error(412,"密码错误");
        }
        String sessionid = SessionID.getSessionId(32);
        redisService.setSession(sessionid,username);
        return new Result(sessionid);
    }

    @Override
    public Result selectPerson(String sessionid) {
        String username = selectUsername(sessionid);
        if(username == null) return Result.error(412,"sessionid不存在");
        return selectPersonByUsername(username);
    }

    @Override
    public Result selectPersonByUsername(String username) {
        User user = selectUserByUsername(username);
        Info info = selectInfoById(user.getInfoid());
        Person person = new Person();
        person.setEmail(user.getEmail().replace(user.getEmail().substring(4,user.getEmail().lastIndexOf("@")),"***"));
        person.setUsername(username);
        person.setName(user.getName());
        person.setIdent(user.getIdent());
        person.setImage(user.getImage());
        person.setUserid(user.getId());
        String name = info.getName();
        if(name != null) name = name.replace(name.substring(1,name.length()),"**");
        person.setRealname(name);
        person.setBirth(info.getBirth());
        person.setSchool(info.getSchool());
        person.setSex(info.getSex());
        return new Result(person);
    }

    @Override
    public Result updateName(String name, String sessionid) {
        String username = selectUsername(sessionid);
        userMapper.updateName(name,username);
        return Result.success("修改成功");
    }

    @Override
    public Result updateInfo(Info info, String sessionid) {
        String username = selectUsername(sessionid);
        User user  = selectUserByUsername(username);
        infoMapper.updateInfo(user.getInfoid(),info);
        return Result.success("修改成功");
    }

    @Override
    public Result selectUserId(String sessionid) {
        String username = selectUsername(sessionid);
        if(username == null){
            return Result.success(null);
        }
        return Result.success(userMapper.selectUserId(username));
    }

    @Override
    public Result selectUsers() {
        return Result.success(userMapper.selectUsers());
    }

    @Override
    public Result getUser(String sessionid) {
        String username = selectUsername(sessionid);

        return Result.success(selectUserByUsername(username));
    }

    @Override
    public String selectUsername(String sessionid){
        List list = new ArrayList();
        String username = "";
        list.add(sessionid);
        try{
            username = redisService.executeRedisScript(list,"selectSession.lua").toString();
        }catch (Exception e){
            return null;
        }
        return username;
    }

    @Override
    public Result selectPersons() {
        List<User> users = userMapper.selectUsers();
        List<Person> persons = new ArrayList<>();
        for(User user :users){
            Info info = selectInfoById(user.getInfoid());
            Person person = new Person();
            person.setEmail(user.getEmail());
            person.setUsername(user.getUsername());
            person.setName(user.getName());
            person.setIdent(user.getIdent());
            String name = info.getName();
            person.setRealname(name);
            person.setBirth(info.getBirth());
            person.setSchool(info.getSchool());
            person.setSex(info.getSex());
            person.setUserid(user.getId());
            persons.add(person);
        }
        return Result.success(persons);
    }



    private Info selectInfoById(int id) {
        return infoMapper.selectInfoById(id);
    }
    public User selectUserByUsername(String username){
        return userMapper.selectUserByUsername(username);
    }
    public User selectUserByEmail(String email){
        return userMapper.selectUserByEmail(email);
    }
}
