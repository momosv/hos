package cn.momosv.hos.bean;

import java.io.Serializable;

public  abstract class IBaseDBPO implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	abstract public  String _getTableName();

	abstract public String _getPKColumnName();

	abstract public String _getPKValue();

	abstract public void _setPKValue(Object var1); 

}
