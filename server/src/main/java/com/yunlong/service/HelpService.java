package com.yunlong.service;

import com.yunlong.api.IHelpService;
import com.yunlong.mapper.HelpMapper;
import com.yunlong.mapper.UserMapper;
import com.yunlong.model.Pick;
import com.yunlong.model.TypeA;
import com.yunlong.model.TypeD;
import com.yunlong.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("HelpService")
public class HelpService implements IHelpService {

    @Autowired
    HelpMapper helpMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Result insertTypeA(int userid,String title,String content,int person,int money) {
        TypeA a = new TypeA();
        a.setUserid(userid);
        a.setTitle(title);
        a.setContent(content);
        a.setPerson(person);
        a.setMoney(money);
        a.setStarttime(new Date());
        helpMapper.insertTypeA(a);
        return Result.success("发布成功");
    }

    @Override
    public Result selectTypeAs() {
        List<TypeA> list = helpMapper.selectTypeAs();
        int num = 0;
        for(TypeA typeA:list){
            typeA.setRealperson(helpMapper.selectRealperson(typeA.getId()));
            typeA.setUsername(userMapper.selectUserById(typeA.getUserid()).getUsername());
            list.set(num,typeA);
            num++;
        }
        return Result.success(list);
    }

    @Override
    public Result pickHelp(String type,int userid,int typeid) {
        Pick pick = helpMapper.selectPickByType(type,typeid);
        if(pick == null){
            helpMapper.insertPick(type,typeid,userid);
            return  Result.success("接取成功");
        }
       return Result.error(412,"你已经接取了该请求");
    }

    @Override
    public Result selectPickHelp(int useid) {
        List<Pick> list = helpMapper.selectPickByUserid(useid);
        List<TypeA> typeA = new ArrayList<>();
        for(Pick pick : list){
            if(pick.getType().equals("A")){
                typeA.add(helpMapper.selectTypeAById(pick.getTypeid()));
            }
        }
        return Result.success(typeA);
    }

    @Override
    public Result getPublishHelp(int userid) {
        return Result.success(helpMapper.selectTypeAByUserId(userid));
    }

    @Override
    public Result insertTypeD(int type,String image,String des,int userid) {
        TypeD typeD = new TypeD();
        typeD.setType(type);
        typeD.setImage(image);
        typeD.setDes(des);
        typeD.setUserid(userid);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        typeD.setStarttime(sdf.format(date));
        helpMapper.insertTypeD(typeD);
        return Result.success("发布成功");
    }

    @Override
    public Result selectType5Ds() {
        List<TypeD> list = helpMapper.selectTypeDs();
        List<TypeD> type5Ds = new ArrayList<>();
        int num = 0;
        for(TypeD typeD : list){
            if(num >= 5) break;
            if(typeD.getStatus() == 0 && typeD.getType() == 1){
                typeD.setUsername(userMapper.selectUserById(typeD.getUserid()).getUsername());
                type5Ds.add(typeD);
                num++;
            }
        }
        return Result.success(type5Ds);
    }

    @Override
    public Result selectTypeDs() {
        List<TypeD> list = helpMapper.selectTypeDs();
        List<TypeD> typeDs = new ArrayList<>();
        for(TypeD typeD : list){
            if(typeD.getStatus() == 0){
                typeD.setUsername(userMapper.selectUserById(typeD.getUserid()).getUsername());
                typeDs.add(typeD);
            }
        }
        return Result.success(typeDs);
    }


}
