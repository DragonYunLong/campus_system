package com.yunlong.session;


import org.junit.jupiter.api.Test;

import java.util.Random;

public class SessionID {
    private static String father = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String getSessionId(int length){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i =0;i<length;i++){
            sb.append(father.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }
}
