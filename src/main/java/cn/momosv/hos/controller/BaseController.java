package cn.momosv.hos.controller;

import cn.momosv.hos.bean.Msg;
import cn.momosv.hos.bean.TbTest;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.impl.BasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mybatis")
public class BaseController {
	@Autowired
	TbTest tb;

   	private	BasicService basicService=new BasicServiceImpl<>();
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() throws Exception {
		System.out.println(tb);
		TbTest tb1=new TbTest();
		System.out.println(tb1);
		TbTest tb2=new TbTest();
		System.out.println(tb2);
		basicService.findAll(TbTest.class);
		return "hello";
	}
	
	@ResponseBody
	@RequestMapping("/map")
	public Map mmp(){
		Map<String, Object> map=new HashMap<>();
		map.put("liao", "傻了吧");
		Msg msg= new Msg();
		msg.success(100, "c");
		return map;
	}
	@ResponseBody
	@RequestMapping("/msg")
	public Msg Msg(){
		Map<String, Object> map=new HashMap<>();
		map.put("liao", "傻了吧");
		map.put("path", ClassLoader.getSystemResource(""));
		return Msg.success().add("data", map);
	}
	
    @RequestMapping("/login")
    public String login() {
    	System.out.println("momo");
        return "sec/login";
    }

}
