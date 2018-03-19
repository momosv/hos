package cn.momosv.hos.dao;

import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbDoctorPO;
import cn.momosv.hos.model.TbOrgManagerPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.websocket.server.PathParam;
import java.util.List;

public interface TbOrgManagerPOMapper {
    TbOrgManagerPO selectByPrimaryKey(String id);

    @Select("select count(1) from tb_base_user u,tb_doctor d where u.id=d.user_id " +
            "and d.org_id=#{orgId} and u.id_card=#{idCard}")
    int countDoctorByIdCard(@Param("orgId") String orgId, @Param("idCard") String idCard);

   @Select("select count(1) from tb_doctor d where d.org_id=#{orgId} and d.account=#{email}")
    int countDoctorByEmail(@Param("orgId") String orgId, @Param("email") String email);

    @Select("select d.* from tb_base_user u,tb_doctor d where u.id=d.user_id " +
            "and d.org_id=#{orgId} and u.id_card=#{idCard}")
    List<TbDoctorPO> getDoctorByIdCard(@Param("orgId") String orgId, @Param("idCard") String idCard);

    @Select("select * from tb_org_manager  where org_id=#{orgId}")
    List<TbOrgManagerPO> getManagerList(@Param("orgId")String orgId);
}