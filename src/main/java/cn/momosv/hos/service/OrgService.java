package cn.momosv.hos.service;

import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbDoctorPO;

import java.util.List;

public interface OrgService {
    boolean validDoctorIdCardExist(String orgId, String idCard);

    boolean validDoctorEMailExist(String orgId, String email);

    List<TbDoctorPO> getDoctorByIdCard(String orgId, String idCard);

    void insertDoctor(TbBaseUserPO user, TbDoctorPO doctor,String deptName) throws Exception;
}
