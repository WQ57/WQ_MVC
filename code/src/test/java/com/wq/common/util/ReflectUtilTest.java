package com.wq.common.util;

import java.util.List;

import org.junit.Test;

/**
 * 反射工具测试类.
 * 
 * @author qingwu
 * @date 2014-8-7 下午5:41:01
 */
public class ReflectUtilTest {

	@SuppressWarnings("rawtypes")
	@Test
	public void testGetClassFromPackage() {
		String packageName = "com.wq.web.servlet";
		List<Class> classes = ReflectUtil.getClassFromPackage(packageName);
		for (Class c : classes) {
			System.out.println(c.getName());
		}
	}
}
