package cn.momosv.hos.service.impl;

import cn.momosv.hos.dao.TbBaseUserPOMapper;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbOrgPatientPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public TbBaseUserPO getUserByPatientId(String id) throws Exception {
        TbOrgPatientPO p= (TbOrgPatientPO) basicService.selectByPrimaryKey(TbOrgPatientPO.class,id);
        return this.getUserByIdCard(p.getUserId());

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

    @Override
    public Object getAuthorityList(int isAllow, String key, String keyType, TbBaseUserPO user) {
        if(StringUtils.isEmpty(key)){key=null;}else {
            key="%"+key+"%";
        }
        return userPOMapper.getAuthorityList(isAllow,key,keyType,user);
    }

    @Override
    public Object getAuthorityDetail(String authId, String idCard) throws Exception {
        BasicExample example =new BasicExample();
        BasicExample.Criteria criteria=example.createCriteria();
        criteria.andVarEqualTo("a.user_id",idCard);
        example.setCol("a.*," +
                " c.diagnosis,c.create_time as case_time," +
                " u.name as user_name," +
                " d.`name` as doc_name,d.account as doc_email,d.position,du.telephone as doc_phone ," +
                " de.`name` as dept_name," +
                " o.`name` as org_name,o.linkman,o.telephone as org_phone " );
        example.setTName(" tb_data_authority a " +
                "LEFT JOIN tb_doctor d ON a.doctor_id=d.id " +
                "LEFT JOIN tb_base_user du ON d.user_id=du.id " +
                "LEFT JOIN tb_department de ON a.apply_dept_id=de.id " +
                "LEFT JOIN tb_base_user u ON a.user_id=u.id_card " +
                "LEFT JOIN tb_case c on c.id=a.case_id " +
                "LEFT JOIN tb_medical_org o on a.apply_org_id=o.id ");
        criteria.andVarEqualTo("a.id",authId);
        return  basicService.selectJoint(example);
    }
}
