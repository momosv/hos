package cn.momosv.hos.service;

import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbOrgPatientPO;

import java.util.List;

public interface UserService {

   TbBaseUserPO getUserByIdCard(String idCard) throws Exception;

    TbBaseUserPO getUserByPatientId(String id) throws Exception;

    TbOrgPatientPO getPatientByTreatCode(String code, String orgId) throws Exception;

    TbOrgPatientPO getPatientByIdCard(String idCard, String orgId) throws Exception;

    List<TbOrgPatientPO> getPatientListByTreatCode(String code, String orgId) throws Exception;

    List<TbOrgPatientPO> getPatientListByIdCard(String idCard, String orgId) throws Exception;

 List<TbOrgPatientPO> getPatientListByIdCardList(List<String> idCards, String orgId) throws Exception;

    List<TbOrgPatientPO> getPatientListByName(String userName, String orgId) throws Exception;

    List<TbBaseUserPO> getUserListByName(String userName) throws Exception;

    List<String> getPatientIdListByIdCard(String idCard) throws Exception;

    Object getAuthorityList(int isAllow, String key, String keyType, TbBaseUserPO user);

    Object getAuthorityDetail(String authId, String idCard) throws Exception;
}
