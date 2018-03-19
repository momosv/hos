package cn.momosv.hos.service;

import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbDataAuthorityPO;
import cn.momosv.hos.model.TbDoctorPO;
import cn.momosv.hos.model.TbOrgManagerPO;
import cn.momosv.hos.vo.TbOrgManagerVO;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;

public interface OrgService {
    boolean validDoctorIdCardExist(String orgId, String idCard);

    boolean validDoctorEMailExist(String orgId, String email);

    List<TbDoctorPO> getDoctorByIdCard(String orgId, String idCard);

    void insertDoctor(TbBaseUserPO user, TbDoctorPO doctor,String deptName) throws Exception;

    void updateDoctor(TbDoctorPO doctorPO, TbBaseUserPO userPO, TbOrgManagerVO org, String deptName) throws IOException, TemplateException;

    Object getAuthorityList(int isAllow, String key, String keyType, Integer pageNum, Integer pageSize,String orgId) throws Exception;

    Object getAuthorityDetail(String authId, String orgId) throws Exception;

    void sendAuthApproveMsg(TbDataAuthorityPO auth) throws Exception;

    List<TbOrgManagerPO> getManagerList(String orgId);
}
