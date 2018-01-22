package cn.momosv.hos.controller;


import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbDoctorPO;
import cn.momosv.hos.model.TbOrgManagerPO;
import cn.momosv.hos.model.TbSysManagerPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.BasicExample.Criteria;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("hos")
public class LoginController extends BasicController{

	@Autowired
   	private BasicService basicService;
	private final static String NORMAL="normal";
	private final static String DOCTOR="doctor";
	private final static String SYS_MANAGER="sys";
	private final static String ORG_MANAGER="org";

	@ResponseBody
	@RequestMapping("login/accept")
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
			example=new BasicExample(TbOrgManagerPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw);
			List<TbOrgManagerPO> list=basicService.selectByExample(TbOrgManagerPO.class,example);
			if(list.size()>0){
				TbOrgManagerPO po=list.get(0);
				session.setAttribute("user",po);
				return "登录成功";
			}
		}else if(type.equals(SYS_MANAGER)){
			example=new BasicExample(TbSysManagerPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw);
			List<TbSysManagerPO> list=basicService.selectByExample(TbSysManagerPO.class,example);
			if(list.size()>0){
				TbSysManagerPO po=list.get(0);
				session.setAttribute("user",po);
				return "登录成功";
			}
		}else if(type.equals(NORMAL)){
			example=new BasicExample(TbBaseUserPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw);
			List<TbBaseUserPO> list=basicService.selectByExample(TbBaseUserPO.class,example);
			if(list.size()>0){
				TbBaseUserPO po=list.get(0);
				session.setAttribute("user",po);
				return "登录成功";
			}
		}
		return "ggg";
	}

	@RequestMapping("login/index")
	public String hello() throws Exception {
		return  "login";
	}

	@RequestMapping("register/index")
	public Object register(){
		return  "register";
	}

	@ResponseBody
	@RequestMapping("register/accept")
	public Msg Msg(String account,String psw,String email,String type) throws Exception {
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
			example=new BasicExample(TbOrgManagerPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw);
			List<TbOrgManagerPO> list=basicService.selectByExample(TbOrgManagerPO.class,example);
			if(list.size()>0){
				TbOrgManagerPO po=list.get(0);
				session.setAttribute("user",po);
				return "登录成功";
			}
		}else if(type.equals(SYS_MANAGER)){
			example=new BasicExample(TbSysManagerPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw);
			List<TbSysManagerPO> list=basicService.selectByExample(TbSysManagerPO.class,example);
			if(list.size()>0){
				TbSysManagerPO po=list.get(0);
				session.setAttribute("user",po);
				return "登录成功";
			}
		}else if(type.equals(NORMAL)){
			example=new BasicExample(TbBaseUserPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw);
			List<TbBaseUserPO> list=basicService.selectByExample(TbBaseUserPO.class,example);
			if(list.size()>0){
				TbBaseUserPO po=list.get(0);
				session.setAttribute("user",po);
				return "登录成功";
			}
		}
		return null;
	}

}
