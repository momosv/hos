package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.*;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.SysService;
import cn.momosv.hos.util.Constants;
import cn.momosv.hos.util.SysUtil;
import cn.momosv.hos.vo.TbOrgManagerVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sys")
public class SysController extends BasicController{

    @Autowired
    private SysService sysService;

    @RequestMapping("getOrgList")
    public Object getOrgList(@RequestParam(name="type",defaultValue = "3") Integer type,
                             @RequestParam(name="key",defaultValue = "") String key,
                             @RequestParam(name="pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(name="pageSize",defaultValue = "10")Integer pageSize) throws Exception {
        TbSysManagerPO sys=validSysManager();
        BasicExample example=new BasicExample(TbMedicalOrgPO.class);
        BasicExample.Criteria criteria=example.createCriteria();
        if(type.equals(0)){//待审批
            criteria.andVarEqualTo("act_code","0");
        }
        if(type.equals(1)){//已经审批
            criteria.andVarBetween("act_code","1","2");
        }

        if(!StringUtils.isEmpty(key)){
            criteria.andVarLike("name","%"+key+"%");
        }
        example.setOrderByClause("create_time desc");
        Page page= PageHelper.startPage(pageNum, pageSize);
        basicService.selectByExample(example);
        return Msg.success().add("page",new PageInfo(page.getResult()));
    }

    @RequestMapping("orgApprove")
    public Object orgApprove(@RequestParam(name="id",required = true)List id,
                             @RequestParam(name="act",defaultValue = "0")Integer act) throws Exception {
        TbSysManagerPO sys=validSysManager();
        sysService.updateMedicalAct(id,act);
        return Msg.success();
    }
    @RequestMapping("orgDetail")
    public Object orgDetail(@RequestParam(name="id",required = true)String id) throws Exception {
        TbSysManagerPO sys=validSysManager();
         Object obj= basicService.selectByPrimaryKey(TbMedicalOrgPO.class,id);
        return Msg.success().add("org",obj);
    }


    @RequestMapping("getUserList")
    public Object getUserList(@RequestParam(name="type",defaultValue = "-1") Integer type,//1是邮箱认证，2是待审批，3是审批通过，4是不通过
                             @RequestParam(name="key",defaultValue = "") String key,
                             @RequestParam(name="pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(name="pageSize",defaultValue = "10")Integer pageSize) throws Exception {
        TbSysManagerPO sys=validSysManager();
        BasicExample example=new BasicExample(TbBaseUserPO.class);
        BasicExample.Criteria criteria=example.createCriteria();

        if(type.equals(Constants.USER_ALL_TYPE)){//提交认证的全部
            criteria.andVarNotBetween("act_code",Constants.USER_EMAIL_UN_IDENTIFY.toString(),Constants.USER_EMAIL_IDENTIFY.toString());
        }else if(type.equals(Constants.USER_PASSED)){//通过
            criteria.andVarEqualTo("act_code",Constants.USER_PASSED.toString());
        }else if(type.equals(Constants.USER_UN_PASSED)){//不通过
            criteria.andVarEqualTo("act_code",Constants.USER_UN_PASSED.toString());
        }else if(type.equals(Constants.USER_UN_APPROVED)){//未审批
            criteria.andVarEqualTo("act_code",Constants.USER_UN_APPROVED.toString());
        }else{//审批
            criteria.andVarBetween("act_code",Constants.USER_PASSED.toString(),Constants.USER_UN_PASSED.toString());
        }
        if(!StringUtils.isEmpty(key)){
            criteria.andVarLike("name","%"+key+"%");
        }
        Page page = PageHelper.startPage(pageNum, pageSize);
        basicService.selectByExample(example);
        return Msg.success().add("page",new PageInfo(page.getResult()));
    }

    @RequestMapping("userDetail")
    public Object userDetail(@RequestParam(name="id",required = true)String id) throws Exception {
        TbSysManagerPO sys=validSysManager();
        Object obj= basicService.selectByPrimaryKey(TbBaseUserPO.class,id);
        return Msg.success().add("detail",obj);
    }

    @RequestMapping("userApprove")//1是邮箱认证，2是待审批，3是审批通过，4是不通过
    public Object userApprove(@RequestParam(name="id",required = true)List id,
                             @RequestParam(name="act",defaultValue = "2")Integer act) throws Exception {
        TbSysManagerPO sys=validSysManager();
        sysService.updateUserAct(id,act);
        return Msg.success();
    }

    @RequestMapping("addSys")
    public Object addSys(TbSysManagerPO sysManagerPO) throws Exception {
        TbSysManagerPO sys=validSysManager();
        basicService.insertOne(sysManagerPO);
        return Msg.success();
    }

    @RequestMapping("updateSys")
    public Object updateSys(TbSysManagerPO sysManagerPO) throws Exception {
        TbSysManagerPO sys=validSysManager();
        basicService.updateOne(sysManagerPO,true);
        return Msg.success();
    }
    @RequestMapping("deleteSys")
    public Object deleteSys(String id) throws Exception {
        TbSysManagerPO sys=validSysManager();
        basicService.deleteByPrimaryKey(TbSysManagerPO.class,id);
        return Msg.success();
    }

    @RequestMapping("getContactList")
    public Msg getContactList(@RequestParam(defaultValue = "-1")String type,String key,@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize) throws Exception {
            BasicExample example = new BasicExample(TbContactUsPO.class);
            BasicExample.Criteria criteria = example.createCriteria();
            example.setOrderByClause("create_time desc");
            if(!StringUtils.isEmpty(key)){
                criteria.andVarLike("title","%"+key+"%");
            }
            if(type.equals("1")||type.equals("0")){
                criteria.andVarLike("is_read",type);
            }
            Page page=PageHelper.startPage(pageNum,pageSize);
            basicService.selectByExample(example);
            return successMsg().add("page",new PageInfo<>(page.getResult()));
    }
    @RequestMapping("reply")
    public Msg replyContact(TbContactUsPO contact) throws Exception {
        TbContactUsPO old = (TbContactUsPO) basicService.selectByPrimaryKey(TbContactUsPO.class,contact.getId());
        if(null==old){
            return failMsg("回复失败，来访信息不存在");
        }
        if(StringUtils.isEmpty(contact.getReply())){
            return failMsg("回复失败，回复内容不能为空");
        }
        contact.setIsRead(1);
        contact.setIsDeal(1);
        basicService.updateOne(contact,true);
        old.setReply(contact.getReply());
        sysService.reply(old);
        return successMsg("回复成功，已向来信人发送邮件");
    }

    @RequestMapping("updateMy")
    public Msg updateMy(TbSysManagerPO man) throws Exception {
        TbSysManagerPO old = validSysManager();
        if(StringUtils.isEmpty(man.getAccount())||StringUtils.isEmpty(man.getEmail())){
            return failMsg("账号/电子邮箱不能为空");
        }
        if(StringUtils.isEmpty(man.getPasswd())){
            return failMsg("密码不能为空");
        }
        BasicExample example=new BasicExample(TbSysManagerPO.class);
        example.createCriteria().andVarEqualTo("email",man.getEmail()).andVarNotEqualTo("id",old.getId());
        List list= basicService.selectByExample(example);
        if(list.size()>0){
            return failMsg("邮箱已经存在");
        }
        example=new BasicExample(TbSysManagerPO.class);
        example.createCriteria().andVarEqualTo("account",man.getAccount()).andVarNotEqualTo("id",old.getId());
        list= basicService.selectByExample(example);
        if(list.size()>0){
            return failMsg("账号已经存在");
        }
        man.setId(old.getId());
        basicService.updateOne(man,true);
        return successMsg("更改成功，请退出重新登录生效");
    }

    @RequestMapping("addChildManager")
    public Msg addChildManager(TbSysManagerPO man) throws Exception {
        if(StringUtils.isEmpty(man.getAccount())||StringUtils.isEmpty(man.getEmail())){
            return failMsg("账号/电子邮箱不能为空");
        }
        if(StringUtils.isEmpty(man.getPasswd())){
            return failMsg("密码不能为空");
        }

        TbSysManagerPO old = validSysManager();
        BasicExample example=new BasicExample(TbSysManagerPO.class);
        example.createCriteria().andVarEqualTo("email",man.getEmail());
        List list= basicService.selectByExample(example);
        if(list.size()>0){
            return failMsg("邮箱已经存在");
        }
        example=new BasicExample(TbSysManagerPO.class);
        example.createCriteria().andVarEqualTo("account",man.getAccount());
        list= basicService.selectByExample(example);
        if(list.size()>0){
            return failMsg("账号已经存在");
        }
        man.setCreateTime(new Date());
        man.setId(UUID36());
        man.setGrade(old.getGrade()+1);
        basicService.insertOne(man);
        return successMsg("添加成功");
    }
    @RequestMapping("getChildManager")
    public Msg getChildManager(String id) throws Exception {
        TbSysManagerPO sys = validSysManager();
        TbSysManagerPO child = (TbSysManagerPO) basicService.selectByPrimaryKey(TbSysManagerPO.class,id);
        if(child==null){
            return failMsg("账号不存在或者已经被删除");
        }
        if(child.getGrade()<=sys.getGrade()){
            return failMsg("您无权限查看同级或者上级管理员");
        }
        return successMsg().add("detail",child);
    }

    @RequestMapping("updateChildManager")
    public Msg updateChildManager(TbSysManagerPO man) throws Exception {
        TbSysManagerPO sys = validSysManager();
        if(StringUtils.isEmpty(man.getAccount())||StringUtils.isEmpty(man.getEmail())){
            return failMsg("账号/电子邮箱不能为空");
        }
        if(StringUtils.isEmpty(man.getPasswd())){
            return failMsg("密码不能为空");
        }
        TbSysManagerPO old = (TbSysManagerPO) basicService.selectByPrimaryKey(TbSysManagerPO.class,man.getId());
        if(null==old){
            return failMsg("子账号不存在或者已经被删除");
        }
        if(old.getGrade()<=sys.getGrade()){
            return failMsg("您无权限删除同级或者上级管理员");
        }
        BasicExample example=new BasicExample(TbSysManagerPO.class);
        example.createCriteria().andVarEqualTo("email",man.getEmail()).andVarNotEqualTo("id",man.getId());
        List list= basicService.selectByExample(example);
        if(list.size()>0){
            return failMsg("邮箱已经存在");
        }
        example=new BasicExample(TbSysManagerPO.class);
        example.createCriteria().andVarEqualTo("account",man.getAccount()).andVarNotEqualTo("id",man.getId());
        list= basicService.selectByExample(example);
        if(list.size()>0){
            return failMsg("账号已经存在");
        }
        basicService.updateOne(man,true);
        return successMsg("更改成功");
    }

    @RequestMapping("deleteChildManager")
    public Msg deleteChildManager(String id) throws Exception {
        TbSysManagerPO sys = validSysManager();
        TbSysManagerPO old = (TbSysManagerPO) basicService.selectByPrimaryKey(TbSysManagerPO.class,id);
        if(null==old){
            return failMsg("子账号不存在或者已经被删除");
        }
        if(sys.getGrade()>=old.getGrade()){
            return failMsg("您无权限删除同级或者上级管理员");
        }
        basicService.deleteByPrimaryKey(TbSysManagerPO.class,id);
        return successMsg("删除成功");
    }

    @RequestMapping("getChildManagerList")
    public Msg getChildManager(String key,@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize ) throws Exception {
        TbSysManagerPO sys = validSysManager();
        BasicExample example=new BasicExample(TbSysManagerPO.class);
       BasicExample.Criteria  criteria = example.createCriteria();
        criteria.andVarGreaterThan("grade",sys.getGrade().toString());
        if(!StringUtils.isEmpty(key)){
        criteria.andVarLike("name","%"+key+"%");
        }
        Page page=PageHelper.startPage(pageNum,pageSize);
        basicService.selectByExample(example);
        return successMsg().add("page",new PageInfo<>(page.getResult()));
    }


    private TbSysManagerPO validSysManager() throws Exception {
        try{
            return (TbSysManagerPO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录系统管理员后再操作");
        }
    }
}
