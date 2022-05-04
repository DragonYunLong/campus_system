package com.yunlong.mapper;


import com.yunlong.model.Pick;
import com.yunlong.model.TypeA;
import com.yunlong.model.TypeD;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HelpMapper {
    void insertTypeA(TypeA typeA);
    void insertPick(@Param("Type") String Type,@Param("Typeid") int Typeid,@Param("Userid") int Userid);
    int selectRealperson (@Param("Typeid") int Typeid);
    Pick selectPickByType(@Param("Type") String Type, @Param("Typeid") int Typeid);
    List<Pick> selectPickByUserid(@Param("Userid") int userid);
    TypeA selectTypeAById(@Param("Id") int id);
    List<TypeA> selectTypeAByUserId(@Param("Userid") int userid);
    String selectUsernameByUserid(@Param("Userid") int userid);
    List<TypeA> selectTypeAs();
    void insertTypeD(TypeD typeD);
    List<TypeD> selectTypeDs();


}
