package cn.momosv.hos.controller.base;


import cn.momosv.hos.model.base.Msg;

import java.util.UUID;

public class BasicController {
    public Msg successMsg= Msg.success();

    public Msg failMsg= Msg.fail();

    public Msg addResult(String key,Object obj){
        return  successMsg.add(key,obj);
    }

    public Msg successMsg(String msg){
        return  successMsg.setMsg(msg);
    }

    public Msg failMsg(String msg){
        return  failMsg.setMsg(msg);
    }
    public String UUID36(){
       return UUID.randomUUID().toString();
    }

}
