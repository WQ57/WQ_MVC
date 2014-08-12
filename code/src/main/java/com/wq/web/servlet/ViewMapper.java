package com.wq.web.servlet;

import java.util.List;

import com.wq.web.servlet.vo.MapperMethodVO;

/**
 * 映射视图.
 * 
 * @author qingwu
 * @date 2014-8-8 下午5:39:35
 */
public class ViewMapper extends MapperMethodVO {

	/**
	 * 类名.
	 */
	@SuppressWarnings("rawtypes")
	private Class viewClass;

	/**
	 * 请求参数.
	 */
	private ThreadLocal<List<Object>> requestParams = new ThreadLocal<List<Object>>();

	/**
	 * @return the viewClass
	 */
	@SuppressWarnings("rawtypes")
	public Class getViewClass() {
		return viewClass;
	}

	/**
	 * @param viewClass
	 *            the viewClass to set
	 */
	@SuppressWarnings("rawtypes")
	public void setViewClass(Class viewClass) {
		this.viewClass = viewClass;
	}

	/**
	 * @return the requestParams
	 */
	public List<Object> getRequestParams() {
		return requestParams.get();
	}

	/**
	 * @param requestParams
	 *            the requestParams to set
	 */
	public void setRequestParams(List<Object> requestParams) {
		this.requestParams.set(requestParams);
	}

}
