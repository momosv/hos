package cn.momosv.hos.controller.base;


import cn.momosv.hos.model.base.Msg;

public class BasicController {
   public Msg successMsg= Msg.success();

    public Msg addResult(String key,Object obj){
        successMsg.add(key,obj);
        return successMsg;
    };
}
