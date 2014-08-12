package com.wq.web.servlet;

import java.io.IOException;

import javassist.ClassPool;
import javassist.NotFoundException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.web.servlet.exception.WqMvcException;
import com.wq.web.servlet.executor.Executor;
import com.wq.web.servlet.render.Render;
import com.wq.web.servlet.render.RenderFactory;

/**
 * 总控制流转.
 * 
 * @author qingwu
 * @date 2014-8-7 下午3:12:36
 */
public class DispatcherServlet extends HttpServlet {

	/**
	 * 配置文件位置.
	 */
	private static String contextConfigLocation;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5648549659505278970L;

	/**
	 * 初始化.
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		if (contextConfigLocation == null) {
			contextConfigLocation = config
					.getInitParameter("contextConfigLocation");
			try {
				// TODO 类加载器做到自动适配
				ClassPool
						.getDefault()
						.insertClassPath(
								"D:/MyPrograms/springsource/vfabric-tc-server-developer-2.9.3.RELEASE/WQ/wtpwebapps/WqMVC/WEB-INF/classes");
			} catch (NotFoundException e) {
				throw new WqMvcException(e);
			}
			new ContextConfig(contextConfigLocation).init();
		}
	}

	/**
	 * 服务分发.
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 将当前请求和输出放到线程中
		String uri = req.getServletPath();
		WebContext.setResponse(resp);
		WebContext.setResquest(req);
		// 获取当前请求的映射视图(包括参数注入)
		ViewMapper viewMapper = ViewMapperFactory.getView();
		if (viewMapper == null) {
			throw new WqMvcException(
					"There is no mapper view with the request of '" + uri
							+ "'...");
		}
		// 获得请求执行器
		Executor executor = new Executor(viewMapper);
		Object returnObj = executor.execute();
		// 执行请求
		if (returnObj != null) {
			// 获得渲染器
			Render render = RenderFactory.getRender(viewMapper);
			// 执行渲染
			render.render(returnObj, req, resp);
		}
	}

	/**
	 * @return the 配置文件位置
	 */
	public String getContextConfigLocation() {
		return contextConfigLocation;
	}

	/**
	 * @param contextConfigLocation
	 *            配置文件位置
	 */
	@SuppressWarnings("static-access")
	public void setContextConfigLocation(String contextConfigLocation) {
		this.contextConfigLocation = contextConfigLocation;
	}

}
