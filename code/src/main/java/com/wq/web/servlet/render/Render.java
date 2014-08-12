package com.wq.web.servlet.render;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 渲染器.
 * 
 * @author wuqing
 * @date 2014年8月9日 下午4:36:44
 */
public interface Render {

	/**
	 * 渲染.
	 * 
	 * @param returnObj
	 * @author wuqing
	 * @date 2014年8月9日 下午4:38:10
	 */
	public void render(Object returnObj, HttpServletRequest req,
			HttpServletResponse rep);

}
