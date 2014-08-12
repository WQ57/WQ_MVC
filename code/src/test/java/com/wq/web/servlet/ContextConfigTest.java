package com.wq.web.servlet;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * WqMvc全局配置测试.
 * 
 * @author qingwu
 * @date 2014-8-8 上午11:21:24
 */
public class ContextConfigTest {

	ContextConfig contextConfig;

	@Before
	public void beforeTest() {
		contextConfig = new ContextConfig("WqMvc.xml");
	}

	/**
	 * 测试初始化.
	 * 
	 * @author qingwu
	 * @date 2014-8-8 上午11:21:59
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testInit() {
		contextConfig.init();
		Map<String, ViewMapper> mapperCtrl = contextConfig.getMapperCtrl();
		Set<String> set = mapperCtrl.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println("==============================");
			System.out.println(key);
			System.out.println(mapperCtrl.get(key));
		}
	}

}
