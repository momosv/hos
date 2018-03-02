package cn.momosv.hos.dao;

import cn.momosv.hos.model.TbCasePO;
import cn.momosv.hos.model.base.BasicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbCasePOMapper {
    TbCasePO selectByPrimaryKey(String id);

    List<Object> selectCaseList(@Param("example")BasicExample caseExample);
}