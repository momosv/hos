package cn.momosv.hos.dao;

import cn.momosv.hos.bean.TbUserBasePO;
import cn.momosv.hos.bean.base.BasicExample;

import java.util.List;


public interface TbUserBasePOMapper {
	
	 List<TbUserBasePO> selectUserWithTest(BasicExample example);

}