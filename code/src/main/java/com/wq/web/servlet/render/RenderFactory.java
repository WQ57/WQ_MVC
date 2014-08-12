package com.wq.web.servlet.render;

import com.wq.web.servlet.ViewMapper;

/**
 * 渲染器工厂.
 * 
 * @author wuqing
 * @date 2014年8月9日 下午4:43:01
 */
public class RenderFactory {

	/**
	 * ajax渲染器.
	 */
	private static Render ajaxRender;

	/**
	 * 页面渲染器.
	 */
	private static Render pageRender;

	/**
	 * @return the ajaxRender
	 */
	public static Render getAjaxRender() {
		return ajaxRender;
	}

	/**
	 * @param ajaxRender
	 *            the ajaxRender to set
	 */
	public static void setAjaxRender(Render ajaxRender) {
		RenderFactory.ajaxRender = ajaxRender;
	}

	/**
	 * @return the pageRender
	 */
	public static Render getPageRender() {
		return pageRender;
	}

	/**
	 * @param pageRender
	 *            the pageRender to set
	 */
	public static void setPageRender(Render pageRender) {
		RenderFactory.pageRender = pageRender;
	}

	/**
	 * 获取渲染器.
	 * 
	 * @return
	 * @author wuqing
	 * @date 2014年8月9日 下午4:42:52
	 */
	public static Render getRender(ViewMapper viewMapper) {
		if (viewMapper.getIsAjax().equals(true)) {
			return ajaxRender;
		} else {
			return pageRender;
		}
	}

}
