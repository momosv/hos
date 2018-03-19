package cn.momosv.hos.service;

import cn.momosv.hos.model.*;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.vo.TbDoctorVO;

import java.util.List;

public interface DoctorService {

    void addCase(TbBaseUserPO user, TbCasePO casePO,  Integer isAgent,TbDoctorVO tbDoctorVO , String id) throws Exception;

    TbOrgPatientPO newPatient(String idCard, Integer isAgent, TbDoctorVO tbDoctorVO) throws Exception;

    Msg getPatient(String idCard, String treatCode, TbDoctorVO tbDoctorVO) throws Exception;

    Msg getCaseList(String idCard, String treatCode, TbDoctorVO tbDoctorVO, int pageNum, int pageSize) throws Exception;

    Object selectCaseList(BasicExample caseExample);

    void deleteCase(String[] ids) throws InstantiationException, IllegalAccessException;

    void getHosList(String caseId);

    Object getSecondList(String caseId);

    boolean checkAuth(TbDoctorVO doctorVO, String caseId) throws IllegalAccessException, InstantiationException, Exception;

    boolean checkApplyAuth(TbDoctorVO doctorVO, String caseId,Integer allowGrade) throws Exception;

    void updateMy(TbDoctorPO doctorPO, TbBaseUserPO user);

    Object getCaseApplyList(String key, String keyType,String isAllow, TbDoctorVO tbDoctorVO, int pageNum, int pageSize);

    void sendAuthMail(TbDoctorVO doctorVO, TbDataAuthorityPO authorityPO, TbCasePO casePO) throws Exception;

    List<Object> getUserCaseList(TbDoctorVO doctorVO, List<String> pList, String diagnosis);

}
