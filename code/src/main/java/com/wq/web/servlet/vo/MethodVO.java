package com.wq.web.servlet.vo;

import java.util.Arrays;

/**
 * 方法对象VO.
 * 
 * @author qingwu
 * @date 2014-8-8 下午4:13:32
 */
public class MethodVO {

	/**
	 * 类对应的方法名称.
	 */
	private String name;

	/**
	 * 类对应的方法参数.
	 */
	@SuppressWarnings("rawtypes")
	private Class[] paramType;

	/**
	 * 类对应的方法参数名称.
	 */
	private String[] paramNames;

	/**
	 * 返回类型.
	 */
	@SuppressWarnings("rawtypes")
	private Class returnType;

	@SuppressWarnings("rawtypes")
	public MethodVO(String name, Class[] paramType, String[] paramNames,
			Class returnType) {
		super();
		this.name = name;
		this.paramType = paramType;
		this.paramNames = paramNames;
		this.returnType = returnType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the paramType
	 */
	@SuppressWarnings("rawtypes")
	public Class[] getParamType() {
		return paramType;
	}

	/**
	 * @param paramType
	 *            the paramType to set
	 */
	@SuppressWarnings("rawtypes")
	public void setParamType(Class[] paramType) {
		this.paramType = paramType;
	}

	/**
	 * @return the paramNames
	 */
	public String[] getParamNames() {
		return paramNames;
	}

	/**
	 * @param paramNames
	 *            the paramNames to set
	 */
	public void setParamNames(String[] paramNames) {
		this.paramNames = paramNames;
	}

	/**
	 * @return the returnType
	 */
	@SuppressWarnings("rawtypes")
	public Class getReturnType() {
		return returnType;
	}

	/**
	 * @param returnType
	 *            the returnType to set
	 */
	@SuppressWarnings("rawtypes")
	public void setReturnType(Class returnType) {
		this.returnType = returnType;
	}

	@Override
	public String toString() {
		return "MethodVO [name=" + name + ", paramType="
				+ Arrays.toString(paramType) + ", paramNames="
				+ Arrays.toString(paramNames) + ", returnType=" + returnType
				+ "]";
	}

}
