package cn.momosv.hos.dao;

import cn.momosv.hos.model.TbMedicalOrgPO;
import cn.momosv.hos.vo.TbMedicalOrgVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.websocket.server.PathParam;
import java.util.List;

public interface TbMedicalOrgPOMapper {
    TbMedicalOrgPO selectByPrimaryKey(String id);

    @Select("select id,name from Tb_Medical_Org where id in (#{orgId})")
    List<TbMedicalOrgVO> getLoginOrg(@Param("orgId") String[] orgId);
}