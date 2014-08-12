package com.wq.web.servlet.vo;

import java.lang.reflect.Method;

import com.wq.common.util.ReflectUtil;
import com.wq.web.servlet.annotation.AjaxResponse;
import com.wq.web.servlet.annotation.RequestMapping;

/**
 * 控制层url对应方法VO.
 * 
 * @author qingwu
 * @date 2014-8-8 下午3:17:46
 */
public class MapperMethodVO {

	/**
	 * 是否符合映射.
	 */
	public boolean isMappered = false;

	/**
	 * 映射url.
	 */
	private String url;

	/**
	 * 请求method:com.wq.web.servlet.RequestMethod.
	 */
	private String requestMethod;

	/**
	 * 对应的方法对象.
	 */
	private MethodVO method;

	/**
	 * 是否是ajax请求输出.
	 */
	private Boolean isAjax = false;

	public MapperMethodVO() {

	}

	@SuppressWarnings("rawtypes")
	public MapperMethodVO(Class clazz, Method method) {
		RequestMapping requestMapping = method
				.getAnnotation(RequestMapping.class);
		AjaxResponse ajaxResponse = method.getAnnotation(AjaxResponse.class);
		if (requestMapping != null) {
			isMappered = true;
			this.url = requestMapping.value();
			this.requestMethod = requestMapping.method();
			this.method = new MethodVO(method.getName(),
					method.getParameterTypes(),
					ReflectUtil.getMethodParamNames(clazz, method.getName(),
							method.getParameterTypes()), method.getReturnType());
			if (ajaxResponse != null) {
				isAjax = true;
			} else {
				isAjax = false;
			}
		}
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the requestMethod
	 */
	public String getRequestMethod() {
		return requestMethod;
	}

	/**
	 * @param requestMethod
	 *            the requestMethod to set
	 */
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	/**
	 * @return the isAjax
	 */
	public Boolean getIsAjax() {
		return isAjax;
	}

	/**
	 * @param isAjax
	 *            the isAjax to set
	 */
	public void setIsAjax(Boolean isAjax) {
		this.isAjax = isAjax;
	}

	/**
	 * @return the method
	 */
	public MethodVO getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(MethodVO method) {
		this.method = method;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MapperMethodVO [isMappered=" + isMappered + ", url=" + url
				+ ", requestMethod=" + requestMethod + ", method=" + method
				+ ", isAjax=" + isAjax + "]";
	}

}
