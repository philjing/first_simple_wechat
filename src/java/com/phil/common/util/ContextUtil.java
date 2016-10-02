package com.phil.common.util;

import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;

@Lazy(false)
public class ContextUtil implements InitializingBean {
	
	private Properties props;

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		for (Entry<Object, Object> e : props.entrySet()) {
			System.setProperty(e.getKey().toString(), e.getValue().toString());
		}
	}

}
