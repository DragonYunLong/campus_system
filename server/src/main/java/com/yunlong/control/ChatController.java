package com.yunlong.control;

import com.yunlong.api.IChatService;
import com.yunlong.api.IUserService;
import com.yunlong.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/ChatController")
public class ChatController {

    @Autowired
    @Qualifier("ChatService")
    IChatService chatService;

    @Autowired
    @Qualifier("UserService")
    IUserService userService;

    @RequestMapping("Chat")
    public Result Chat(String sessionid,int userid){
        int user1id = (int)userService.selectUserId(sessionid).getData();
        int user2id = userid;
        return chatService.insertChat(user1id,user2id);
    }

    @RequestMapping("getChatUsers")
    public Result getChatUsers(String sessionid){
        int userid = (int)userService.selectUserId(sessionid).getData();
        return chatService.selectChatUsers(userid);
    }
    @RequestMapping("getMessage")
    public Result getMessage(String sessionid,int chatid){
        int userid = (int)userService.selectUserId(sessionid).getData();
        return chatService.selectMessage(userid,chatid);
    }

    @RequestMapping("sendMessage")
    public Result sendMessage(String sessionid,int chatid,String message){
        int userid = (int)userService.selectUserId(sessionid).getData();
        return chatService.insertMessage(chatid,userid,message);
    }

}
