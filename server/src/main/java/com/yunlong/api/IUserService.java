package com.yunlong.api;

import com.yunlong.model.Info;
import com.yunlong.model.User;
import com.yunlong.result.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;


public interface IUserService {

    Result insertUser(User user);
    Result login(String username, String password);
    Result selectPerson(String username);
    Result updateName(String name, String sessionid);
    Result updateInfo(Info info, String sessionid);
    Result selectUserId(String sessionid);
    Result selectUsers();
    Result getUser(String sessionid);
    String selectUsername(String sessionid);
    Result selectPersons();
    Result selectPersonByUsername(String username);
}
