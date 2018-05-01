package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.exception.MyException;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbCasePO;
import cn.momosv.hos.model.TbDataAuthorityPO;
import cn.momosv.hos.model.TbOrgPatientPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.BasicExample.Criteria;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.DoctorService;
import cn.momosv.hos.service.OrgService;
import cn.momosv.hos.service.UserService;
import cn.momosv.hos.util.Constants;
import cn.momosv.hos.util.SysUtil;
import cn.momosv.hos.vo.TbDoctorVO;
import cn.momosv.hos.vo.TbOrgManagerVO;
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

    @Autowired
    public OrgService orgService;

    @Autowired
    DoctorService doctorService;

    @RequestMapping("getCaseList")
    public Object getCaseList(
                                String key,
                                String keyType,
                              @RequestParam(name="pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(name="pageSize",defaultValue = "10")int pageSize) throws Exception {
        TbBaseUserPO userPO=validUser();
        if(!userPO.getActCode().equals(Constants.USER_PASSED)){
            return failMsg("身份信息尚未通过实名认证，不能进行病历查阅，请到个人中心进行实名认证申请！");
        }
        List<String> pList= userService.getPatientIdListByIdCard(userPO.getIdCard());
        if(pList.size()==0){
            return Msg.success().add("page",null);
        }
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
        criteria.andVarIn("patient_id",pList);

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
        TbBaseUserPO user=null;
        TbCasePO casePO = (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,id);
        if(null==casePO){
            return failMsg("该病历数据不存在或者已经被删除");
        }
        try{
          TbOrgManagerVO orgVO = validOrgManager();
          if(!orgVO.getOrgId().equals(casePO.getOrgId())){
                return failMsg("您无权限查看该病历数据");
          }
        }catch (Exception e){
            try {
               TbDoctorVO doctorVO = validDoctor();
               if(!doctorService.checkAuth(doctorVO,casePO.getId())){
                   return failMsg("您无权限查看该病历数据");
               }

            }catch (Exception ed){
                TbBaseUserPO userPO0 = userService.getUserByPatientId(casePO.getPatientId());
                 user = validUser();
                if(!user.getActCode().equals(Constants.USER_PASSED)){
                    return failMsg("身份信息尚未通过实名认证，不能进行病历查阅，请到个人中心进行实名认证申请！");
                }
                if(!userPO0.getIdCard().equals(user.getIdCard())){
                    return failMsg("您无权限查看该病历数据");
                }
            }

        }
        if(user==null) {
            user = userService.getUserByPatientId(casePO.getPatientId());
        }
        TbOrgPatientPO patientPO = (TbOrgPatientPO) basicService.selectByPrimaryKey(TbOrgPatientPO.class,casePO.getPatientId());
        return successMsg("获取成功").add("patient",patientPO).add("user",user).add("case",casePO);
    }


    @RequestMapping("userDetail")
    public Object getUserDetail() throws Exception {
        TbBaseUserPO user = validUser();
        TbBaseUserPO user0 = (TbBaseUserPO) basicService.selectByPrimaryKey(TbBaseUserPO.class,user.getId());
        if(user0!=null)
        user0.setPasswd("");
        return successMsg().add("user",user0);
    }

    @RequestMapping("validPW")
    public Object validPW(@RequestParam(required = true) String oldP,@RequestParam(required = true)String id) throws Exception {
        TbBaseUserPO user0 = (TbBaseUserPO) basicService.selectByPrimaryKey(TbBaseUserPO.class,id);
        if(user0!=null&&oldP.trim().equals(user0.getPasswd())){
            return successMsg("验证通过");
        }
        return failMsg("旧密码验证不通过");
    }


    @RequestMapping("updateMy")
    public Msg updateMy(TbBaseUserPO userPO) throws Exception {
        TbBaseUserPO old = validUser();
        if(userPO.getIdCard()!=null&&!userPO.getIdCard().equals(old.getIdCard())){
            return failMsg("不能对身份证号进行修改");
        }
        userPO.setId(old.getId());
        userPO.setAccount(userPO.getEmail());
        BasicExample example=new BasicExample(TbBaseUserPO.class);
        example.createCriteria().andVarEqualTo("account",userPO.getAccount()).andVarNotEqualTo("id",old.getId());
        example.or().andVarEqualTo("email",userPO.getEmail()).andVarNotEqualTo("id",old.getId());
        List list= basicService.selectByExample(example);
        if(list.size()>0){
            return failMsg("账号/邮箱已经被其他用户注册");
        }
        basicService.updateOne(userPO,true);
        return successMsg("更改成功");
    }
    @RequestMapping("updatePW")
    public Msg updatePW(TbBaseUserPO userPO) throws Exception {
        if(StringUtils.isEmpty(userPO.getPasswd())){
            return successMsg("更改失败，密码不能为空");
        }
        TbBaseUserPO old = validUser();
        userPO.setId(old.getId());
        basicService.updateOne(userPO,true);
        return successMsg("更改成功，请退出重新登录生效");
    }


    @RequestMapping("updateMyA")
    public Msg updateMyA(TbBaseUserPO userPO) throws Exception {
        TbBaseUserPO old = validUser();
        userPO.setId(old.getId());
        userPO.setActCode(2);//状态修改为申请待审批
        if(userPO.getIdCard()!=null&&!userPO.getIdCard().equals(old.getIdCard())){
            return failMsg("不能对身份证号进行修改");
        }
        userPO.setIdCard(null);
        basicService.updateOne(userPO,true);
        return successMsg("提交申请成功");
    }


    @RequestMapping("getAuthorityList")
    public Msg getAuthorityList(@RequestParam(defaultValue = "-2") int isAllow,//-1未审批,-2全部
                                String key,
                                String keyType,
                                @RequestParam(defaultValue = "1")Integer pageNum,
                                @RequestParam(defaultValue = "10")Integer pageSize) throws Exception {
        TbBaseUserPO user= validUser();
        if(!user.getActCode().equals(Constants.USER_PASSED)){
            return failMsg("身份信息尚未通过实名认证，不能进行病历权限审批，请到个人中心进行实名认证申请！");
        }
        Page page= PageHelper.startPage(pageNum,pageSize);
        userService.getAuthorityList(isAllow,key,keyType,user);
        return successMsg().add("page",new PageInfo<>(page.getResult()));
    }

    @RequestMapping("getAuthorityDetail")
    public Msg getAuthorityListDetail(String authId) throws Exception {
        TbBaseUserPO user= validUser();
        return successMsg().add("detail",userService.getAuthorityDetail(authId,user.getIdCard()));
    }

    @RequestMapping("approveAuthority")
    public Msg getAuthorityListDetail(TbDataAuthorityPO auth) throws Exception {
        TbBaseUserPO user= validUser();
        if(StringUtils.isEmpty(auth.getId())){
            return failMsg("权限id不能为空");
        }
        TbDataAuthorityPO old= (TbDataAuthorityPO) basicService.selectByPrimaryKey(TbDataAuthorityPO.class,auth.getId());
        if(!old.getUserId().equals(user.getIdCard())){
            return failMsg("您无权限操作不是本人数据");
        }
        if(auth.getDeadline()==null){
            return failMsg("权限截止日期不能为空");
        }
        auth.setCaseId(old.getCaseId());
        auth.setDoctorId(old.getDoctorId());
        basicService.updateOne(auth,true);
        orgService.sendAuthApproveMsg(auth);
        return successMsg("更新成功");
    }

    private TbBaseUserPO validUser() throws Exception {
        try{
            return (TbBaseUserPO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录个人身份账号后再操作");
        }
    }

    private TbDoctorVO validDoctor() throws Exception {
        try{
            return (TbDoctorVO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录医生身份账号后再操作");
        }
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
