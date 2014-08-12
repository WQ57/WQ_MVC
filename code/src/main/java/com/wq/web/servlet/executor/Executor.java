package com.wq.web.servlet.executor;

import com.wq.common.util.ReflectUtil;
import com.wq.web.servlet.ViewMapper;

/**
 * 请求执行器.
 * 
 * @author wuqing
 * @date 2014年8月8日 下午10:23:23
 */
public class Executor {

	/**
	 * 视图控制器映射.
	 */
	private ViewMapper viewMapper;

	public Executor(ViewMapper viewMapper) {
		this.viewMapper = viewMapper;
	}

	/**
	 * 执行请求.
	 * 
	 * @return
	 * @author wuqing
	 * @date 2014年8月8日 下午10:47:59
	 */
	@SuppressWarnings("unchecked")
	public Object execute() {
		Object viewObj = ReflectUtil.newInstance(viewMapper.getViewClass());
		if (viewMapper.getMethod().getReturnType() == null) {
			ReflectUtil.invokeMethod(viewObj, viewMapper.getMethod().getName(),
					viewMapper.getRequestParams(), viewMapper.getMethod()
							.getParamType());
			return null;
		}
		return ReflectUtil.invokeMethod(viewObj, viewMapper.getMethod()
				.getName(), viewMapper.getRequestParams(), viewMapper
				.getMethod().getParamType());
	}
}
