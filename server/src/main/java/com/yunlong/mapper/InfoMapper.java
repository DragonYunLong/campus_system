package com.yunlong.mapper;


import com.yunlong.model.Info;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoMapper {
    void insertInfo(Info info);
    Info selectInfoById(int infoid);
    void updateInfo(int infoid,@Param("info") Info info);
}
