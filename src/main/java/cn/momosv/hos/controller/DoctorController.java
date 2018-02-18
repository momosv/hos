package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.dao.TbConsultationPOMapper;
import cn.momosv.hos.model.*;
import cn.momosv.hos.service.DoctorService;
import cn.momosv.hos.service.UserService;
import cn.momosv.hos.util.SysUtil;
import cn.momosv.hos.vo.TbDoctorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("doc")
public class DoctorController extends BasicController {

    @Autowired
    DoctorService doctorService;

    /**
     * 初诊
     * @param user
     * @param casePO
     * @param isAgent
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("addCase")
    public Object addCase(TbBaseUserPO user, TbCasePO casePO,Integer isAgent/*是否代理*/,String id) throws Exception {
        doctorService.addCase(user,casePO,isAgent,validDoctor(),id);
        return successMsg("保存成功");
    }

    /**
     * 住院
     * @param hospitalized
     * @return
     * @throws Exception
     */
    @RequestMapping("addHospitalized")
    public Object addHospitalized(TbHospitalizedPO hospitalized) throws Exception {

        Object x = validCase(hospitalized.getCaseId());
        if (x != null) return x;
        hospitalized.setId(UUID36());
        hospitalized.setCreateTime(new Date());
        basicService.insertOne(hospitalized);
        return successMsg("保存成功");
    }

    /**
     * 复诊
     * @param visitPO
     * @return
     * @throws Exception
     */
    @RequestMapping("addReturnVisit")
    public Object addReturnVisit(TbReturnVisitPO visitPO) throws Exception {
        Object x = validCase(visitPO.getCaseId());
        if (x != null) return x;
        visitPO.setId(UUID36());
        visitPO.setCreateTime(new Date());
        basicService.insertOne(visitPO);
        return successMsg("保存成功");
    }

    /**
     * 分析计划
     * @param planPO
     * @return
     * @throws Exception
     */
    @RequestMapping("addAnalyzePlan")
    public Object addAnalyzePlan(TbAnalyzePlanPO planPO) throws Exception {
        Object x = validCase(planPO.getCaseId());
        if (x != null) return x;
        planPO.setId(UUID36());
        planPO.setCreateTime(new Date());
        basicService.insertOne(planPO);
        return successMsg("保存成功");
    }

    /**
     * 出院
     * @param leaveHospital
     * @return
     * @throws Exception
     */
    @RequestMapping("addLeaveHospital")
    public Object addLeaveHospital(TbLeaveHospitalPO leaveHospital) throws Exception {
        Object x = validCase(leaveHospital.getCaseId());
        if (x != null) return x;
        leaveHospital.setId(UUID36());
        leaveHospital.setCreateTime(new Date());
        basicService.insertOne(leaveHospital);
        return successMsg("保存成功");
    }


    /**
     * 会诊
     * @param consultation
     * @return
     * @throws Exception
     */
    @RequestMapping("addConsultation")
    public Object addConsultation(TbConsultationPO consultation) throws Exception {
        Object x = validCase(consultation.getCaseId());
        if (x != null) return x;
        consultation.setId(UUID36());
        consultation.setCreateTime(new Date());
        basicService.insertOne(consultation);
        return successMsg("保存成功");
    }

    private Object validCase(String caseId) throws Exception {
        if(StringUtils.isEmpty(caseId)){
            return failMsg("所属初诊病历不能为空");
        }else{
            if(null==basicService.selectByPrimaryKey(TbCasePO.class,caseId)){
                return failMsg("所属初诊病历不存在");
            }
        }
        return null;
    }

    /**
     * 手术
     * @param surgery
     * @return
     * @throws Exception
     */
    @RequestMapping("addSurgery")
    public Object addSurgery(TbSurgeryPO surgery) throws Exception {
        Object x = validCase(surgery.getCaseId());
        if (x != null) return x;
        surgery.setId(UUID36());
        surgery.setCreateTime(new Date());
        basicService.insertOne(surgery);
        return successMsg("保存成功");
    }
    /**
     * 初诊列表
     * @return
     * @throws Exception
     */
    @RequestMapping("getCaseList")
    public Object getCaseList(String idCard,String treatCode) throws Exception {
      return  doctorService.getCaseList(idCard,treatCode,validDoctor());
    }
    @RequestMapping("getPatient")
    public Object getPatient(String idCard, String treatCode) throws Exception {
        if(idCard==null&&treatCode==null){
            return failMsg("身份证或者机构治疗编码不能为空");
        }
        return  doctorService.getPatient(idCard,treatCode, validDoctor());
    }

    private TbDoctorVO validDoctor() throws Exception {
        try{
            return (TbDoctorVO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录医生身份账号后再操作");
        }
    }
}
