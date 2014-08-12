package com.wq.web.servlet.vo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.wq.web.servlet.annotation.Controller;
import com.wq.web.servlet.annotation.RequestMapping;

/**
 * 控制层类对应VO.
 * 
 * @author qingwu
 * @date 2014-8-8 下午3:21:57
 */
public class MapperClassVO {

	/**
	 * 是否符合映射.
	 */
	public boolean isMappered = false;

	/**
	 * url前缀.
	 */
	private String urlPrefix;

	/**
	 * url对应的执行方法.
	 */
	private List<MapperMethodVO> urlMapperMethods = new ArrayList<MapperMethodVO>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MapperClassVO(Class c) {
		Controller ctrlAnno = (Controller) c.getAnnotation(Controller.class);
		if (ctrlAnno != null) {
			isMappered = true;
			// 类定义了模块
			RequestMapping classMapping = (RequestMapping) c
					.getAnnotation(RequestMapping.class);
			if (classMapping != null) {
				urlPrefix = classMapping.value();
			}
			// 对方法进行
			Method[] methods = c.getMethods();
			for (Method method : methods) {
				MapperMethodVO mapperMethodVO = new MapperMethodVO(c, method);
				if (mapperMethodVO.isMappered == true) {
					urlMapperMethods.add(mapperMethodVO);
				}
			}
		}
	}

	/**
	 * @return the urlPrefix
	 */
	public String getUrlPrefix() {
		return urlPrefix;
	}

	/**
	 * @param urlPrefix
	 *            the urlPrefix to set
	 */
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	/**
	 * @return the urlMapperMethods
	 */
	public List<MapperMethodVO> getUrlMapperMethods() {
		return urlMapperMethods;
	}

	/**
	 * @param urlMapperMethods
	 *            the urlMapperMethods to set
	 */
	public void setUrlMapperMethods(List<MapperMethodVO> urlMapperMethods) {
		this.urlMapperMethods = urlMapperMethods;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MapperClassVO [isMappered=" + isMappered + ", urlPrefix="
				+ urlPrefix + ", urlMapperMethods=" + urlMapperMethods + "]";
	}

}
