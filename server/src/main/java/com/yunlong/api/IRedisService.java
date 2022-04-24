package com.yunlong.api;

import org.springframework.stereotype.Service;

import java.util.List;


public interface IRedisService {
    void setString(String key,String value);
    Object executeRedisScript(List input, String scriptName);
    void setSession(String key, String value);
}
