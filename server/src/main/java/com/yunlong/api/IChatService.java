package com.yunlong.api;

import com.yunlong.result.Result;

public interface IChatService {

    Result selectChatUsers(int userid);

    Result insertChat(int user1id, int user2id);

    Result selectMessage(int userid, int chatid);
    Result insertMessage(int chatid,int userid,String text);
}
