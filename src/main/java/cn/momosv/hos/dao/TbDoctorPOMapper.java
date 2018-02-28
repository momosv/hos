package cn.momosv.hos.dao;

import cn.momosv.hos.model.TbDoctorPO;
import cn.momosv.hos.model.base.BasicExample;


public interface TbDoctorPOMapper {
    TbDoctorPO selectByPrimaryKey(String id);

    Object selectCaseList(BasicExample caseExample);
}