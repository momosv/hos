package cn.momosv.hos.controller;

import cn.momosv.hos.bean.TbTest;
import cn.momosv.hos.bean.base.BasicExample;
import cn.momosv.hos.bean.base.Msg;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.ITestService;
import cn.momosv.hos.service.ITestService2;
import cn.momosv.hos.service.impl.BasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mybatis")
public class BaseController {
	@Autowired
	private TbTest tb;


   	@Autowired
	private ITestService testService;
 	@Autowired
	private ITestService2 testService2;
	@Autowired
   	private BasicService basicService;
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/hello")
	public List<TbTest> hello() throws Exception {
		
		BasicExample example=new BasicExample();
		
	List<TbTest> list=basicService.selectByExample(TbTest.class,example);
		return  list;
	}
	
	@ResponseBody
	@RequestMapping("/map")
	public Map<String, Object> mmp(){
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
