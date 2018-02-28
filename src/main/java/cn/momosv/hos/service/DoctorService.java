package cn.momosv.hos.service;

import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbCasePO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.vo.TbDoctorVO;

public interface DoctorService {

    void addCase(TbBaseUserPO user, TbCasePO casePO,  Integer isAgent,TbDoctorVO tbDoctorVO , String id) throws Exception;

    Msg getPatient(String idCard, String treatCode, TbDoctorVO tbDoctorVO) throws Exception;

    Msg getCaseList(String idCard, String treatCode, TbDoctorVO tbDoctorVO) throws Exception;

    Object selectCaseList(BasicExample caseExample);

    void deleteCase(String[] ids) throws InstantiationException, IllegalAccessException;
}
