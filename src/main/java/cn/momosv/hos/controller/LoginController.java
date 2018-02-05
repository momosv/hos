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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("hos")
public class LoginController extends BasicController{

	@Autowired
   	private BasicService basicService;

	private final static String NORMAL="normal";
	private final static String DOCTOR="doctor";
	private final static String SYS_MANAGER="sys";
	private final static String ORG_MANAGER="org";

	@RequestMapping("login/index")
	public String hello() throws Exception {
		return  "login";
	}

	@RequestMapping("register/index")
	public Object registerIndex(){
		return  "register";
	}

	/**
	 * 登录
	 * @param account
	 * @param psw
	 * @param orgId
	 * @param type
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("login/accept")
	public Object login(@RequestParam(required = true) String account, @RequestParam(required = true) String psw, String orgId , String type, HttpSession session) throws Exception {
		BasicExample example;
		if(type.equals(DOCTOR)){
			if(StringUtils.isEmpty(orgId)){
				throw new  NullPointerException("所属机构不能为空或者不存在");
			}
			example=new BasicExample(TbDoctorPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw).andVarEqualTo("org_id",orgId);
			List<TbDoctorPO> list=basicService.selectByExample(TbDoctorPO.class,example);
			if(list.size()>0){
				TbDoctorPO po=list.get(0);
				session.setAttribute("user",po);
				return successMsg("登录成功").add("user",po.getName());
			}
		}else if(type.equals(ORG_MANAGER)){
			if(StringUtils.isEmpty(orgId)){
				throw new  NullPointerException("所属机构不能为空或者不存在");
			}
			example=new BasicExample(TbOrgManagerPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw).andVarEqualTo("org_id",orgId);;
			List<TbOrgManagerPO> list=basicService.selectByExample(TbOrgManagerPO.class,example);
			if(list.size()>0){
				TbOrgManagerPO po=list.get(0);
				session.setAttribute("user",po);
				return successMsg("登录成功").add("user",po.getName());
			}
		}else if(type.equals(SYS_MANAGER)){
			example=new BasicExample(TbSysManagerPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw);
			List<TbSysManagerPO> list=basicService.selectByExample(TbSysManagerPO.class,example);
			if(list.size()>0){
				TbSysManagerPO po=list.get(0);
				session.setAttribute("user",po);
				return successMsg("登录成功").add("user",po.getName());
			}
		}else if(type.equals(NORMAL)){
			example=new BasicExample(TbBaseUserPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw);
			List<TbBaseUserPO> list=basicService.selectByExample(TbBaseUserPO.class,example);
			if(list.size()>0){
				TbBaseUserPO po=list.get(0);
				session.setAttribute("user",po);
				return successMsg("登录成功").add("user",po.getName());
			}
		}
		return failMsg("登录失败");
	}

	@ResponseBody
	@RequestMapping("register/accept")
	public Msg register(@RequestParam(required = true)TbBaseUserPO user){
		if(StringUtils.isEmpty(user.getAccount())||StringUtils.isEmpty(user.getPasswd())){
			failMsg.setMsg("账号/邮箱或者密码不能为空");
		}
		if(StringUtils.isEmpty(user.getIdCard())){
			failMsg.setMsg("身份证号不能为空");
		}
		user.setId(UUID.randomUUID().toString());
		return failMsg;
	}

}
