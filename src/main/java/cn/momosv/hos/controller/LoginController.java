package cn.momosv.hos.controller;


import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.*;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.BasicExample.Criteria;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.LoginService;
import cn.momosv.hos.util.Constants;
import cn.momosv.hos.util.SysUtil;
import cn.momosv.hos.vo.TbDoctorVO;
import cn.momosv.hos.vo.TbOrgManagerVO;
import cn.momosv.hos.exception.MyException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


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
	public String hello(Map map) throws Exception {
		BasicExample example =new BasicExample(TbMedicalOrgPO.class);
		example.setOrderByClause("create_time desc");
		Page page=PageHelper.startPage(1,3);
		basicService.selectByExample(example);
		PageInfo data=new PageInfo<>(page.getResult());
		map.put("orgList",data.getList());

		BasicExample basicExample=new BasicExample("u.id_card = p.user_id","tb_base_user u,tb_org_patient p");
		//basicExample.setCol("u.id");
		basicService.selectJoint(basicExample);
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
			example=new BasicExample(TbDoctorVO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw).andVarEqualTo("org_id",orgId);
			List<TbDoctorVO> list=basicService.selectByExample(example);

			if(list.size()>0){
				TbDoctorVO vo=list.get(0);
				TbDepartmentPO deptPO= (TbDepartmentPO) basicService.selectByPrimaryKey(TbDepartmentPO.class,vo.getDeptId());
				if(deptPO==null) {
					return failMsg("登录失败,该用户所属部门/科室不存在");
				}
				vo.setDeptCode(deptPO.getCode());
				vo.setDeptName(deptPO.getName());
				TbMedicalOrgPO orgPO= (TbMedicalOrgPO) basicService.selectByPrimaryKey(TbMedicalOrgPO.class,vo.getOrgId());
				vo.setOrgName(orgPO.getName());
				TbBaseUserPO baseUser= (TbBaseUserPO) basicService.selectByPrimaryKey(TbBaseUserPO.class,vo.getUserId());

				session.setAttribute("deptName",deptPO.getName());
				session.setAttribute("orgName",orgPO.getName());
				session.setAttribute("user",vo);
				session.setAttribute("baseUser",baseUser);
				session.setAttribute("identity",DOCTOR);
				return successMsg("登录成功").add("user",vo.getName()).add("identity",DOCTOR);
			}
		}else if(type.equals(ORG_MANAGER)){
			if(StringUtils.isEmpty(orgId)){
				return failMsg("所属机构不能为空或者不存在");
			}
			example=new BasicExample(TbOrgManagerVO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw).andVarEqualTo("org_id",orgId);;
			List<TbOrgManagerVO> list=basicService.selectByExample(example);
			if(list.size()>0){
				TbOrgManagerVO vo=list.get(0);
				TbMedicalOrgPO orgPO= (TbMedicalOrgPO) basicService.selectByPrimaryKey(TbMedicalOrgPO.class,vo.getOrgId());
				vo.setOrgName(orgPO.getName());
				session.setAttribute("user",vo);
				session.setAttribute("identity",ORG_MANAGER);
				return successMsg("登录成功").add("user",vo.getName()).add("identity",ORG_MANAGER);
			}
		}else if(type.equals(SYS_MANAGER)){
			example=new BasicExample(TbSysManagerPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw);
			List<TbSysManagerPO> list=basicService.selectByExample(example);
			if(list.size()>0){
				TbSysManagerPO po=list.get(0);
				session.setAttribute("user",po);
				session.setAttribute("identity",SYS_MANAGER);
				return successMsg("登录成功").add("user",po.getName()).add("identity",SYS_MANAGER);
			}
		}else if(type.equals(NORMAL)){
			example=new BasicExample(TbBaseUserPO.class);
			Criteria criteria=  example.createCriteria();
			criteria.andVarEqualTo("account",account).andVarEqualTo("passwd",psw);
			TbBaseUserPO po= (TbBaseUserPO) basicService.selectOneByExample(example);
			if(po!=null){
				if(po.getActCode().equals(Constants.USER_EMAIL_UN_IDENTIFY)){
					return failMsg("登录失败,账号尚未激活，请激活邮箱或重新注册再使用");
				}
				session.setAttribute("user",po);
				session.setAttribute("identity",NORMAL);
				return successMsg("登录成功").add("user",po.getName()).add("identity",NORMAL);
			}
		}
		return failMsg("登录失败，账号/密码不正确");
	}

	@RequestMapping("/org/my")
	public String orgMy(){
		return "org/my";
	}
	@RequestMapping("/sys/my")
	public String sysMy(){
		return "sys/my";
	}
	@RequestMapping("/doc/my")
	public String docMy(){
		return "doc/my";
	}
	@RequestMapping("/user/my")
	public String userMy(){
		return "user/my";
	}

	@ResponseBody
	@RequestMapping("register/user")
	public Msg register(TbBaseUserPO user) throws Exception {
		user.setAccount(user.getEmail());
		validUser(user);
		if(StringUtils.isEmpty(user.getIdCard())){
			return failMsg("注册身份证号不能为空");
		}
		//检查是否有认证过得身份证
		BasicExample example=new BasicExample(TbBaseUserPO.class);
		example.createCriteria().andVarEqualTo("id_card",user.getIdCard());
		TbBaseUserPO userPO = (TbBaseUserPO) basicService.selectOneByExample(example);
		if(null!=userPO){
			if(userPO.getActCode().equals(Constants.USER_PASSED)
					||(userPO.getActCode().equals(Constants.USER_UN_APPROVED))){
			return Msg.fail(201,"身份信息已经被注册认证或处于待审批认证中");
			}else{
				userPO.setCreateTime(new Date());
				user.setActCode(0);
				basicService.updateOne(user,true);
				loginService.sendUserRegisterMail(userPO);
				return successMsg("注册成功");
			}
		}
		user.setId(UUID.randomUUID().toString());
		user.setCreateTime(new Date());
		user.setActCode(0);
		basicService.insertOne(user);
		loginService.sendUserRegisterMail(user);
		return successMsg("注册成功");
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
		if(null == user){
			map.put("tips","链接已经失效，请重新注册！");
			map.put("actCode","0");
		}else if(user.getActCode().equals(1)){
			map.put("tips","该邮箱已经激活，可以直接登录使用！");
			map.put("actCode","1");
		}else if(null != user.getCreateTime()&&user.getCreateTime().getTime()-(new Date()).getTime()+24*60*60*1000>0){
			user.setActCode(1);
			if(basicService.updateOne(user,true)>0){
				map.put("tips","该邮箱已经激活，可以直接登录使用！");
				map.put("actCode","1");
			}else{
				//basicService.deleteByPrimaryKey(TbBaseUserPO.class,id);
				map.put("tips","该邮箱激活失败，请重新注册！");
				map.put("actCode","0");
			}
		}else{
			//basicService.deleteByPrimaryKey(TbBaseUserPO.class,id);
			map.put("tips","链接已经失效，请重新注册！");
			map.put("actCode","0");
		}
		return  "actTips";
	}
	@RequestMapping("exit")//1是邮箱认证，2是待审批，3是审批通过，4是不通过
	public Object exit() throws Exception {
		session.removeAttribute(SysUtil.USER);
		session.removeAttribute("identity");
		return "login";
	}

	@RequestMapping("validUser")
	public Object validUser(TbBaseUserPO user) throws MyException, IllegalAccessException, InstantiationException {
		BasicExample example;
		if(StringUtils.isEmpty(user.getEmail())){
			throw new MyException("邮箱不能为空");
		}
		if(!StringUtils.isEmpty(user.getEmail())){
			example=new BasicExample(TbBaseUserPO.class);
			example.createCriteria().andVarEqualTo("email",user.getEmail());
			example.createCriteria().andVarNotEqualTo("act_code","0");
			if(basicService.countByExample(example)>0){
				throw new MyException("该邮箱已经被注册且验证,可以直接登录");
			}
		}
		return successMsg().add("msg","验证通过");
	}

	@ResponseBody
	@RequestMapping("login/getOrg")
	public Msg getOrg(String loginAccount,String type) throws Exception {
		BasicExample example;
		List<String> orgId=new ArrayList<>();
		if(type.equals(ORG_MANAGER)){
			example = new BasicExample(TbOrgManagerPO.class);
			example.createCriteria().andVarEqualTo("account",loginAccount);
			List<TbOrgManagerPO> list=basicService.selectByExample(example);
			for (TbOrgManagerPO po : list) {
				orgId.add(po.getOrgId());
			}

		}else if(type.equals(DOCTOR)){
			example = new BasicExample(TbDoctorPO.class);
			example.createCriteria().andVarEqualTo("account",loginAccount);
			List<TbDoctorPO> list=basicService.selectByExample(example);
			for (TbDoctorPO po : list) {
				orgId.add(po.getOrgId());
			}
		}
		List<TbMedicalOrgPO> list=basicService.selectByPrimaryKey(TbMedicalOrgPO.class,orgId);
		return Msg.success().add("list",list);
	}

	/**
	 * 获取最近入住的机构
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("login/orgList")
	public Msg getOrg() throws Exception {
		BasicExample example = new BasicExample(TbMedicalOrgPO.class);
		example.setOrderByClause("create_time");
		Page page=PageHelper.startPage(1,10);
		List<TbMedicalOrgPO> list=basicService.selectByExample(example);
		PageInfo result= new PageInfo(page.getResult());
		return Msg.success().add("data",result);
	}

	@ResponseBody
	@RequestMapping("contact")
	public Msg contactUs(TbContactUsPO contactUsPO){
		if(null==contactUsPO.getContent()||null==contactUsPO.getTitle()){
			return failMsg("标题或者内容不能为空");
		}
		if(contactUsPO.getEmail()==null&&contactUsPO.getPhone()==null){
			return failMsg("电子邮箱或者电话须填写至少一项");
		}
		contactUsPO.setId(UUID36());
		contactUsPO.setCreateTime(new Date());
		contactUsPO.setIsRead(0);
		basicService.insertOne(contactUsPO);
		return successMsg("反馈成功，我们将会尽快和您联系！");
	}
	/**
	 * 找回密码
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("user/findPW")
	public Msg findPW(@RequestParam(required = true) String email,String orgId,@RequestParam(required = true)String type) throws Exception {
		BasicExample example =new BasicExample();
		if(type.equals(NORMAL)){
			example.setClazz(TbBaseUserPO.class);
			example.createCriteria().andVarEqualTo("email",email);
		}else if(type.equals(DOCTOR)){
			if(StringUtils.isEmpty(orgId)){
				return failMsg("机构不能为空");
			}
			example.setClazz(TbDoctorPO.class);
			example.createCriteria().andVarEqualTo("account",email);
			example.createCriteria().andVarEqualTo("org_id",orgId);
		}else if(type.equals(ORG_MANAGER)){
			if(StringUtils.isEmpty(orgId)){
				return failMsg("机构不能为空");
			}
			example.setClazz(TbOrgManagerPO.class);
			example.createCriteria().andVarEqualTo("email",email);
			example.createCriteria().andVarEqualTo("org_id",orgId);
		}
	    Object obj =	basicService.selectOneByExample(example);
		if(obj==null){
			return failMsg("账号/邮箱不存在");
		}

		if(type.equals(NORMAL)){
			TbBaseUserPO userPO = (TbBaseUserPO)obj;
			loginService.findPW(email,userPO.getName(),userPO.getAccount(),userPO.getPasswd(),type);
		}else if(type.equals(DOCTOR)){
			TbDoctorPO doctorPO = (TbDoctorPO) obj;
			loginService.findPW(email,doctorPO.getName(),doctorPO.getAccount(),doctorPO.getPasswd(),type);
		}else if(type.equals(ORG_MANAGER)){
			TbOrgManagerPO managerPO = (TbOrgManagerPO) obj;
			loginService.findPW(email,managerPO.getName(),managerPO.getAccount(),managerPO.getPasswd(),type);
		}
		return  successMsg("已经通过电子邮件给您重新下发密码，请注意查收!");
	}
}
