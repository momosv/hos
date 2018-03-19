package cn.momosv.hos.service.impl;

import cn.momosv.hos.dao.TbCasePOMapper;
import cn.momosv.hos.dao.TbDoctorPOMapper;
import cn.momosv.hos.email.MailService;
import cn.momosv.hos.model.*;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.DoctorService;
import cn.momosv.hos.service.OrgService;
import cn.momosv.hos.service.UserService;
import cn.momosv.hos.util.SysUtil;
import cn.momosv.hos.util.XDateUtils;
import cn.momosv.hos.vo.TbDoctorVO;
import cn.momosv.hos.vo.TbHospitalizedVO;
import cn.momosv.hos.vo.TbSurgeryVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import freemarker.template.Template;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private BasicService basicService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private OrgService orgService;

    @Autowired
    private TbDoctorPOMapper doctorMapper;

    @Autowired
    private TbCasePOMapper caseMapper;

    @Autowired
    HttpSession session;


    @Autowired
    FreeMarkerConfig freeMarkerConfig;

    @Autowired
    MailService mailService;

    @Override
    public void addCase( TbBaseUserPO user, TbCasePO casePO, Integer isAgent, TbDoctorVO tbDoctorVO , String id ) throws Exception {
        //查询是否有treat_code
        TbOrgPatientPO  patientPO =   userService.getPatientByIdCard(user.getIdCard(),tbDoctorVO.getOrgId());
        if(patientPO == null){//新增机构患者
            patientPO= newPatient(user.getIdCard(), isAgent, tbDoctorVO);
        }
        //获取baseUser
        TbBaseUserPO oldUser= userService.getUserByIdCard(user.getIdCard());
        if(null != oldUser){
            user.setId(oldUser.getId());
            basicService.updateOne(user,true);
        }else {
            user.setId(UUID.randomUUID().toString());
            user.setAccount(user.getEmail());
            user.setPasswd(user.getEmail());
            user.setCreateTime(new Date());
            user.setActCode(1);//默认邮箱认证通过
            basicService.insertOne(user);

        }
        casePO.setId(UUID.randomUUID().toString());
        casePO.setPatientId(patientPO.getId());
        casePO.setDeptId(tbDoctorVO.getDeptId());
        casePO.setCreateTime(new Date());
        casePO.setIsArchived(0);//归档false
        basicService.insertOne(casePO);
    }

    @Override
    public TbOrgPatientPO newPatient(String idCard, Integer isAgent, TbDoctorVO tbDoctorVO) throws Exception {
        TbOrgPatientPO patientPO=new TbOrgPatientPO();
        patientPO.setId(UUID.randomUUID().toString());
        patientPO.setCreateTime(new Date());
        patientPO.setOrgId(tbDoctorVO.getOrgId());
        patientPO.setUserId(idCard);
        patientPO.setIsAgent(isAgent);
        String tc=XDateUtils.dateToString(new Date(),"yyyyMMddHHmmssSSS");
        tc=tc.substring(3);
        patientPO.setTreatCode(tc);
        boolean isExsit=true;
        while(isExsit){
            TbOrgPatientPO  oldPatientPO = userService.getPatientByTreatCode(patientPO.getTreatCode(),tbDoctorVO.getOrgId());
            if(null==oldPatientPO){
                isExsit=false;
            }else {
                tc=XDateUtils.dateToString(new Date(),"yyyyMMddHHmmssSSS");
                tc=tc.substring(3);
                patientPO.setTreatCode(tc);
            }
        }
        basicService.insertOne(patientPO);
        return patientPO;
    }

    @Override
    public Msg getPatient(String idCard, String treatCode, TbDoctorVO tbDoctorVO) throws Exception {
        //获取baseUser
        if(idCard!=null) {
            TbBaseUserPO oldUser = userService.getUserByIdCard(idCard);
            TbOrgPatientPO patient = userService.getPatientByIdCard(oldUser.getIdCard(),tbDoctorVO.getOrgId());
            return Msg.success().add("user",oldUser).add("patient",patient);
        }
        if(treatCode!=null){
            TbOrgPatientPO patient = userService.getPatientByTreatCode(treatCode,tbDoctorVO.getOrgId());
            TbBaseUserPO oldUser = userService.getUserByIdCard(patient.getUserId());
            return Msg.success().add("user",oldUser).add("patient",patient);
        }
        return null;
    }

    @Override
    public Msg getCaseList(String key, String keyType, TbDoctorVO tbDoctorVO, int pageNum, int pageSize) throws Exception {
        //获取baseUser
        List<TbOrgPatientPO> patients=new ArrayList<>();
        if(!StringUtils.isEmpty(key)) {
            if(keyType.equals("name")){
                patients =  userService.getPatientListByName(key,tbDoctorVO.getOrgId());
            }else if(keyType.equals("idCard")){
                patients = userService.getPatientListByIdCard(key,tbDoctorVO.getOrgId());
            }else{
                patients = userService.getPatientListByTreatCode(key,tbDoctorVO.getOrgId());
            }
        }
        List<String> patientIds=new ArrayList<>();
        for (TbOrgPatientPO patient : patients) {
            patientIds.add(patient.getId());
        }
        BasicExample caseExample=new BasicExample(TbCasePO.class);
        BasicExample.Criteria criteria= caseExample.createCriteria();
        criteria.andVarEqualTo("doctor_id",tbDoctorVO.getId());
        if(patientIds.size()>0){
            criteria.andVarIn("patient_id",patientIds);
        }else if(!StringUtils.isEmpty(key)){
            criteria.andVarEqualTo("patient_id","-1");
        }
        caseExample.setOrderByClause("create_time desc");
        Page page = PageHelper.startPage(pageNum, pageSize);
        doctorService.selectCaseList(caseExample);
        return Msg.success().add("page",new PageInfo<>(page.getResult()));
    }

    @Override
    public Object selectCaseList(BasicExample caseExample) {
        return caseMapper.selectCaseList(caseExample);
    }

    @Override
    public void deleteCase(String[] ids) throws InstantiationException, IllegalAccessException {
        BasicExample example=new BasicExample();
        example.createCriteria().andVarIn("case_id",Arrays.asList(ids));
        //住院
        example.setClazz(TbHospitalizedPO.class);
        basicService.deleteByExample(example);
        //复诊、查房、术后小计
        example.setClazz(TbReturnVisitPO.class);
        basicService.deleteByExample(example);
        //分析计划
        example.setClazz(TbAnalyzePlanPO.class);
        basicService.deleteByExample(example);
        //出院
        example.setClazz(TbLeaveHospitalPO.class);
        basicService.deleteByExample(example);
        //会诊
        example.setClazz(TbConsultationPO.class);
        basicService.deleteByExample(example);
        //手术
        example.setClazz(TbSurgeryPO.class);
        basicService.deleteByExample(example);
        //转院
        example.setClazz(TbTransferPO.class);
        basicService.deleteByExample(example);
        //case
        basicService.deleteByPrimaryKey(TbCasePO.class,ids);
    }

    @Override
    public void getHosList(String caseId) {

        doctorMapper.getHosList(caseId);
    }

    @Override
    public Object getSecondList(String caseId) {
        //住院
        //复诊、查房、术后小计
        //分析计划
        //出院
        //会诊
        //手术
        //转院
        List<TbHospitalizedVO> hosList= doctorMapper.getHosList(caseId);
        List<TbAnalyzePlanPO> anaList=   doctorMapper.getAnalyzeList(caseId);
        List<TbLeaveHospitalPO> leaList=   doctorMapper.getLeaveHosList(caseId);
        List<TbReturnVisitPO> returnList=  doctorMapper.getReturnList(caseId);
        List<TbSurgeryVO> surList=  doctorMapper.getSurgeryList(caseId);
        List<TbTransferPO> traList=  doctorMapper.getTransferList(caseId);
        List<TbConsultationPO> conList=  doctorMapper.getConsultationList(caseId);
        for (TbHospitalizedVO vo : hosList) {
            List list=doctorMapper.getReturnSecondList(caseId,vo.getId(),2);
            vo.setReturnList(list);
        }
        for (TbSurgeryVO vo : surList) {
            List list=doctorMapper.getReturnSecondList(caseId,vo.getId(),3);
            vo.setReturnList(list);
        }
        return Msg.success("success")
                .add("hosList",hosList)
                .add("anaList",anaList)
                .add("leaList",leaList)
                .add("returnList",returnList)
                .add("surList",surList)
                .add("traList",traList)
                .add("conList",conList);
    }

    @Override
    public boolean checkAuth(TbDoctorVO doctorVO, String caseId) throws Exception {
        BasicExample example =new BasicExample(TbDataAuthorityPO.class);
        BasicExample.Criteria criteria= example.createCriteria();
        criteria.andVarEqualTo("apply_org_id",doctorVO.getOrgId());
        criteria.andVarEqualTo("case_id",caseId);
        criteria.andVarEqualTo("allow_grade","2");
        criteria.andVarEqualTo("is_allow","1");
        criteria.andVarLessThanOrEqualTo("deadline",new Date().toString());
        example.or().andVarEqualTo("apply_dept_id",doctorVO.getDeptId())
                .andVarEqualTo("case_id",caseId)
                .andVarEqualTo("allow_grade","1")
                .andVarEqualTo("is_allow","1")
                .andVarLessThanOrEqualTo("deadline",new Date().toString());
        example.or().andVarEqualTo("doctor_id",doctorVO.getId())
                .andVarEqualTo("case_id",caseId)
                .andVarEqualTo("allow_grade","0")
                .andVarEqualTo("is_allow","1")
                .andVarLessThanOrEqualTo("deadline",new Date().toString());
        TbDataAuthorityPO auth = (TbDataAuthorityPO) basicService.selectOneByExample(example);
        if(auth==null||!auth.getIsAllow().equals(1)){
            return false;
        }
        return true;
    }
    @Override
    public boolean checkApplyAuth(TbDoctorVO doctorVO, String caseId,Integer allowGrade) throws Exception {
        BasicExample example =new BasicExample(TbDataAuthorityPO.class);
        BasicExample.Criteria criteria= example.createCriteria();
        if(allowGrade.equals(0)){
            criteria.andVarEqualTo("doctor_id",doctorVO.getId())
                    .andVarEqualTo("case_id",caseId)
                    .andVarEqualTo("allow_grade","0");
        }else if(allowGrade.equals(1)){
            criteria.andVarEqualTo("apply_dept_id",doctorVO.getDeptId())
                    .andVarEqualTo("case_id",caseId)
                    .andVarEqualTo("allow_grade","1");
        }else{
            criteria.andVarEqualTo("apply_org_id",doctorVO.getOrgId())
                    .andVarEqualTo("case_id",caseId)
                    .andVarEqualTo("allow_grade","2");
        }
        TbDataAuthorityPO auth = (TbDataAuthorityPO) basicService.selectOneByExample(example);
        if(auth==null){
            return false;
        }
        return true;
    }

    @Override
    public void updateMy(TbDoctorPO doctorPO, TbBaseUserPO user) {
        basicService.updateOne(doctorPO,true);
        if(!StringUtils.isEmpty(user.getId())){
            basicService.updateOne(user,true);
        }
    }

    @Override
    public Object getCaseApplyList(String key, String keyType,String isAllow, TbDoctorVO tbDoctorVO, int pageNum, int pageSize) {
        Page page =PageHelper.startPage(pageNum,pageSize);
         doctorMapper.getCaseApplyList(key,keyType,isAllow,tbDoctorVO,new Date());
        return Msg.success().add("page",new PageInfo<>(page.getResult()));
    }

    @Override
    public void sendAuthMail(TbDoctorVO doctorVO, TbDataAuthorityPO authorityPO, TbCasePO casePO) throws Exception {
       Map<String, Object> pMap=new HashedMap();
        pMap.put("doctor",doctorVO.getName());
        pMap.put("docOrg",doctorVO.getOrgName());
        pMap.put("docDept",doctorVO.getDeptName());
        pMap.put("case",casePO.getDiagnosis());
        pMap.put(SysUtil.BASE_PATH, (String) session.getAttribute(SysUtil.BASE_PATH));
        TbMedicalOrgPO medicalOrgPO= (TbMedicalOrgPO) basicService.selectByPrimaryKey(TbMedicalOrgPO.class,casePO.getOrgId());
        if(medicalOrgPO!=null){
            pMap.put("orgName",medicalOrgPO.getName());
        }
        pMap.put("deadline", XDateUtils.dateToString(authorityPO.getDeadline(),"yyyy-MM-dd"));
        if(authorityPO.getAllowGrade().equals(1)){
            pMap.put("grade","部门");
        }else if(authorityPO.getAllowGrade().equals(2)){
            pMap.put("grade","机构");
        }else{
            pMap.put("grade", "个人");
        }
        //通知user
        TbBaseUserPO user = userService.getUserByPatientId(casePO.getPatientId());
        if(user != null && !StringUtils.isEmpty(user.getEmail())){
            pMap.put("name",user.getName());
            mailService.sendHtmlMail(user.getEmail(), "病历权限申请通知", "doc/applyCaseAuthEmail.html",pMap);
        }
        //通知orgManager
       List<TbOrgManagerPO> list = orgService.getManagerList(casePO.getOrgId());
        if(list!=null){
            for (TbOrgManagerPO po : list) {
                pMap.put("name",po.getName());
                mailService.sendHtmlMail(po.getEmail(), "病历权限申请通知", "doc/applyCaseAuthEmail.html",pMap);
            }
        }
    }

    @Override
    public List<Object> getUserCaseList(TbDoctorVO doctorVO, List<String> pList, String diagnosis) {
        if(StringUtils.isEmpty(diagnosis))diagnosis=null;
        else diagnosis="%"+diagnosis+"%";
      return doctorMapper.getUserCaseList( doctorVO, pList,diagnosis);
    }

    private TbOrgPatientPO getTbOrgPatient(TbBaseUserPO user) throws Exception {
        BasicExample tExample = new BasicExample(TbOrgPatientPO.class);
        tExample.createCriteria().andVarEqualTo("user_id",user.getIdCard());
        return (TbOrgPatientPO) basicService.selectOneByExample(tExample);
    }
}
