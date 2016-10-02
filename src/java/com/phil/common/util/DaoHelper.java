package com.phil.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;


public class DaoHelper {
	
	public static String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().lastIndexOf("from");
		return hql.substring(beginPos);
	}

	public static String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	public static String removeGroupBy(String hql) {
		Pattern p = Pattern.compile("group\\s*by[\\w|\\W|\\s|\\S]*",Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	public static String insertAlias(String hql, Class<?> clasz) {
		hql = " " + hql;
		List<Field> fields = getDeclaredFields(clasz);
		for (Field field : fields) {
			String name = field.getName();
			hql = hql.replaceAll(" " + name, " e." + name);
			hql = hql.replaceAll("\\(" + name, "\\(e." + name);
		}
		
		hql = hql.substring(1);
		return hql;
	}

	/**
	 * 循环向上转型, 获取对象的 DeclaredField
	 * @param object : 子类对象
	 * @return 父类中的属性对象
	 */
	
	public static List<Field> getDeclaredFields(Class<?>  clazz){
		List<Field> fields= new ArrayList<Field>();
		for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
			try {
				fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			} catch (Exception e) {
				//如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
				;
			} 
		}
		return fields;
	}
	
	public static String insertJoin(String... includes) {
		String hql = "";
		for (String include : includes) {
			hql += " left outer join  fetch e." + include + " as " + include
					+ " ";
		}
		return hql;
	}
	
	@SuppressWarnings("rawtypes")
	public static <E> ParameterizedRowMapper resultBeanMapper(Class<E> clazz) {
		return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
	}
	
	public static String getDeleteIds(Object... ids) {
		for(Object id : ids){
			if(id instanceof String)
				return StringHelper.join(ids, ",","'");
			else
				return StringHelper.join(ids, ",");
		}
		return null;
	}
	
	
	public static void main(String[] args){
		final String countSql = "select count(*) "
			+ DaoHelper.removeSelect(DaoHelper.removeOrders(DaoHelper.removeGroupBy("select count(*) from hm_funds_send_detail t left join hm_funds_project b on b.id = t.funds_project_id where 1=1 group by t.account")));
		System.out.println(countSql);
	}
}
