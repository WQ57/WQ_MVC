package com.wq.web.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.common.util.RequestUtil;
import com.wq.common.util.ValueUtil;
import com.wq.web.servlet.vo.MethodVO;

/**
 * 视图映射工厂.
 * 
 * @author qingwu
 * @date 2014-8-8 下午5:33:48
 */
public class ViewMapperFactory {

	/**
	 * 获取视图.
	 * 
	 * @return
	 * @author qingwu
	 * @date 2014-8-8 下午5:33:41
	 */
	@SuppressWarnings("unchecked")
	public static ViewMapper getView() {
		// 根据请求地址映射到具体的视图控制器
		HttpServletRequest req = WebContext.getRequest();
		String uri = req.getServletPath().split("\\.")[0];
		ViewMapper viewMapper = ContextConfig.getMapperCtrl().get(uri);
		List<Object> params = new ArrayList<Object>();
		// 如果视图控制器不为空，注入请求参数
		if (viewMapper != null) {
			MethodVO method = viewMapper.getMethod();
			for (int i = 0; i < method.getParamNames().length; i++) {
				String param = req.getParameter(method.getParamNames()[i]);
				if (HttpServletRequest.class.isAssignableFrom(method
						.getParamType()[i])) {
					params.add(WebContext.getRequest());
				} else if (HttpServletResponse.class.isAssignableFrom(method
						.getParamType()[i])) {
					params.add(WebContext.getResponse());
				} else {
					if (ValueUtil.isCustomDefinitionObject(method
							.getParamType()[i]) == false) {
						params.add(ValueUtil.castValue(param,
								method.getParamType()[i]));
					} else {
						params.add(RequestUtil.paramsToBean(req,
								method.getParamType()[i]));
					}
				}
			}
			viewMapper.setRequestParams(params);
		}
		return viewMapper;
	}
}
