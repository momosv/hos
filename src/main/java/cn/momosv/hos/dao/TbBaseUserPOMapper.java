package cn.momosv.hos.dao;

import cn.momosv.hos.model.TbBaseUserPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbBaseUserPOMapper {
    TbBaseUserPO selectByPrimaryKey(String id);

    @Select("select id from tb_org_patient where user_id = #{idCard}")
    List<String> getPatientIdListByIdCard(@Param("idCard") String idCard);

    List<Object> getAuthorityList(@Param("isAllow")int isAllow,@Param("key") String key,@Param("keyType") String keyType, @Param("user") TbBaseUserPO user);
}