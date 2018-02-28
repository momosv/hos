package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("hos/page")
@Controller
public class PageController extends BasicController {

    @RequestMapping("sys/orgDetail/{id}")
    public String orgDetail(@PathVariable("id") String id,Map map){
      //  map.put("id",id);
        return "sys/orgDetail";
    }
    @RequestMapping("sys/userDetail/{id}")
    public String userDetail(@PathVariable("id") String id,Map map){
      //  map.put("id",id);
        return "sys/userDetail";
    }
    @RequestMapping("org/docDetail/{id}")
    public String docDetail(@PathVariable("id") String id,Map map){
      //  map.put("id",id);
        return "org/docDetail";
    }
    @RequestMapping("org/addDoc")
    public String userDetail(){
        return "org/addDoc";
    }
}
