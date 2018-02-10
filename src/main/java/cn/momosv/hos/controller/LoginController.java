package cn.momosv.hos.controller;


import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.*;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.BasicExample.Criteria;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.LoginService;
import cn.momosv.hos.util.SysUtil;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/hos")
public class LoginController extends BasicController{

	@Autowired
	private LoginService loginService;

	private final static String NORMAL="normal";
	private final static String DOCTOR="doctor";
	private final static String SYS_MANAGER="sys";
	private final static String ORG_MANAGER="org";

	/**
	 * 首页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login")
	public String hello() throws Exception {
		return  "login";
	}

	/**
	 * 个人注册首页
	 * @return
	 */
	@RequestMapping("register")
	public Object registerIndex(){
		return  "register";
	}

	/**
	 * 机构注册首页
	 * @return
	 */
	@RequestMapping("register/orgIndex")
	public Object registerOrg(){
		return  "registerOrg";
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
	public Object login(@RequestParam(required = true) String account, @RequestParam(required = true) String psw, String orgId , @RequestParam(required = true)String type, HttpSession session) throws Exception {
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
	@RequestMapping("register/user")
	public Msg register(TbBaseUserPO user,@RequestParam(required = true) String type) throws IOException, TemplateException {
		if(StringUtils.isEmpty(user.getAccount())||StringUtils.isEmpty(user.getPasswd())){
			failMsg("账号/邮箱或者密码不能为空");
		}
		if(StringUtils.isEmpty(user.getIdCard())){
			failMsg("身份证号不能为空");
		}
		user.setId(UUID.randomUUID().toString());
		user.setEmail(user.getAccount());
		user.setCreateTime(new Date());
		user.setActCode(0);
		if(NORMAL.equals(type)){
			session.setAttribute("momo","momo");
			basicService.insertOne(user);
			loginService.sendUserRegisterMail(user);
			return successMsg("注册成功");
		}
		return failMsg("注册身份不能为空");
	}

	@ResponseBody
	@RequestMapping("register/org")
	public Msg registerOrg(TbMedicalOrgPO org){
		if(StringUtils.isEmpty(org.getName())){
			return	failMsg("机构名称不能为空");
		}
		if(StringUtils.isEmpty(org.getLicence())){
			return	failMsg("机构许可证不能为空");
		}
		if(StringUtils.isEmpty(org.getEmail())){
			return	failMsg("邮箱不能为空");
		}
		if(StringUtils.isEmpty(org.getLegal())){
			return	failMsg("法定负责人不能为空");
		}
		if(StringUtils.isEmpty(org.getPrincipal())){
			return	failMsg("机构负责人不能为空");
		}
		if(StringUtils.isEmpty(org.getLinkman())){
			return	failMsg("机构许联系人不能为空");
		}
		if(StringUtils.isEmpty(org.getTelephone())){
			return failMsg("机构联系方式不能为空");
		}
		if(StringUtils.isEmpty(org.getLicenceImage())){
			return failMsg("机构许可证照片不能为空");
		}
		org.setId(UUID36());
		org.setActCode(0);
		org.setCreateTime(new Date());
		basicService.insertOne(org);
		return successMsg("注册成功,我们将会尽快审批并以邮件方式通知您审批结果");
	}

	@RequestMapping("login/act/{id}")
	public Object actEmail(@PathVariable String id, String email, Map map, HttpServletRequest request,HttpSession session) throws Exception {
		BasicExample example=new BasicExample<>(TbBaseUserPO.class);
		example.createCriteria().andVarEqualTo("id",id);
		TbBaseUserPO user= (TbBaseUserPO) basicService.selectByPrimaryKey(TbBaseUserPO.class,id);
		if(null==user){
			map.put("tips","链接已经失效，请重新注册！");
			map.put("actCode","0");
		}else if(user.getActCode().equals(1)){
			map.put("tips","该邮箱已经激活，可以直接登录使用！");
			map.put("actCode","1");
		}else if(null!=user.getCreateTime()&&user.getCreateTime().getTime()-(new Date()).getTime()+24*60*60*1000>0){
			user.setActCode(1);
			if(basicService.updateOne(user,true)>0){
				map.put("tips","该邮箱已经激活，可以直接登录使用！");
				map.put("actCode","1");
			}else{
				basicService.deleteByPrimaryKey(TbBaseUserPO.class,id);
				map.put("tips","该邮箱激活失败，请重新注册！");
				map.put("actCode","0");
			}
		}else{
			basicService.deleteByPrimaryKey(TbBaseUserPO.class,id);
			map.put("tips","链接已经失效，请重新注册！");
			map.put("actCode","0");
		}
		return  "actTips";
	}
	@RequestMapping("exit")//1是邮箱认证，2是待审批，3是审批通过，4是不通过
	public Object exit() throws Exception {
		session.removeAttribute(SysUtil.USER);
		return "login";
	}
}
