package com.phil.common.dao;

import java.util.Collection;

public interface CommonDao{
	
	/**
	 * 是否存在指定条件数据。
	 * @param entityClass 映射类
	 * @param whereClause 条件
	 * @param values 条件值
	 * @return true:存在，false:不存在
	 */
	public boolean isExist(Class<?> entityClass,String whereClause, Object... values );
	
	
	/**
	 * 执行update,delete,insert语句。
	 * @param hql
	 * 
	 * @return 执行影响的数量
	 */
	public int execute(final String hql, final Object... args);
	
	/**
	 * 持久化
	 * @param entity
	 */
	public void save(Object entity) ;

	/**
	 * 更新
	 * @param entity
	 */
	public void update(Object entity) ;

	/**
	 * 保存或更新。
	 * @param entity
	 */
	public void saveOrUpdate(Object entity);
	/**
	 * 删除操作。
	 * @param entity
	 */
	public void delete(Object entity);
	
	/**
	 * 批量删除。
	 * @param entities
	 */
	public void deleteAll(Collection<?> entities);
	
	/**
	 * 根据id删除某实体。
	 * @param clasz
	 * @param id
	 */
	public void delete(Class<?> clasz,Object id);
	
	/**
	 *  批量按照id删除数据。
	 * @param clasz 待删除的实体类
	 * @param ids 待删除的ids(数组)
	 */
	public void delete(Class<?> clasz,Object[] ids);
	
	/**
	 * 批量按照id删除数据。
	 * @param clasz 待删除的实体类
	 * @param ids 待删除的ids："1,2,3,4..."
	 */
	public void delete(Class<?> clasz,String ids);
	
	/**
	 * 批量按照某属性的取值范围删除数据。
	 * @param clasz 待删除的实体类
	 * @param property 实体类的属性："name"或"code"等等"...."
	 * @param values  实体类的属性的取值范围
	 
	public void delete(Class<?> clasz,String property,Object[] values);
	*/

}
