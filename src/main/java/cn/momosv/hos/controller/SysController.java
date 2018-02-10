package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbMedicalOrgPO;
import cn.momosv.hos.model.TbSysManagerPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.SysService;
import cn.momosv.hos.util.Constants;
import cn.momosv.hos.util.SysUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys")
public class SysController extends BasicController{

    @Autowired
    private SysService sysService;

    @RequestMapping("getOrgList")
    public Object getOrgList(@RequestParam(name="type",defaultValue = "2") Integer type,
                             @RequestParam(name="key",defaultValue = "") String key,
                             @RequestParam(name="pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(name="pageSize",defaultValue = "10")Integer pageSize) throws Exception {
        TbSysManagerPO sys=validSysManager();
        BasicExample example=new BasicExample(TbMedicalOrgPO.class);
        if(type.equals(0)){
            example.createCriteria().andVarEqualTo("act_code","0");
        }
        if(type.equals(1)){
            example.createCriteria().andVarEqualTo("act_code","1");
        }
        if(!StringUtils.isEmpty(key)){
            example.createCriteria().andVarLike("name",key);
        }
       Page page= PageHelper.startPage(pageNum, pageSize);
        basicService.selectByExample(TbMedicalOrgPO.class,example);
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
        if(type.equals(Constants.USER_ALL_TYPE)){//提交认证的全部
            example.createCriteria().andVarNotBetween("act_code",Constants.USER_EMAIL_UN_IDENTIFY.toString(),Constants.USER_EMAIL_IDENTIFY.toString());
        }else if(type.equals(Constants.USER_PASSED)){//通过
            example.createCriteria().andVarNotEqualTo("act_code",Constants.USER_PASSED.toString());
        }else if(type.equals(Constants.USER_UN_PASSED)){//不通过
            example.createCriteria().andVarNotEqualTo("act_code",Constants.USER_UN_PASSED.toString());
        }else if(type.equals(Constants.USER_UN_APPROVED)){//未审批
            example.createCriteria().andVarEqualTo("act_code",Constants.USER_UN_APPROVED.toString());
        }else{//审批
            example.createCriteria().andVarBetween("act_code",Constants.USER_PASSED.toString(),Constants.USER_UN_PASSED.toString());
        }
        Page page = PageHelper.startPage(pageNum, pageSize);
        basicService.selectByExample(example);
        return Msg.success().add("page",new PageInfo(page.getResult()));
    }

    @RequestMapping("userDetail")
    public Object userDetail(@RequestParam(name="id",required = true)String id) throws Exception {
        TbSysManagerPO sys=validSysManager();
        Object obj= basicService.selectByPrimaryKey(TbBaseUserPO.class,id);
        return Msg.success().add("org",obj);
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

    private TbSysManagerPO validSysManager() throws Exception {
        try{
            return (TbSysManagerPO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录系统管理员后再操作");
        }
    }
}
