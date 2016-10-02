package com.phil.common.dao.impl;

import java.sql.SQLException;
import java.util.Collection;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.phil.common.dao.CommonDao;

@Repository
public class CommonDaoImpl implements CommonDao {
	
	/**
     * Hibernate template object.
     */
	@Resource
	protected HibernateTemplate hibernateTemplate;
	
	/**
     * Jdbc template object.
     */
	@Resource
	protected JdbcTemplate jdbcTemplate;

	protected SimpleJdbcCall jdbcCall;
	
	private String dialect;
    
    
    /**
	 * Default Constructor.
	 */
	public CommonDaoImpl() {
		
	}


	/**
	 * 是否存在指定条件数据。
	 * @param entityClass 映射类
	 * @param whereClause 条件
	 * @param values 条件值
	 * @return true:存在，false:不存在
	 */
	public boolean isExist(Class<?> entityClass,String whereClause, Object... values ) {
		String selectHql = "select count(id) from "+entityClass.getName() + "  where " + whereClause;
		return true; //findLong(selectHql, values) > 0;
	}
	
	
	/**
	 * 执行update,delete,insert语句。
	 * @param hql
	 * 
	 * @return 执行影响的数量
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int execute(final String hql, final Object... args) {
		return (Integer) getHibernateTemplate().executeWithNativeSession(new HibernateCallback(){
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Query query =  session.createQuery(hql);
				for (int i = 0; args != null && i < args.length; i++) {
					query.setParameter(i, args[i]);
				}
				return query.executeUpdate();
			}
		});
	}
	
	/**
	 * 持久化
	 * @param entity
	 */
	public void save(Object entity) {
		getHibernateTemplate().save(entity);
	}

	/**
	 * 更新
	 * @param entity
	 */
	public void update(Object entity) {
		getHibernateTemplate().update(entity);
	}

	/**
	 * 保存或更新。
	 * @param entity
	 */
	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	/**
	 * 删除操作。
	 * @param entity
	 */
	public void delete(Object entity){
		getHibernateTemplate().delete(entity);
	}
	
	/**
	 * 批量删除。
	 * @param entities
	 */
	public void deleteAll(Collection<?> entities){
		getHibernateTemplate().deleteAll(entities);
	}
	
	/**
	 * 根据id删除某实体。
	 * @param clasz
	 * @param id
	 */
	public void delete(Class<?> clasz,Object id){
		execute("delete " + clasz.getName() + " where id=?", id);
	}
	
	/**
	 *  批量按照id删除数据。
	 * @param clasz 待删除的实体类
	 * @param ids 待删除的ids(数组)
	 */
	public void delete(Class<?> clasz,Object[] ids){
		//execute("delete " + clasz.getName() + " where id in ("+DaoHelper.getDeleteIds(ids)+")");
	}
	
	/**
	 * 批量按照id删除数据。
	 * @param clasz 待删除的实体类
	 * @param ids 待删除的ids："1,2,3,4..."
	 */
	public void delete(Class<?> clasz,String ids){
		execute("delete " + clasz.getName() + " where id in ("+ids+")");
	}
	
	/**
	 * 批量按照某属性的取值范围删除数据。
	 * @param clasz 待删除的实体类
	 * @param property 实体类的属性："name"或"code"等等"...."
	 * @param values  实体类的属性的取值范围
	 
	public void delete(Class<?> clasz,String property,Object[] values){
		execute("delete " + clasz.getName() + " where "+property+" in ("+DaoHelper.getDeleteIds(values)+")");
	}
	*/

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public SimpleJdbcCall getJdbcCall() {
		return jdbcCall;
	}

	public void setJdbcCall(SimpleJdbcCall jdbcCall) {
		this.jdbcCall = jdbcCall;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

}
