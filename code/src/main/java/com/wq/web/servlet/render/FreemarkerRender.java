package com.wq.web.servlet.render;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wq.common.util.DateUtil;
import com.wq.web.servlet.exception.WqMvcException;
import com.wq.web.servlet.vo.ModelAndView;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;

/**
 * 页面跳转freemarker渲染器.
 * 
 * @author wuqing
 * @date 2014年8月9日 下午4:56:05
 */
public class FreemarkerRender implements Render {

	private Configuration cfg;

	private Template template;

	private ModelAndView modelAndView;

	/**
	 * 设置视图和模型.
	 * 
	 * @param returnObj
	 * @author wuqing
	 * @date 2014年8月10日 下午9:57:36
	 */
	public void setModelAndView(Object returnObj) {
		if (returnObj.getClass().isAssignableFrom(String.class)) {
			modelAndView = new ModelAndView();
			modelAndView.setViewName((String) returnObj);
		} else {
			if (!returnObj.getClass().isAssignableFrom(ModelAndView.class)) {
				throw new WqMvcException(
						"Return Type must be 'String' or 'com.wq.web.servlet.vo.ModelAndView'");
			}
			modelAndView = (ModelAndView) returnObj;
		}
	}

	/**
	 * 初始化渲染配置.
	 * 
	 * @author wuqing
	 * @date 2014年8月10日 下午9:57:48
	 */
	public void initFreemarker(HttpServletRequest req) {
		cfg = new Configuration();
		try {
			// 设置模板文件目录
			cfg.setDirectoryForTemplateLoading(new File(req.getSession()
					.getServletContext().getRealPath("/")
					+ PageRenderConfig.pagePrefix));
			DefaultObjectWrapper defaultObjectWrapper = new DefaultObjectWrapper();
			defaultObjectWrapper.setDefaultDateType(TemplateDateModel.DATETIME);
			cfg.setObjectWrapper(defaultObjectWrapper);
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);  
			cfg.setNumberFormat("0.######");
			//cfg.setLocale(new Locale("en_US"));
			//cfg.setDateFormat(DateUtil.format_01);
			template = cfg.getTemplate(modelAndView.getView().getName()
					+ PageRenderConfig.pageSuffix);
		} catch (IOException e) {
			throw new WqMvcException(e);
		}
	}

	/**
	 * 执行渲染.
	 * 
	 * @author wuqing
	 * @date 2014年8月10日 下午9:58:01
	 */
	public void process(HttpServletResponse rep) {
		try {
			template.process(modelAndView.getModel().getBean(), rep.getWriter());
		} catch (TemplateException e) {
			throw new WqMvcException(e);
		} catch (IOException e) {
			throw new WqMvcException(e);
		}
	}

	public void render(Object returnObj, HttpServletRequest req,
			HttpServletResponse rep) {
		// 设置视图和模型
		setModelAndView(returnObj);
		// 初始化渲染配置
		initFreemarker(req);
		// 执行渲染
		process(rep);
	}
}
