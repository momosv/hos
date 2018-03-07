package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbDepartmentPO;
import cn.momosv.hos.model.TbDoctorPO;
import cn.momosv.hos.model.TbOrgManagerPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.OrgService;
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
@RequestMapping("org")
public class OrgController extends BasicController {

    @Autowired
    OrgService orgService;

    @RequestMapping("addDoctor")
    public Object addDoctor( TbDoctorPO doctor,TbBaseUserPO user,String deptName) throws Exception {
        TbOrgManagerVO org = validOrgManager();
        doctor.setOrgId(org.getOrgId());
        if(StringUtils.isEmpty(user.getEmail())||StringUtils.isEmpty(doctor.getPasswd())){
            return  failMsg("邮箱或者密码不能为空");
        }
        if(StringUtils.isEmpty(user.getIdCard())){
            return  failMsg("身份证号不能为空");
        }
        if(StringUtils.isEmpty(doctor.getName())){
            return failMsg("名字不能为空");
        }
        if(orgService.validDoctorIdCardExist(org.getOrgId(),user.getIdCard())){
            return failMsg("此身份证的人员已经存在本机构");
        }
        if(orgService.validDoctorEMailExist(org.getOrgId(),user.getEmail())){
            return failMsg("此邮箱的人员已经存在本机构");
        }
      //  List<TbDoctorPO> list=orgService.getDoctorByIdCard(org.getOrgId(),user.getIdCard());
        orgService.insertDoctor(user,doctor,deptName);
        return successMsg();
    }

    @RequestMapping("updateDoctor")
    public Object updateDoctor(String docId,
                               String name,
                                Integer sex,
                                Integer isLeave,
                               String deptId,
                               String position,
                                Date entryTime,
                               String workCode,
                               String passwd,
                                String telephone,
                                 String address,
                                 String deptName
                               ) throws Exception {
        TbOrgManagerVO org = validOrgManager();
        TbDoctorPO doctorPO= (TbDoctorPO) basicService.selectByPrimaryKey(TbDoctorPO.class,docId);
        if(null==doctorPO){
            return failMsg("该人员不存在");
        }
        if(!org.getOrgId().equals(doctorPO.getOrgId())){
            return failMsg("您无权限查看非本机构人员");
        }
        doctorPO.setName(name);
        doctorPO.setDeptId(deptId);
        doctorPO.setPosition(  position);
        doctorPO.setEntryTime(  entryTime);
        doctorPO.setWorkCode(workCode);
        doctorPO.setWorkCode(passwd);
        doctorPO.setIsLeave(isLeave);

        TbBaseUserPO userPO= (TbBaseUserPO) basicService.selectByPrimaryKey(TbBaseUserPO.class,doctorPO.getUserId());
        userPO.setSex(sex);
        userPO.setTelephone(telephone);
        userPO.setAddress(address);
        orgService.updateDoctor(doctorPO,userPO,org,deptName);
        return successMsg();

    }

    @RequestMapping("getDoctorDetail")
    public Msg getDoctorDetail(String id) throws Exception {
        TbOrgManagerVO org = validOrgManager();
        TbDoctorPO doctorPO= (TbDoctorPO) basicService.selectByPrimaryKey(TbDoctorPO.class,id);
        if(!org.getOrgId().equals(doctorPO.getOrgId())){
            return failMsg("您无权限查看非本机构人员");
        }
        TbBaseUserPO userPO= (TbBaseUserPO) basicService.selectByPrimaryKey(TbBaseUserPO.class,doctorPO.getUserId());
        if(null!=userPO) {
            userPO.setPasswd("");
            userPO.setIdFace("");
            userPO.setIdHand("");
            userPO.setIdNational("");
        }

        return getDeptList().add("doctor",doctorPO).add("user",userPO);

    }

    @RequestMapping("getDoctorList")
    public Msg getDoctorList(@RequestParam(defaultValue = "-1") Integer isLeave,
                             @RequestParam(defaultValue = "",required = true)String deptId,
                             String key,
                             @RequestParam(defaultValue = "1")Integer pageNum,
                             @RequestParam(defaultValue = "10")Integer pageSize) throws Exception {
        TbOrgManagerVO org= validOrgManager();
        BasicExample example =new BasicExample(TbDoctorPO.class);
        BasicExample.Criteria criteria=example.createCriteria();
        criteria.andVarEqualTo("org_id",org.getOrgId())
                .andVarEqualTo("dept_id",deptId);
        example.setOrderByClause("name");
        if(!isLeave.equals(-1)){
            criteria.andVarEqualTo("is_leave",isLeave.toString());
        }
        if(!StringUtils.isEmpty(key)){
            criteria.andVarLike("name","%"+key+"%");
        }
        Page page= PageHelper.startPage(pageNum,pageSize);
     basicService.selectByExample(example);
      return successMsg().add("page",new PageInfo(page.getResult()));
    }

    @RequestMapping("getDeptList")
    public Msg getDeptList() throws Exception {
        TbOrgManagerVO org= validOrgManager();
        BasicExample example =new BasicExample(TbDepartmentPO.class);
        example.createCriteria().andVarEqualTo("org_id",org.getOrgId());
        example.setOrderByClause("name");
        return successMsg().add("list",basicService.selectByExample(example));
    }

    @RequestMapping("addDept")
    public Object addDept(TbDepartmentPO dept) throws Exception {
        TbOrgManagerVO org = validOrgManager();
        if(StringUtils.isEmpty(dept.getName())){
            return failMsg("部门/科室名称不能为空");
        }
        BasicExample example=new BasicExample(TbOrgManagerPO.class);
        example.createCriteria().andVarEqualTo("name",dept.getName());
        if(basicService.countByExample(example)>0){
            return failMsg("部门/科室名称已经存在");
        }
        dept.setOrgId(org.getOrgId());
        dept.setId(UUID36());
        basicService.insertOne(dept);
        return successMsg("添加成功");
    }

    @RequestMapping("getAuthorityList")
    public Msg getAuthorityList(@RequestParam(defaultValue = "-2") int isAllow,//-1未审批,-2全部
                             String key,
                             String keyType,
                             @RequestParam(defaultValue = "1")Integer pageNum,
                             @RequestParam(defaultValue = "10")Integer pageSize) throws Exception {
        TbOrgManagerVO org= validOrgManager();
        return successMsg().add("page",orgService.getAuthorityList(isAllow,key,keyType,pageNum,pageSize,org.getOrgId()));
    }


    private TbOrgManagerVO validOrgManager() throws Exception {
        try{
            return (TbOrgManagerVO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录机构管理员后再操作");
        }
    }
}
