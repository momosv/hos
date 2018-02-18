package cn.momosv.hos.dao;

import cn.momosv.hos.model.TbCasePO;
import cn.momosv.hos.model.base.BasicExample;
import org.apache.ibatis.annotations.Param;

public interface TbCasePOMapper {
    TbCasePO selectByPrimaryKey(String id);

    Object selectCaseList(@Param("example")BasicExample caseExample);
}