package com.wq.web.servlet.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型.
 * 
 * @author wuqing
 * @date 2014年8月8日 下午9:55:18
 */
public class Model {

	private Map<String, Object> bean = new HashMap<String, Object>();

	public Map<String, Object> getBean() {
		return bean;
	}

	public void setBean(Map<String, Object> bean) {
		this.bean = bean;
	}

}
