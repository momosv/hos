package cn.momosv.hos.controller;


import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.TbDoctorPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.BasicExample.Criteria;
import cn.momosv.hos.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/hos")
public class LoginController extends BasicController{

	@Autowired
   	private BasicService basicService;
	private final static String NORMAL="normal";
	private final static String DOCTOR="doctor";
	private final static String SYS_MANAGER="sys";
	private final static String ORG_MANAGER="org";

	@ResponseBody
	@RequestMapping("/login")
	public String login(String account, String psw, String type, HttpSession session) throws Exception {
		BasicExample example;
		if(type.equals(DOCTOR)){
			example=new BasicExample(TbDoctorPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw);
			List<TbDoctorPO> list=basicService.selectByExample(TbDoctorPO.class,example);
			if(list.size()>0){
				TbDoctorPO po=list.get(0);
				session.setAttribute("user",po);
				return "登录成功";
			}
		}else if(type.equals(ORG_MANAGER)){

		}else if(type.equals(SYS_MANAGER)){

		}else if(type.equals(NORMAL)){

		}
		return "ggg";
	}

/*	@RequestMapping("/hello")
	public Object hello() throws Exception {
		BasicExample example=new BasicExample(TbTest.class);
		List<TbTest> list=basicService.selectByExample(TbTest.class,example);
		int i= basicService.countByExample(example);
		addResult("list",list);
		return  successMsg;
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
	}*/

}
