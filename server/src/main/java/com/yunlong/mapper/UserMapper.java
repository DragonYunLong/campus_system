package com.yunlong.mapper;


import com.yunlong.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    void insertUser(User user);
    User selectUserByUsername(String username);
    User selectUserByEmail(String email);
    void updateName(String name,String username);
    int selectUserId(@Param("username") String username);
    List<User> selectUsers();

}
