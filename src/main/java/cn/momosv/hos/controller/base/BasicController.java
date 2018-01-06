package cn.momosv.hos.controller.base;

import cn.momosv.hos.bean.base.Msg;

public class BasicController {
   public Msg successMsg= Msg.success();
   public Msg failMsg= Msg.fail();

    public Msg addResult(String key,Object obj){
        successMsg.add(key,obj);
        return successMsg;
    };
}
