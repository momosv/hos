package cn.momosv.hos.model.base;

import java.io.Serializable;

/**
 * @description base
 * @author Lin Shengwen
 * @dateTime 2018/1/5 9:56
 * @version
 */
public  abstract class IBaseDBPO implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;

	abstract public  String _getTableName();

	 public String _getPKColumnName(){
	 	return "id";
	 };

	abstract public String _getPKValue();

	abstract public void _setPKValue(Object var1); 

}
