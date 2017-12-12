package cn.momosv.hos.dao;

import cn.momosv.hos.bean.BasicExample;
import cn.momosv.hos.bean.TbUserBasePO;

import java.util.List;


public interface TbUserBasePOMapper extends BasicMapper<TbUserBasePO, BasicExample>{
	
	 List<TbUserBasePO> selectUserWithTest(BasicExample example);

}