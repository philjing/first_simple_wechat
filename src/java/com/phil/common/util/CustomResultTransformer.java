package com.phil.common.util;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;

 /**
 * 自定义的数据库字库转换成POJO。
 */
@SuppressWarnings("serial")
public class CustomResultTransformer implements ResultTransformer
{	

	private final Class<?> resultClass;
	
	private Class<?>[]	clazz;
	
	private Setter[] setters;
	
	private final PropertyAccessor propertyAccessor;
	
	public CustomResultTransformer(Class<?> resultClass)
	{
		if ( resultClass == null )
			throw new IllegalArgumentException ( "resultClass cannot be null" );
		this.resultClass = resultClass;
		propertyAccessor = new ChainedPropertyAccessor ( new PropertyAccessor[] { PropertyAccessorFactory.getPropertyAccessor ( resultClass , null ),
				PropertyAccessorFactory.getPropertyAccessor ( "field" ) } );
	}
	
	// 结果转换时，HIBERNATE调用此方法
	public Object transformTuple( Object[] tuple , String[] aliases )
	{
		Object result;
		try
		{
			if ( setters == null )
			{// 首先初始化，取得目标POJO类的所有SETTER方法
				setters = new Setter[aliases.length];
				clazz = new Class[aliases.length];
				for ( int i = 0 ; i < aliases.length ; i++ )
				{
					String alias = aliases[i];
					if ( alias != null )
					{
						// 我的逻辑主要是在getSetterByColumnName方法里面，其它都是HIBERNATE的另一个类中COPY的
						// 这里填充所需要的SETTER方法
						setters[i] = getSetterByColumnName (alias,i);
					}
				}
			}
			result = resultClass.newInstance();
			
			// 这里使用SETTER方法填充POJO对象
			for ( int i = 0 ; i < aliases.length ; i++ )
			{
				if ( setters[i] != null && tuple[i] != null )
				{
					try
					{
						setters[i].set ( result , ConvertUtils.convert ( tuple[i] , clazz[i] ) , null );
					} catch ( SecurityException e )
					{
						e.printStackTrace ( );
					}
				}
			}
		} catch ( InstantiationException e )
		{
			throw new HibernateException ( "Could not instantiate resultclass: " + resultClass.getName ( ) );
		} catch ( IllegalAccessException e )
		{
			throw new HibernateException ( "Could not instantiate resultclass: " + resultClass.getName ( ) );
		}
		
		return result;
	}
	
	// 根据数据库字段名在POJO查找JAVA属性名，参数就是数据库字段名，如：USER_ID
	private Setter getSetterByColumnName ( String alias , int index )
	{
		// 取得POJO所有属性名
		Field[] fieldsSuperClass = resultClass.getSuperclass ( ).getDeclaredFields ( );
		Field[] fieldsMine = resultClass.getDeclaredFields ( );
		Field[] fields = (Field[]) ArrayUtils.addAll ( fieldsSuperClass , fieldsMine );
		if ( fields == null || fields.length == 0 )
		{
			throw new RuntimeException ( "实体" + resultClass.getName ( ) + "不含任何属性" );
		}
		// 把字段名中所有的下杠去除
		String proName = alias.replaceAll ( "_" , "" ).toLowerCase ( );
		for ( Field field : fields )
		{
			if ( field.getName ( ).toLowerCase ( ).equals ( proName ) )
			{
				// 去除下杠的字段名如果和属性名对得上，就取这个SETTER方法
				clazz[index] = field.getType ( );
				return propertyAccessor.getSetter ( resultClass , field.getName ( ) );
			}
		}
		//throw new RuntimeException("找不到数据库字段 ：" + alias + " 对应的POJO属性或其getter方法，比如数据库字段为USER_ID或USERID，那么JAVA属性应为userId");
		return null;
	}

	
	@SuppressWarnings("rawtypes")
	public List transformList ( List collection ){
		return collection;
	}
	
}


