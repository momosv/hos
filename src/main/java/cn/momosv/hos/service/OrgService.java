package cn.momosv.hos.service;

import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbDoctorPO;
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
}
