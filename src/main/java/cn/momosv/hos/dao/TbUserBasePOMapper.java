package cn.momosv.hos.dao;


import cn.momosv.hos.model.base.BasicExample;

import java.util.List;


public interface TbUserBasePOMapper {
	
	 List<TbUserBasePO> selectUserWithTest(BasicExample example);

}