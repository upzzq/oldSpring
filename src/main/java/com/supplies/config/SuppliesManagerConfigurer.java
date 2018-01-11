package com.supplies.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class SuppliesManagerConfigurer extends PropertyPlaceholderConfigurer {

	private Map<String, String> propsMap = new HashMap<String, String>();
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String valueStr = props.getProperty(keyStr);
			propsMap.put(keyStr, valueStr);
		}
	}

	public Map<String, String> getPropsMap() {
		return propsMap;
	}

	public String getProperty(String key){
		if(propsMap != null){
			return propsMap.get(key);
		}
		return null;
	}


	
	

	
}
