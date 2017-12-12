package cn.momosv.hos.bean;

import java.io.Serializable;

public  abstract class IBaseDBPO<T> implements Serializable, Cloneable{
	abstract public  String _getTableName();

	abstract public String _getPKColumnName();

	abstract public String _getPKValue();

	abstract public void _setPKValue(Object var1); 

}
