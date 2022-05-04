package com.yunlong.service;

import com.yunlong.api.IChatService;
import com.yunlong.mapper.ChatMapper;
import com.yunlong.mapper.UserMapper;
import com.yunlong.model.Chat;
import com.yunlong.model.Message;
import com.yunlong.model.Person;
import com.yunlong.model.User;
import com.yunlong.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("ChatService")
public class ChatService implements IChatService {

    @Autowired
    ChatMapper chatMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Result selectChatUsers(int userid) {
        List<Chat> user1 =  chatMapper.selectChatUser1(userid);
        List<Chat> user2 =  chatMapper.selectChatUser2(userid);
        List<Person> users = new ArrayList<>();
        for(Chat chat : user1){
            User temp = userMapper.selectUserById(chat.getUser1id());
            Person person = new Person();
            person.setUserid(temp.getId());
            person.setImage(temp.getImage());
            person.setUsername(temp.getUsername());
            person.setIdent(chat.getId());
            users.add(person);
        }
        for(Chat chat : user2){
            User temp = userMapper.selectUserById(chat.getUser2id());
            Person person = new Person();
            person.setUserid(temp.getId());
            person.setImage(temp.getImage());
            person.setUsername(temp.getUsername());
            person.setIdent(chat.getId());
            users.add(person);
        }
        return Result.success(users);
    }

    @Override
    public Result insertChat(int user1id, int user2id) {
        Chat chat = chatMapper.selectChat(user1id,user2id);
        if(chat == null)
            chatMapper.insertChat(user1id,user2id);
        return Result.success("True");
    }

    @Override
    public Result selectMessage(int userid, int chatid) {
        List<Message> list = chatMapper.selectMessage(chatid);
        int num = 0;
        for(Message message : list){
            if(message.getUserid() == userid){
                message.setStatus(true);
            }else {
                message.setStatus(false);
            }
            list.set(num,message);
        }
        return Result.success(list);
    }

    @Override
    public Result insertMessage(int chatid, int userid, String text) {
        Message mes = new Message();
        mes.setMessage(text);
        mes.setChatid(chatid);
        mes.setUserid(userid);
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yy-MM-dd:HH:mm:ss");
        mes.setDate(sd.format(date));
        chatMapper.insertMessage(mes);
        return  Result.success(true);
    }
}
