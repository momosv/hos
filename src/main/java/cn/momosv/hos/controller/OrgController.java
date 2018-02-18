package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbDepartmentPO;
import cn.momosv.hos.model.TbDoctorPO;
import cn.momosv.hos.model.TbOrgManagerPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.service.OrgService;
import cn.momosv.hos.util.SysUtil;
import cn.momosv.hos.vo.TbOrgManagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
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

    private TbOrgManagerVO validOrgManager() throws Exception {
        try{
            return (TbOrgManagerVO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录机构管理员后再操作");
        }
    }
}
