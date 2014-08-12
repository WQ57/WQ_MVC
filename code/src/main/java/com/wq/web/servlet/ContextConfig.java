package com.wq.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wq.common.util.ClassLoaderUtil;
import com.wq.common.util.ReflectUtil;
import com.wq.common.util.XmlUtil;
import com.wq.web.servlet.exception.WqMvcException;
import com.wq.web.servlet.render.FreemarkerRender;
import com.wq.web.servlet.render.JsonRender;
import com.wq.web.servlet.render.PageRenderConfig;
import com.wq.web.servlet.render.Render;
import com.wq.web.servlet.render.RenderFactory;
import com.wq.web.servlet.vo.MapperClassVO;
import com.wq.web.servlet.vo.MapperMethodVO;

/**
 * 全局配置.
 * 
 * @author qingwu
 * @date 2014-8-7 下午3:33:54
 */
public class ContextConfig {

	/**
	 * 是否已经初始化.
	 */
	private static Boolean isInit = false;

	/**
	 * 配置文件位置.
	 */
	private String contextConfigLocation;

	/**
	 * 控制器
	 */
	private static Map<String, ViewMapper> viewMapper = new HashMap<String, ViewMapper>();

	/**
	 * 配置文档.
	 */
	private Document configXml;

	/**
	 * @return the mapperCtrl
	 */
	public static Map<String, ViewMapper> getMapperCtrl() {
		return viewMapper;
	}

	/**
	 * 
	 * @param contextConfigLocation
	 */
	public ContextConfig(String contextConfigLocation) {
		this.contextConfigLocation = contextConfigLocation;
		if (isInit == false) {
			init();
		}
	}

	/**
	 * 初始化.
	 * 
	 * @author qingwu
	 * @date 2014-8-7 下午3:45:39
	 */
	public void init() {
		synchronized (isInit) {
			if (isInit == false) {
				isInit = true;
				setConfigXml();
				initViewMapper();
				initAjaxRender();
				initPageRender();
			}
		}
	}

	/**
	 * 读取配置文件.
	 * 
	 * @author wuqing
	 * @date 2014年8月10日 下午8:42:57
	 */
	private void setConfigXml() {
		try {
			URL pathUrl = ClassLoaderUtil
					.getExtendResource(contextConfigLocation);
			InputStream in = new FileInputStream(new File(pathUrl.getPath()));
			configXml = XmlUtil.getXmlDocument(in);
		} catch (MalformedURLException e) {
			throw new WqMvcException(e);
		} catch (FileNotFoundException e) {
			throw new WqMvcException(e);
		}
	}

	/**
	 * 初始化控制器映射map.
	 * 
	 * @author qingwu
	 * @date 2014-8-7 下午4:07:03
	 */
	private void initViewMapper() {
		try {
			Element root = configXml.getDocumentElement();
			NodeList ctrlList = root.getElementsByTagName("ControllerPackage");
			if (ctrlList.getLength() == 0) {
				throw new WqMvcException(
						"There is no 'ControllerPackage' node in the '"
								+ contextConfigLocation + "'");
			}
			for (int i = 0; i < ctrlList.getLength(); i++) {
				Node ctrlNode = ctrlList.item(i);
				String ctrlPackage = ctrlNode.getTextContent();
				addViewMapper(ctrlPackage);
			}
		} catch (DOMException e) {
			throw new WqMvcException(e);
		}
	}

	/**
	 * 添加视图控制器映射.
	 * 
	 * @param ctrlPackage
	 *            控制器包名
	 * @author qingwu
	 * @date 2014-8-7 下午4:26:18
	 */
	@SuppressWarnings({ "rawtypes" })
	private void addViewMapper(String ctrlPackage) {
		if (ctrlPackage != null && !ctrlPackage.equals("")) {
			List<Class> classes = ReflectUtil.getClassFromPackage(ctrlPackage);
			for (Class c : classes) {
				MapperClassVO mapperClassVO = new MapperClassVO(c);
				if (mapperClassVO.isMappered == true) {
					for (int i = 0; i < mapperClassVO.getUrlMapperMethods()
							.size(); i++) {
						MapperMethodVO mapperMethod = mapperClassVO
								.getUrlMapperMethods().get(i);
						String url = mapperClassVO.getUrlPrefix()
								+ mapperMethod.getUrl();
						ViewMapper mc = new ViewMapper();
						mc.setViewClass(c);
						mc.setUrl(url);
						mc.setRequestMethod(mapperMethod.getRequestMethod());
						mc.setIsAjax(mapperMethod.getIsAjax());
						mc.setMethod(mapperMethod.getMethod());
						mc.isMappered = true;
						if (viewMapper.containsKey(url)) {
							throw new WqMvcException(
									"There are same request url in controller mapper..."
											+ "url:" + url);
						}
						viewMapper.put(url, mc);
					}
				}
			}
		} else {
			throw new WqMvcException(
					"There is null value of 'ControllerPackage' node in the '"
							+ contextConfigLocation + "'");
		}
	}

	/**
	 * 初始化ajax渲染器.
	 * 
	 * @author wuqing
	 * @date 2014年8月10日 下午8:39:07
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initAjaxRender() {
		try {
			Element root = configXml.getDocumentElement();
			NodeList nodeList = root.getElementsByTagName("ajaxRender");
			if (nodeList.getLength() == 0) {// 如果没有配置则默认是json渲染
				RenderFactory.setAjaxRender(new JsonRender());
			} else if (nodeList.getLength() > 1) {// 配置了多个抛错
				throw new WqMvcException(
						"It must be only one node of 'ajaxRender'...");
			} else {
				// 取class属性值：ajax渲染器的实现类
				Node node = nodeList.item(0);
				String clsName = node.getAttributes().getNamedItem("class")
						.getNodeValue();
				if (clsName == null) {
					throw new WqMvcException(
							"There is no attribute of 'class' in the node of 'ajaxRender'...");
				}
				Class cls = Class.forName(clsName);
				// 必须实现Render接口
				if (!ReflectUtil.isImplementsInterface(cls,
						Render.class.getName())) {
					throw new WqMvcException(
							"AjaxRender must implements 'com.wq.web.servlet.render.Render'...");
				}
				// 初始化Render实例
				RenderFactory.setAjaxRender((Render) ReflectUtil
						.newInstance(cls));
			}
		} catch (ClassNotFoundException e) {
			throw new WqMvcException(e);
		}
	}

	/**
	 * 初始化页面渲染器.
	 * 
	 * @author wuqing
	 * @date 2014年8月10日 下午8:39:22
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initPageRender() {
		try {
			Element root = configXml.getDocumentElement();
			NodeList nodeList = root.getElementsByTagName("pageRender");
			if (nodeList.getLength() == 0) {// 如果没有配置则默认是freemarker渲染
				RenderFactory.setPageRender(new FreemarkerRender());
			} else if (nodeList.getLength() > 1) {// 配置了多个抛错
				throw new WqMvcException(
						"It must be only one node of 'pageRender'...");
			} else {
				// 取class属性值：page渲染器的实现类
				Node node = nodeList.item(0);
				String clsName = node.getAttributes().getNamedItem("class")
						.getNodeValue();
				if (clsName == null) {
					throw new WqMvcException(
							"There is no attribute of 'class' in the node of 'pageRender'...");
				}
				Class cls = Class.forName(clsName);
				// 必须实现Render接口
				if (!ReflectUtil.isImplementsInterface(cls,
						Render.class.getName())) {
					throw new WqMvcException(
							"PageRender must implements 'com.wq.web.servlet.render.Render'...");
				}
				// 初始化Render实例
				RenderFactory.setPageRender((Render) ReflectUtil
						.newInstance(cls));
				// 设置页面地址前缀和后缀
				NodeList childs = node.getChildNodes();
				for (int i = 0; i < childs.getLength(); i++) {
					Node child = childs.item(i);
					if (child.getNodeName().equals("prefix")) {
						PageRenderConfig.pagePrefix = child.getTextContent();
					} else if (child.getNodeName().equals("suffix")) {
						PageRenderConfig.pageSuffix = child.getTextContent();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			throw new WqMvcException(e);
		}
	}
}
