package cn.momosv.hos.controller.base;


import cn.momosv.hos.model.base.Msg;

import java.util.UUID;

public class BasicController {
    public Msg successMsg(){
        return Msg.success();
    }

    public Msg failMsg(){
        return Msg.fail();
    }

    public Msg addResult(String key,Object obj){
        Msg msg = Msg.success();
        msg.add(key,obj);
        return msg;
    }

    public Msg successMsg(String msg){
        return Msg.success(msg);
    }

    public Msg failMsg(String msg){
        return Msg.fail(msg);
    }
    public String UUID36(){
        return UUID.randomUUID().toString();
    }

}
