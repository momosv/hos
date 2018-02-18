package cn.momosv.hos.service.impl;

import cn.momosv.hos.dao.TbCasePOMapper;
import cn.momosv.hos.dao.TbDoctorPOMapper;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbCasePO;
import cn.momosv.hos.model.TbOrgPatientPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.DoctorService;
import cn.momosv.hos.service.UserService;
import cn.momosv.hos.util.XDateUtils;
import cn.momosv.hos.vo.TbDoctorVO;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TbDoctorPOMapper doctorMapper;

    @Autowired
    private TbCasePOMapper caseMapper;

    @Override
    public void addCase( TbBaseUserPO user, TbCasePO casePO, Integer isAgent, TbDoctorVO tbDoctorVO , String id ) throws Exception {
        //查询是否有treat_code
        TbOrgPatientPO  patientPO =   userService.getPatientByIdCard(user.getIdCard(),tbDoctorVO.getOrgId());
        if(patientPO == null){//新增机构患者
            patientPO.setId(UUID.randomUUID().toString());
            patientPO.setCreateTime(new Date());
            patientPO.setOrgId(tbDoctorVO.getOrgId());
            patientPO.setUserId(user.getIdCard());
            patientPO.setIsAgent(isAgent);
            patientPO.setTreatCode(XDateUtils.dateToString(new Date(),"yyyymmddhhMMssSSS"));
            boolean isExsit=true;
            while(isExsit){
                TbOrgPatientPO  oldPatientPO = userService.getPatientByTreatCode(patientPO.getTreatCode(),tbDoctorVO.getOrgId());
                if(null==oldPatientPO){
                    isExsit=false;
                }else {
                    patientPO.setTreatCode(XDateUtils.dateToString(new Date(),"yyyymmddhhMMssSSS"));
                }
            }
            basicService.insertOne(patientPO);
        }
        //获取baseUser
        TbBaseUserPO oldUser= userService.getUserByIdCard(user.getIdCard());
        Map<String, String> map = new HashedMap();
        if(null != oldUser){
            map.put("new", "0");
        }else {
            user.setId(UUID.randomUUID().toString());
            user.setAccount(user.getEmail());
            user.setPasswd(user.getEmail());
            user.setCreateTime(new Date());
            user.setActCode(1);//默认邮箱认证通过
            basicService.insertOne(user);
            map.put("new", "1");
        }
        casePO.setPatientId(patientPO.getId());
        casePO.setDeptId(tbDoctorVO.getDeptId());
        casePO.setCreateTime(new Date());
        casePO.setIsArchived(0);//归档false
        basicService.insertOne(casePO);
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
    public Msg getCaseList(String idCard, String treatCode, TbDoctorVO tbDoctorVO) throws Exception {
        //获取baseUser
        List<TbOrgPatientPO> patients=new ArrayList<>();
        if(idCard!=null) {
            patients = userService.getPatientListByIdCard(idCard,tbDoctorVO.getOrgId());
            if(patients.size()<=0){
                return Msg.success().add("list",null);
            }
        }else if(treatCode!=null){
            patients = userService.getPatientListByTreatCode(treatCode,tbDoctorVO.getOrgId());
            if(patients.size()<=0){
                return Msg.success().add("list",null);
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
        }
        return Msg.success().add("list",doctorService.selectCaseList(caseExample));
    }

    @Override
    public Object selectCaseList(BasicExample caseExample) {
        return caseMapper.selectCaseList(caseExample);
    }

    private TbOrgPatientPO getTbOrgPatient(TbBaseUserPO user) throws Exception {
        BasicExample tExample = new BasicExample(TbOrgPatientPO.class);
        tExample.createCriteria().andVarEqualTo("user_id",user.getIdCard());
        return (TbOrgPatientPO) basicService.selectOneByExample(tExample);
    }
}
