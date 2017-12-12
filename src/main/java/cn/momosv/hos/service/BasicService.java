/**  
 * @Title: BasicService.java
 * @Package com.yjw.andy.service
 * @Description: TODO
 * @author 余健文
 * @date 2016年9月19日
 */
package cn.momosv.hos.service;

import java.util.List;
import java.util.Map;


/**
 * @Title: BasicService.java
 * @Package com.yjw.andy.service
 * @Description: TODO
 * @author 余健文
 * @date 2016年9月19日
 */
public interface BasicService<T ,E > {

    int countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(T t, String id);

    List<T> selectByExample(Class<T> clazz, E example)throws Exception;

    T selectByPrimaryKey(Class<T> clazz, String ids)throws Exception;

    List<T> selectByPrimaryKey(Class<T> clazz, String[] ids) throws Exception;

    int updateByExample(T record, E example);

	Object getFieldValueByName(String fieldName, Object o);

	String[] getFieldName(Object o);

	Object[] getFieldValues(Object o);

	public   int insertBatch(List<T> list);

	public  int updateBatch(List<T> list);

	Map<String, Object> getFieldMapValues(List<T> list, boolean selective);

	int updateBatch(List<T> list, boolean selective);

	List<T> findAll(Class<T> t) throws Exception ;

	int insertOne(T record);

    int updateOne(T record);

    public int updateOne(T t, boolean selective);

	int updateByExample(T record, E example, boolean selective);

	int deleteByPrimaryKey(T t, List<String> id);

	int deleteByPrimaryKey(T t, String[] id);
	
}
