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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/sys")
public class SysController extends BasicController{
    @Autowired
    private BasicService basicService;

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
        PageHelper.startPage(pageNum, pageSize);
        List list=  basicService.selectByExample(TbMedicalOrgPO.class,example);
        PageInfo page = new PageInfo(list);
        return Msg.success().add("page",page);
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
    public Object getUserList(@RequestParam(name="type",defaultValue = "-1") Integer type,//全部-1,0未审批，1通过，2不通过，3已经审批
                             @RequestParam(name="key",defaultValue = "") String key,
                             @RequestParam(name="pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(name="pageSize",defaultValue = "10")Integer pageSize) throws Exception {
        TbSysManagerPO sys=validSysManager();
        BasicExample example=new BasicExample(TbBaseUserPO.class);
        if(type.equals(Constants.USER_UN_APPROVED)){//未审批
            example.createCriteria().andVarEqualTo("act_code",type.toString());
        }else if(type.equals(Constants.USER_APPROVED)){//审批
            example.createCriteria().andVarNotEqualTo("act_code",Constants.USER_UN_APPROVED.toString());
        }else if(type.equals(Constants.USER_PASSED)){//通过
            example.createCriteria().andVarNotEqualTo("act_code",Constants.USER_PASSED.toString());
        }else if(type.equals(Constants.USER_UN_PASSED)){//不通过
            example.createCriteria().andVarNotEqualTo("act_code",Constants.USER_UN_PASSED.toString());
        }
        example.createCriteria().andVarIsNotNull("id_face");
        PageHelper.startPage(pageNum, pageSize);
        List list=  basicService.selectByExample(example);
        PageInfo page = new PageInfo(list);
        return Msg.success().add("page",page);
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
