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

	private Map<Object, Object> bean = new HashMap<Object, Object>();

	public Map<Object, Object> getBean() {
		return bean;
	}

	public void setBean(Map<Object, Object> bean) {
		this.bean = bean;
	}

}
