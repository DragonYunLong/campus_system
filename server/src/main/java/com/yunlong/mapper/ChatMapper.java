package com.yunlong.mapper;

import com.yunlong.model.Chat;
import com.yunlong.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMapper {
    List<Chat> selectChatUser1(@Param("Userid") int user2id);
    List<Chat> selectChatUser2(@Param("Userid") int user1id);
    void insertChat(@Param("user1id") int user1id,@Param("user2id") int user2id);
    void insertMessage(Message message);
    Chat selectChat(@Param("user1id") int user1id,@Param("user2id") int user2id);
    List<Message> selectMessage(@Param("chatid") int chatid);
}
