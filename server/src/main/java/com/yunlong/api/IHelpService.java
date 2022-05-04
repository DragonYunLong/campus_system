package com.yunlong.api;

import com.yunlong.model.TypeA;
import com.yunlong.model.TypeD;
import com.yunlong.result.Result;

import java.util.List;

public interface IHelpService {

    Result insertTypeA(int userid,String title,String content,int person,int money);

    Result selectTypeAs();

    Result pickHelp(String type,int userid,int typeid);

    Result selectPickHelp(int userid);

    Result getPublishHelp(int userid);

    Result insertTypeD(int type,String image,String des,int userid);

    Result selectType5Ds();

    Result selectTypeDs();
}
