package cn.momosv.hos.service.impl;

import cn.momosv.hos.dao.TbOrgManagerPOMapper;
import cn.momosv.hos.email.MailService;
import cn.momosv.hos.model.*;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.OrgService;
import cn.momosv.hos.service.UserService;
import cn.momosv.hos.util.SysUtil;
import cn.momosv.hos.vo.TbOrgManagerVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("orgService")
public class OrgServiceImpl implements OrgService {

    @Autowired
    TbOrgManagerPOMapper orgMapper;

    @Autowired
    public BasicService basicService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    @Autowired
    FreeMarkerConfig freeMarkerConfig;

    @Autowired
    MailService mailService;


    @Override
    public boolean validDoctorIdCardExist(String orgId, String idCard){
        return orgMapper.countDoctorByIdCard(orgId,idCard)>0;
    }
    @Override
    public boolean validDoctorEMailExist(String orgId, String email){
        return orgMapper.countDoctorByEmail(orgId,email)>0;
    }
    @Override
    public List<TbDoctorPO> getDoctorByIdCard(String orgId, String idCard){
        return orgMapper.getDoctorByIdCard(orgId,idCard);
    }

    @Override
    public void insertDoctor(TbBaseUserPO user, TbDoctorPO doctor,String deptName) throws Exception {
        //获取baseUser
        TbBaseUserPO oldUser= userService.getUserByIdCard(user.getIdCard());
        Map<String, String> map = new HashedMap();
        if(null != oldUser){
            doctor.setUserId(oldUser.getId());
            map.put("new", "0");
        }else {
            user.setId(UUID.randomUUID().toString());
            user.setAccount(user.getEmail());
            user.setPasswd(doctor.getPasswd());
            user.setName(doctor.getName());
            user.setCreateTime(new Date());
            user.setActCode(1);//默认邮箱认证通过
            basicService.insertOne(user);
            map.put("new", "1");
            doctor.setUserId(user.getId());

        }
        doctor.setId(UUID.randomUUID().toString());
        doctor.setAccount(user.getEmail());
        doctor.setCreateTime(new Date());
        doctor.setIsLeave(0);
        basicService.insertOne(doctor);
        //下发邮件
        map.put("email", doctor.getAccount());
        map.put("passwd", doctor.getPasswd());
        map.put("orgName", ((TbOrgManagerVO)session.getAttribute("user")).getOrgName());
        map.put("deptName", deptName);
        map.put("position", doctor.getPosition());
        map.put("name", doctor.getName());
        map.put(SysUtil.BASE_PATH, (String) session.getAttribute(SysUtil.BASE_PATH));
        // 通过指定模板名获取FreeMarker模板实例
        Template template = freeMarkerConfig.getConfiguration().getTemplate("org/addDoctorEmail.html");
        // 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        mailService.sendHtmlMail(doctor.getAccount(), map.get("orgName")+"人员添加通知", htmlText);

    }

    @Override
    public void updateDoctor(TbDoctorPO doctorPO, TbBaseUserPO userPO, TbOrgManagerVO org, String deptName) throws IOException, TemplateException {
        basicService.updateOne(doctorPO,true);
        basicService.updateOne(userPO,true);
        //下发邮件
        Map<String, String> map = new HashedMap();
        map.put("email", doctorPO.getAccount());
        map.put("passwd", doctorPO.getPasswd());
        map.put("orgName", ((TbOrgManagerVO)session.getAttribute("user")).getOrgName());
        map.put("deptName", deptName);
        map.put("position", doctorPO.getPosition());
        map.put("name", doctorPO.getName());
        map.put("isLeave", doctorPO.getIsLeave().equals(0)?"在职":"离职");
        map.put(SysUtil.BASE_PATH, (String) session.getAttribute(SysUtil.BASE_PATH));
        // 通过指定模板名获取FreeMarker模板实例
        Template template = freeMarkerConfig.getConfiguration().getTemplate("org/updateDoctorEmail.html");
        // 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        mailService.sendHtmlMail(doctorPO.getAccount(), map.get("orgName")+"人员修改通知", htmlText);
    }

    @Override
    public Object getAuthorityList(int isAllow, String key, String keyType, Integer pageNum, Integer pageSize,String orgId) throws Exception {
        BasicExample example =new BasicExample();
        BasicExample.Criteria criteria=example.createCriteria();
        criteria.andVarEqualTo("case_org_id",orgId);
        example.setOrderByClause("create_time");
        example.setCol("a.*,c.diagnosis,c.create_time as case_time,c.from_dept_name,c.from_org_name, u.name as user_name,d.`name` as doc_name,de.`name` as dept_name,o.`name` as org_name " );
        example.setTName(" tb_data_authority a " +
                "LEFT JOIN tb_doctor d ON a.doctor_id=d.id " +
                "LEFT JOIN tb_department de ON a.apply_dept_id=de.id " +
                "LEFT JOIN tb_base_user u ON a.user_id=u.id_card " +
                "LEFT JOIN tb_case c on c.id=a.case_id " +
                "LEFT JOIN tb_medical_org o on a.apply_org_id=o.id ");
        if(!StringUtils.isEmpty(key)){
            if(keyType.equals("name")){
                criteria.andVarLike("u.name","%"+key+"%");
            }else if(keyType.equals("diagnosis")){
                criteria.andVarLike("c.diagnosis","%"+key+"%");
            }else if(keyType.equals("org")){
                criteria.andVarLike("o.name","%"+key+"%");
            }
        }
        if(isAllow==-1){
            criteria.andVarEqualTo("is_allow","-1");
        }else if(isAllow>-1){
            criteria.andVarGreaterThan("is_allow","-1");
        }

        Page page= PageHelper.startPage(pageNum,pageSize);
        basicService.selectJoint(example);
        return  new PageInfo(page.getResult());
    }

    @Override
    public Object getAuthorityDetail(String authId, String orgId) throws Exception {
        BasicExample example =new BasicExample();
        BasicExample.Criteria criteria=example.createCriteria();
        criteria.andVarEqualTo("case_org_id",orgId);
        example.setCol("a.*," +
                " c.diagnosis,c.create_time as case_time," +
                " u.name as user_name," +
                " d.`name` as doc_name,d.account as doc_email,d.position,du.telephone as doc_phone ," +
                " de.`name` as dept_name," +
                " o.`name` as org_name,o.linkman,o.telephone as org_phone " );
        example.setTName(" tb_data_authority a " +
                "LEFT JOIN tb_doctor d ON a.doctor_id=d.id " +
                "LEFT JOIN tb_base_user du ON d.user_id=du.id " +
                "LEFT JOIN tb_department de ON a.apply_dept_id=de.id " +
                "LEFT JOIN tb_base_user u ON a.user_id=u.id_card " +
                "LEFT JOIN tb_case c on c.id=a.case_id " +
                "LEFT JOIN tb_medical_org o on a.apply_org_id=o.id ");
        criteria.andVarEqualTo("a.id",authId);
        return  basicService.selectJoint(example);
    }

    @Override
    public void sendAuthApproveMsg(TbDataAuthorityPO auth) throws Exception {
        try {
            TbDoctorPO doc= (TbDoctorPO) basicService.selectByPrimaryKey(TbDoctorPO.class,auth.getDoctorId());
            TbCasePO casePO= (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,auth.getCaseId());
            TbMedicalOrgPO org= (TbMedicalOrgPO) basicService.selectByPrimaryKey(TbMedicalOrgPO.class,casePO.getOrgId());
            TbBaseUserPO userPO=userService.getUserByPatientId(casePO.getPatientId());
            //下发邮件
            Map<String, String> map = new HashedMap();
            map.put("isAllow",auth.getIsAllow().toString());
            map.put("case",casePO.getDiagnosis()+"-"+userPO.getName());
            map.put("isPass", auth.getIsAllow().equals(1)?"通过":"不通过");
            map.put("orgName", org.getName());
            map.put("name", doc.getName());
            map.put(SysUtil.BASE_PATH, (String) session.getAttribute(SysUtil.BASE_PATH));
            // 通过指定模板名获取FreeMarker模板实例
            Template template = freeMarkerConfig.getConfiguration().getTemplate("org/approveCaseAuthEmail.html");
            // 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
            String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            mailService.sendHtmlMail(doc.getAccount(), "病历【"+casePO.getDiagnosis()+"-"+userPO.getName()+"】权限审核["+map.get("isPass")+"]通知", htmlText);

        }catch (Exception e){
            return;
        }
    }
}
