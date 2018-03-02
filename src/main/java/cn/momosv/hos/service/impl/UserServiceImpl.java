package cn.momosv.hos.service.impl;

import cn.momosv.hos.dao.TbBaseUserPOMapper;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbOrgPatientPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {


    @Autowired
    private BasicService basicService;

    /**
     *
     */
    @Autowired
    private TbBaseUserPOMapper userPOMapper;

    @Override
    public TbBaseUserPO getUserByIdCard(String idCard) throws Exception {
        BasicExample userExample=new BasicExample(TbBaseUserPO.class);
        userExample.createCriteria().andVarEqualTo("idCard",idCard);
        return  (TbBaseUserPO) basicService.selectOneByExample(userExample);
    }
    @Override
    public TbOrgPatientPO getPatientByTreatCode(String code, String orgId) throws Exception {
        BasicExample userExample=new BasicExample(TbOrgPatientPO.class);
        userExample.createCriteria()
                .andVarEqualTo("treatCode",code)
                .andVarEqualTo("org_id",orgId);;
        return  (TbOrgPatientPO) basicService.selectOneByExample(userExample);
    }

    @Override
    public TbOrgPatientPO getPatientByIdCard(String idCard, String orgId) throws Exception {
        BasicExample userExample=new BasicExample(TbOrgPatientPO.class);
        userExample.createCriteria()
                .andVarEqualTo("user_id",idCard)
                .andVarEqualTo("org_id",orgId);
        return  (TbOrgPatientPO) basicService.selectOneByExample(userExample);
    }

    @Override
    public List<TbOrgPatientPO> getPatientListByTreatCode(String code, String orgId) throws Exception {
        BasicExample userExample=new BasicExample(TbOrgPatientPO.class);
        userExample.createCriteria()
                .andVarLike("treatCode","%"+code+"%")
                .andVarEqualTo("org_id",orgId);;
        return  (List<TbOrgPatientPO>) basicService.selectByExample(userExample);
    }

    @Override
    public List<TbOrgPatientPO> getPatientListByIdCard(String idCard, String orgId) throws Exception {
        BasicExample userExample=new BasicExample(TbOrgPatientPO.class);
        userExample.createCriteria()
                .andVarLike("user_id","%"+idCard+"%")
                .andVarEqualTo("org_id",orgId);
        return   basicService.selectByExample(userExample);
    }

    @Override
    public List<TbOrgPatientPO> getPatientListByIdCardList(List<String> idCards, String orgId) throws Exception {
        BasicExample userExample=new BasicExample(TbOrgPatientPO.class);
        userExample.createCriteria()
                .andVarIn("user_id",idCards)
                .andVarEqualTo("org_id",orgId);
        return   basicService.selectByExample(userExample);
    }

    @Override
    public List<TbOrgPatientPO> getPatientListByName(String userName, String orgId) throws Exception {
        List<TbBaseUserPO> u= getUserListByName(userName);
        if(!u.isEmpty()){
            List<String> list=new ArrayList<>(u.size());
            for (TbBaseUserPO userPO : u) {
                list.add(userPO.getIdCard());
            }
            return getPatientListByIdCardList(list,orgId);
        }
        return new ArrayList<>();
    }

    @Override
    public List<TbBaseUserPO> getUserListByName(String userName) throws Exception {
        BasicExample userExample=new BasicExample(TbBaseUserPO.class);
        userExample.createCriteria()
                .andVarLike("name",userName);
        return   basicService.selectByExample(userExample);
    }

    @Override
    public List<String> getPatientIdListByIdCard(String idCard) throws Exception {
        return userPOMapper.getPatientIdListByIdCard(idCard);
    }
}
