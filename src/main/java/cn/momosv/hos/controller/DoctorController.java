package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.exception.MyException;
import cn.momosv.hos.model.*;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.DoctorService;
import cn.momosv.hos.service.UserService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("doc")
public class DoctorController extends BasicController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    UserService userService;

    /**
     * 初诊
     * @param user
     * @param casePO
     * @param isAgent
     * @return
     * @throws Exception
     */
    @RequestMapping("addCase")
    public Object addCase(TbBaseUserPO user, TbCasePO casePO,Date caseDate,Integer isAgent/*是否代理*/,String patientId) throws Exception {
        TbDoctorVO doc = validDoctor();
        casePO.setDoctorId(doc.getId());
        casePO.setOrgId(doc.getOrgId());
        casePO.setFromDeptName(doc.getDeptName());
        casePO.setFromOrgName(doc.getOrgName());
        doctorService.addCase(user,casePO,isAgent,validDoctor(),patientId);
        return successMsg("保存成功");
    }

    @RequestMapping("getCasePatient")
    public Object casePatient(String idCard,String treatCode) throws Exception {
        if(StringUtils.isEmpty(treatCode)&&StringUtils.isEmpty(idCard)){
            return failMsg("门诊卡号或者身份证号不能为空");
        }
        TbDoctorVO doc = validDoctor();
        BasicExample example = new BasicExample(TbOrgPatientPO.class);
        BasicExample.Criteria criteria=example.createCriteria();
        criteria.andVarEqualTo("org_id",doc.getOrgId());
        if(!StringUtils.isEmpty(idCard)){
            criteria.andVarEqualTo("user_id",idCard);
        }else if(!StringUtils.isEmpty(treatCode)){
            criteria.andVarEqualTo("treat_code",treatCode);
        }
        TbOrgPatientPO patientPO = (TbOrgPatientPO) basicService.selectOneByExample(example);
        if(patientPO == null&&!StringUtils.isEmpty(idCard)){//新增机构患者
            patientPO= doctorService.newPatient(idCard, 1, doc);
        }
        TbBaseUserPO userPO = null;
        if(null!=patientPO){
            userPO = userService.getUserByIdCard(patientPO.getUserId());
            if(userPO!=null)userPO.setPasswd(null);
        }
        return successMsg("保存成功").add("patient",patientPO).add("user",userPO);
    }
    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("getCase")
    public Object getCase(String id) throws Exception {
       TbCasePO casePO = (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,id);
        Msg msg=new Msg();
       if(null!=casePO){
            TbOrgPatientPO patientPO= (TbOrgPatientPO) basicService.selectByPrimaryKey(TbOrgPatientPO.class,casePO.getPatientId());
            msg= (Msg) casePatient(patientPO.getUserId(),null);
       }
       msg.setMsg("获取成功").add("case",casePO);
        return msg;
    }

    @RequestMapping("applyAgainDetail")
    public Object applyAgainDetail(String id) throws Exception {
        TbDataAuthorityPO authorityPO= (TbDataAuthorityPO) basicService.selectByPrimaryKey(TbDataAuthorityPO.class,id);
        if(authorityPO==null){
            return failMsg("获取权限数据从失败,数据不存在");
        }
        return  this.getCase(authorityPO.getCaseId());
    }

    @RequestMapping("applyCase")
    public Object applyCase(TbDataAuthorityPO authorityPO) throws Exception {
      TbDoctorVO doctorVO= validDoctor();
        TbCasePO casePO=null;
      if(!StringUtils.isEmpty(authorityPO.getId())){//更新权限日期
         TbDataAuthorityPO old= (TbDataAuthorityPO) basicService.selectByPrimaryKey(TbDataAuthorityPO.class,authorityPO.getId());
            if(old==null){
                 return failMsg("获取权限数据从失败,数据不存在");
            }else {
                casePO = (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,old.getCaseId());
                  if(casePO==null){
                      throw new MyException("病历数据不存在或者已经被删除，申请失败");
                  }
                  authorityPO.setIsAllow(-1);
            }
            basicService.updateOne(authorityPO,true);
      }else{
           casePO= (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,authorityPO.getCaseId());
           if(casePO==null){
                throw new MyException("病历数据不存在或者已经被删除，申请失败");
           }
           if(doctorService.checkApplyAuth(doctorVO,casePO.getId(),authorityPO.getAllowGrade())){
              throw new MyException("已经存在同级别的申请，申请失败");
          }
            TbBaseUserPO userPO = userService.getUserByPatientId(casePO.getPatientId());
            authorityPO.setId(UUID36());
            authorityPO.setCreateTime(new Date());
            authorityPO.setDoctorId(doctorVO.getId());
            authorityPO.setApplyDeptId(doctorVO.getDeptId());
            authorityPO.setApplyOrgId(doctorVO.getOrgId());
            authorityPO.setCaseOrgId(casePO.getOrgId());
            authorityPO.setIsAllow(-1);
            if(userPO!=null)
            authorityPO.setUserId(userPO.getIdCard());
            basicService.insertOne(authorityPO);
      }
     doctorService.sendAuthMail(doctorVO,authorityPO,casePO); //邮件通知
      return  successMsg("申请成功");
    }


    @RequestMapping("getUserCaseList")
    public Object getUserCaseList(
            String id,
            String diagnosis,
            @RequestParam(name="pageNum",defaultValue = "1") int pageNum,
            @RequestParam(name="pageSize",defaultValue = "10")int pageSize) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbBaseUserPO userPO = (TbBaseUserPO) userService.getUserByIdCard(id);
        if(userPO==null){
            return failMsg("患者信息不存在");
        }
        userPO.setPasswd(null);
        List<String> pList= userService.getPatientIdListByIdCard(id);
        Page page = PageHelper.startPage(pageNum, pageSize);
        if(pList.size()==0){
            return Msg.success().add("page",new PageInfo(page.getResult()));
        }
        doctorService.getUserCaseList(doctorVO,pList,diagnosis);
        return Msg.success().add("page",new PageInfo(page.getResult())).add("user",userPO);
    }
    /**
     * 更新
     * @param casePO
     * @return
     * @throws Exception
     */
    @RequestMapping("updateCase")
    public Object updateCase(TbCasePO casePO) throws Exception {
        validDoctor();
        TbCasePO old = validCase(casePO.getId());
        if(null == old){
           return failMsg("病例不存在或者已经被删除");
        }
        if(!old.getDoctorId().equals(validDoctor().getId())){
           return failMsg("你没有权限更新本病例");
        }
        casePO.setUpdateTime(new Date());
        basicService.updateOne(casePO,true);
        return successMsg("保存成功");
    }

    /**
     * 删除
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteCase")
    public Object deleteCase(String ids[]) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        List<TbCasePO> olds=basicService.selectByPrimaryKey(TbCasePO.class,ids);
        if(0 == olds.size()){
            return failMsg("病例不存在或者已经被删除");
        }
        for (TbCasePO old : olds) {
            if(!old.getDoctorId().equals(doctorVO.getId())){
               return failMsg("你没有权限删除本病例");
            }
        }
        doctorService.deleteCase(ids);
        return successMsg("删除成功");
    }

    /**
     * 住院
     * @param hospitalized
     * @return
     * @throws Exception
     */
    @RequestMapping("addHospitalized")
    public Object addHospitalized(TbHospitalizedPO hospitalized,String bedNum) throws Exception {
        validCase(hospitalized.getCaseId());
        hospitalized.setId(UUID36());
        if(hospitalized.getCreateTime()==null)hospitalized.setCreateTime(new Date());
        basicService.insertOne(hospitalized);
        TbCasePO casePO=new TbCasePO();
        casePO.setId(hospitalized.getCaseId());
        casePO.setBedNum(bedNum);
        basicService.updateOne(casePO,true);
        return successMsg("保存成功");
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("getHospitalized")
    public Object getHospitalized(String id) throws Exception {
        return successMsg("获取成功").add("detail",basicService.selectByPrimaryKey(TbHospitalizedPO.class,id));
    }
    /**
     * 更新住院
     * @param hospitalized
     * @return
     * @throws Exception
     */
    @RequestMapping("updateHospitalized")
    public Object updateHospitalized(TbHospitalizedPO hospitalized) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbHospitalizedPO old= (TbHospitalizedPO) basicService.selectByPrimaryKey(TbHospitalizedPO.class,hospitalized.getId());
        if(null== old){
            return failMsg("住院记录不存在或者已经被删除");
        }
        hospitalized.setUpdateTime(new Date());
        basicService.updateOne(hospitalized,true);
        return successMsg("保存成功");
    }
    /**
     * 删除住院
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteHospitalized")
    public Object deleteHospitalized(String id) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbHospitalizedPO old= (TbHospitalizedPO) basicService.selectByPrimaryKey(TbHospitalizedPO.class,id);
        if(null== old){
            return failMsg("住院记录不存在或者已经被删除");
        }
        TbCasePO casePO= (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,old.getCaseId());
        if(casePO==null||!casePO.getDoctorId().equals(doctorVO.getId())){
            return failMsg("你无权限删除住院记录");
        }
        basicService.deleteByPrimaryKey(TbHospitalizedPO.class,id);
        return successMsg("删除成功");
    }

    /**
     * 复诊
     * @param visitPO
     * @return
     * @throws Exception
     */
    @RequestMapping("addReturnVisit")
    public Object addReturnVisit(TbReturnVisitPO visitPO) throws Exception {
        validCase(visitPO.getCaseId());
        visitPO.setId(UUID36());
        visitPO.setCreateTime(new Date());
        basicService.insertOne(visitPO);
        return successMsg("保存成功");
    }
    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("getReturnVisit")
    public Object getReturnVisit(String id) throws Exception {
        TbReturnVisitPO returnVisitPO= (TbReturnVisitPO) basicService.selectByPrimaryKey(TbReturnVisitPO.class,id);
        TbCasePO casePO= (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,returnVisitPO.getCaseId());
        try{
            TbOrgManagerVO orgVO = validOrgManager();
            if(!orgVO.getOrgId().equals(casePO.getOrgId())){
                return failMsg("您无权限查看该病历数据");
            }
        }catch (Exception e){
            try {
                TbDoctorVO doctorVO = validDoctor();
                if(!doctorVO.getId().equals(casePO.getDoctorId())&&!doctorVO.getDeptId().equals(casePO.getDeptId())&&!doctorService.checkAuth(doctorVO,casePO.getId())){
                    return failMsg("您无权限查看该病历数据");
                }
            }catch (Exception ed){
                TbBaseUserPO userPO0 = userService.getUserByPatientId(casePO.getPatientId());
                TbBaseUserPO user = validUser();
                if(!userPO0.getIdCard().equals(user.getIdCard())){
                    return failMsg("您无权限查看该病历数据");
                }
            }

        }
        return successMsg("获取成功").add("detail",returnVisitPO);
    }
    /**
     * 更新
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("updateReturnVisit")
    public Object updateReturnVisit(TbReturnVisitPO visitPO) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbReturnVisitPO old= (TbReturnVisitPO) basicService.selectByPrimaryKey(TbReturnVisitPO.class,visitPO.getId());
        if(null== old){
            return failMsg("复诊不存在或者已经被删除");
        }
        visitPO.setUpdateTime(new Date());
        basicService.updateOne(visitPO,true);
        return successMsg("保存成功");
    }

    /**
     * 删除复诊
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteReturnVisit")
    public Object deleteReturnVisit(String id) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbReturnVisitPO old= (TbReturnVisitPO) basicService.selectByPrimaryKey(TbReturnVisitPO.class,id);
        if(null== old){
            return failMsg("复诊记录不存在或者已经被删除");
        }
        TbCasePO casePO= (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,old.getCaseId());
        if(casePO==null||!casePO.getDoctorId().equals(doctorVO.getId())){
            return failMsg("你无权限删除复诊记录");
        }
        basicService.deleteByPrimaryKey(TbReturnVisitPO.class,id);
        return successMsg("删除成功");
    }
    /**
     * 分析计划
     * @param planPO
     * @return
     * @throws Exception
     */
    @RequestMapping("addAnalyze")
    public Object addAnalyzePlan(TbAnalyzePlanPO planPO) throws Exception {
        validCase(planPO.getCaseId());
        planPO.setId(UUID36());
       if(planPO.getCreateTime()==null)planPO.setCreateTime(new Date());
        basicService.insertOne(planPO);
        return successMsg("保存成功");
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("getAnalyze")
    public Object getAnalyzePlan(String id) throws Exception {
        return successMsg("获取成功").add("detail",basicService.selectByPrimaryKey(TbAnalyzePlanPO.class,id));
    }
    /**
     * 更新
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("updateAnalyze")
    public Object updateAnalyzePlan(TbAnalyzePlanPO planPO) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbAnalyzePlanPO old= (TbAnalyzePlanPO) basicService.selectByPrimaryKey(TbAnalyzePlanPO.class,planPO.getId());
        if(null== old){
            return failMsg("分析&诊疗计划不存在或者已经被删除");
        }
        basicService.updateOne(planPO,true);
        return successMsg("保存成功");
    }

    /**
     * 删除
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteAnalyze")
    public Object deleteAnalyzePlan(String id) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbAnalyzePlanPO old= (TbAnalyzePlanPO) basicService.selectByPrimaryKey(TbReturnVisitPO.class,id);
        if(null== old){
            return failMsg("复分析&诊疗计划不存在或者已经被删除");
        }
        TbCasePO casePO= (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,old.getCaseId());
        if(casePO==null||!casePO.getDoctorId().equals(doctorVO.getId())){
            return failMsg("你无权限删除分析&诊疗计划");
        }
        basicService.deleteByPrimaryKey(TbReturnVisitPO.class,id);
        return successMsg("删除成功");
    }
    /**
     * 出院
     * @param leaveHospital
     * @return
     * @throws Exception
     */
    @RequestMapping("addLeaveHospital")
    public Object addLeaveHospital(TbLeaveHospitalPO leaveHospital) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        validCase(leaveHospital.getCaseId());
        leaveHospital.setId(UUID36());
        leaveHospital.setCreateTime(new Date());
        basicService.insertOne(leaveHospital);
        return successMsg("保存成功");
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("getLeaveHospital")
    public Object getLeaveHospital(String id) throws Exception {
        return successMsg("获取成功").add("detail",basicService.selectByPrimaryKey(TbLeaveHospitalPO.class,id));
    }
    /**
     * 更新
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("updateLeaveHospital")
    public Object updateLeaveHospital(TbLeaveHospitalPO leaveHospital) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbLeaveHospitalPO old= (TbLeaveHospitalPO) basicService.selectByPrimaryKey(TbLeaveHospitalPO.class,leaveHospital.getId());
        if(null== old){
            return failMsg("出院小结不存在或者已经被删除");
        }
        basicService.updateOne(leaveHospital,true);
        return successMsg("保存成功");
    }

    /**
     * 删除
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteLeaveHospital")
    public Object deleteLeaveHospital(String id) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbLeaveHospitalPO old= (TbLeaveHospitalPO) basicService.selectByPrimaryKey(TbLeaveHospitalPO.class,id);
        if(null== old){
            return failMsg("出院小结不存在或者已经被删除");
        }
        TbCasePO casePO= (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,old.getCaseId());
        if(casePO==null||!casePO.getDoctorId().equals(doctorVO.getId())){
            return failMsg("你无权限删除出院小结");
        }
        basicService.deleteByPrimaryKey(TbReturnVisitPO.class,id);
        return successMsg("删除成功");
    }
    /**
     * 会诊
     * @param consultation
     * @return
     * @throws Exception
     */
    @RequestMapping("addConsultation")
    public Object addConsultation(TbConsultationPO consultation) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        validCase(consultation.getCaseId());
        consultation.setId(UUID36());
        if(consultation.getCreateTime()==null)consultation.setCreateTime(new Date());
        basicService.insertOne(consultation);
        return successMsg("保存成功");
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("getConsultation")
    public Object getConsultation(String id) throws Exception {
        return successMsg("获取成功").add("detail",basicService.selectByPrimaryKey(TbConsultationPO.class,id));
    }
    /**
     * 更新
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("updateConsultation")
    public Object updateConsultation(TbConsultationPO consultation) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbConsultationPO old= (TbConsultationPO) basicService.selectByPrimaryKey(TbConsultationPO.class,consultation.getId());
        if(null== old){
            return failMsg("会诊不存在或者已经被删除");
        }
        basicService.updateOne(consultation,true);
        return successMsg("保存成功");
    }

    /**
     * 删除
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteConsultation")
    public Object deleteConsultation(String id) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbLeaveHospitalPO old= (TbLeaveHospitalPO) basicService.selectByPrimaryKey(TbConsultationPO.class,id);
        if(null== old){
            return failMsg("会诊记录不存在或者已经被删除");
        }
        TbCasePO casePO= (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,old.getCaseId());
        if(casePO==null||!casePO.getDoctorId().equals(doctorVO.getId())){
            return failMsg("你无权限删除会诊记录");
        }
        basicService.deleteByPrimaryKey(TbReturnVisitPO.class,id);
        return successMsg("删除成功");
    }


    /**
     * 手术
     * @param surgery
     * @return
     * @throws Exception
     */
    @RequestMapping("addSurgery")
    public Object addSurgery(TbSurgeryPO surgery) throws Exception {
        validCase(surgery.getCaseId());
        surgery.setId(UUID36());
        if(surgery.getCreateTime()==null) {
            surgery.setCreateTime(new Date());
        }
        basicService.insertOne(surgery);
        return successMsg("保存成功");
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("getSurgery")
    public Object getSurgery(String id) throws Exception {
        return successMsg("获取成功").add("detail",basicService.selectByPrimaryKey(TbSurgeryPO.class,id));
    }
    /**
     * 更新
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("updateSurgery")
    public Object updateSurgery(TbSurgeryPO surgery) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbSurgeryPO old= (TbSurgeryPO) basicService.selectByPrimaryKey(TbSurgeryPO.class,surgery.getId());
        if(null== old){
            return failMsg("手术记录不存在或者已经被删除");
        }
        basicService.updateOne(surgery,true);
        return successMsg("保存成功");
    }
    /**
     * 转院
     * @param transfer
     * @return
     * @throws Exception
     */
    @RequestMapping("addTransfer")
    public Object addTransfer(TbTransferPO transfer) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        validCase(transfer.getCaseId());
        transfer.setId(UUID36());
        if(transfer.getCreateTime() == null) transfer.setCreateTime(new Date());
        basicService.insertOne(transfer);
        return successMsg("保存成功");
    }
    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("getTransfer")
    public Object getTransfer(String id) throws Exception {
        return successMsg("获取成功").add("detail",basicService.selectByPrimaryKey(TbTransferPO.class,id));
    }
    /**
     * 更新
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("updateTransfer")
    public Object updateTransfer(TbTransferPO transfer) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbTransferPO old= (TbTransferPO) basicService.selectByPrimaryKey(TbTransferPO.class,transfer.getId());
        if(null== old){
            return failMsg("会诊不存在或者已经被删除");
        }
        basicService.updateOne(transfer,true);
        return successMsg("保存成功");
    }

    /**
     * 删除
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteTransfer")
    public Object deleteTransfer(String id) throws Exception {
        TbDoctorVO doctorVO=validDoctor();
        TbTransferPO old= (TbTransferPO) basicService.selectByPrimaryKey(TbTransferPO.class,id);
        if(null== old){
            return failMsg("会诊记录不存在或者已经被删除");
        }
        TbCasePO casePO= (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,old.getCaseId());
        if(casePO==null||!casePO.getDoctorId().equals(doctorVO.getId())){
            return failMsg("你无权限删除会诊记录");
        }
        basicService.deleteByPrimaryKey(TbReturnVisitPO.class,id);
        return successMsg("删除成功");
    }

    /**
     * 初诊列表
     * @return
     * @throws Exception
     */
    @RequestMapping("getCaseList")
    public Object getCaseList(String key, String keyType,
                              @RequestParam(name="pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(name="pageSize",defaultValue = "10")int pageSize) throws Exception {
        return  doctorService.getCaseList(key,keyType,validDoctor(),pageNum, pageSize);
    }
    /**
     * 申请的初诊列表
     * @return
     * @throws Exception
     */
    @RequestMapping("getCaseApplyList")
    public Object getCaseApplyList(String key, String keyType,String isAllow,
                              @RequestParam(name="pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(name="pageSize",defaultValue = "10")int pageSize) throws Exception {
        if(StringUtils.isEmpty(key)){key=null;}
        else{
            key="%"+key+"%";
        }
        if(StringUtils.isEmpty(isAllow)||isAllow.equals("-1")){isAllow=null;}
        return  doctorService.getCaseApplyList(key,keyType,isAllow,validDoctor(),pageNum, pageSize);
    }

    @RequestMapping("getPatient")
    public Object getPatient(String idCard, String treatCode) throws Exception {
        if(idCard==null&&treatCode==null){
            return failMsg("身份证或者机构治疗编码不能为空");
        }
        return  doctorService.getPatient(idCard,treatCode, validDoctor());
    }

    @RequestMapping("getCaseSecondList")
    public Object getCaseSecondList(String caseId) throws Exception {
        TbCasePO casePO = validCase(caseId);
        try{
            TbOrgManagerVO orgVO = validOrgManager();
            if(!orgVO.getOrgId().equals(casePO.getOrgId())){
                return failMsg("您无权限查看该病历数据");
            }
        }catch (Exception e){
            try {
                TbDoctorVO doctorVO = validDoctor();
                if(!doctorVO.getId().equals(casePO.getDoctorId())&&!doctorVO.getDeptId().equals(casePO.getDeptId())&&!doctorService.checkAuth(doctorVO,casePO.getId())){
                    return failMsg("您无权限查看该病历数据");
                }
            }catch (Exception ed){
                TbBaseUserPO userPO0 = userService.getUserByPatientId(casePO.getPatientId());
               TbBaseUserPO userPO = validUser();
               if(!userPO0.getIdCard().equals(userPO.getIdCard())){
                   return failMsg("您无权限查看该病历数据");
               }
            }

        }
        //住院
        return doctorService.getSecondList(caseId);

    }

    @RequestMapping("getSecondReturn")
    public Msg getSecondReturn(String secondId,String type) throws Exception {
        BasicExample example=new BasicExample(TbReturnVisitPO.class);
        example.createCriteria().andVarEqualTo("secondId",secondId)
                .andVarEqualTo("type",type);
        example.setOrderByClause("create_time desc");
        return successMsg().add("list",basicService.selectByExample(example));

    }


    @RequestMapping("updateMy")
    public Msg updateMy(TbDoctorPO doctorPO,String telephone,int sex,String address) throws Exception {
        TbDoctorVO old = validDoctor();
        doctorPO.setId(old.getId());
        BasicExample example=new BasicExample(TbDoctorPO.class);
        example.createCriteria().andVarEqualTo("account",doctorPO.getAccount()).andVarNotEqualTo("id",old.getId()).andVarEqualTo("org_id",old.getOrgId());
        List list= basicService.selectByExample(example);
        if(list.size()>0){
            return failMsg("账号/邮箱已经存在该机构");
        }
        doctorPO.setUpdateTime(new Date());
        TbBaseUserPO user=new TbBaseUserPO();
        user.setId(old.getUserId());
        user.setSex(sex);
        user.setAddress(address);
        user.setTelephone(telephone);
        doctorService.updateMy(doctorPO,user);
        return successMsg("更改成功，请退出重新登录生效");
    }


    private TbCasePO validCase(String caseId) throws Exception {
        TbCasePO casePO;
        if(StringUtils.isEmpty(caseId)){
            throw new Exception("所属初诊病历不能为空");
        }else{
            casePO= (TbCasePO) basicService.selectByPrimaryKey(TbCasePO.class,caseId);
            if(casePO==null){
                throw new Exception("所属初诊病历不存在");
            }
        }
        return casePO;
    }

    private TbDoctorVO validDoctor() throws Exception {
        try{
            return (TbDoctorVO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录医生身份账号后再操作");
        }
    }

    private TbBaseUserPO validUser() throws Exception {
        try{
            return (TbBaseUserPO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录个人身份账号后再操作");
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
