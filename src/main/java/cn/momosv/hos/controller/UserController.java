package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbCasePO;
import cn.momosv.hos.model.TbOrgPatientPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.BasicExample.Criteria;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.UserService;
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
@RequestMapping("/user")
public class UserController extends BasicController{


    @Autowired
    UserService userService;

    @RequestMapping("getCaseList")
    public Object getCaseList(
                                String key,
                                String keyType,
                              @RequestParam(name="pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(name="pageSize",defaultValue = "10")int pageSize) throws Exception {
        TbBaseUserPO userPO=validUser();
        BasicExample example=new BasicExample();
        Criteria criteria=example.createCriteria();
        if(!StringUtils.isEmpty(key)) {
            if(keyType.equals("diagnosis")){
                criteria.andVarLike("diagnosis","%"+key+"%");
            }else if(keyType.equals("docName")){
                criteria.andVarLike("doc.name","%"+key+"%");
            }else{
                criteria.andVarLike("org.name","%"+key+"%");
            }
        }
       example.setTName("tb_case c,tb_doctor doc,tb_medical_org org,tb_department dept");
       example.setCol("c.id,c.diagnosis,c.create_time,doc.name as doc_name,org.name as org_name,dept.name as dept_name");
        criteria.andJoin("c.org_id=org.id and doc.id=c.doctor_id and c.dept_id=dept.id");
        Page page = PageHelper.startPage(pageNum, pageSize);
        basicService.selectJoint(example);
        return Msg.success().add("page",new PageInfo(page.getResult()));
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("getCase")
    public Object getCase(String id) throws Exception {
        TbBaseUserPO user=   validUser();
        TbCasePO casePO = (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,id);
        TbOrgPatientPO patientPO=null;
        if(null!=casePO){
             patientPO= (TbOrgPatientPO) basicService.selectByPrimaryKey(TbOrgPatientPO.class,casePO.getPatientId());
        }
        return successMsg("获取成功").add("patient",patientPO).add("user",user).add("case",casePO);
    }

    private TbBaseUserPO validUser() throws Exception {
        try{
            return (TbBaseUserPO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录个人身份账号后再操作");
        }
    }

}
