package com.wq.common.util;

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.wq.web.servlet.exception.WqException;

/**
 * HttpServletRequest请求工具类.
 * 
 * @author wuqing
 * @date 2014年8月10日 下午5:50:03
 */
public class RequestUtil {

	/**
	 * 请求参数转Bean.
	 * 
	 * @param req
	 * @param targetClz
	 * @return
	 * @author wuqing
	 * @date 2014年8月10日 下午5:52:18
	 */
	public static <T> T paramsToBean(HttpServletRequest req, Class<T> targetClz) {
		// 如果是非自定义类型
		if (ValueUtil.isCustomDefinitionObject(targetClz) == false) {
			throw new WqException(
					"The target class is not Custom Definition...");
		}
		T obj = ReflectUtil.newInstance(targetClz);
		Map<String, Field> fieldMaps = ReflectUtil.getFieldMap(targetClz);
		Map<String, String> params = getParamsMap(req);
		Set<String> set = params.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String paramName = it.next();
			String paramValue = params.get(paramName);
			if (fieldMaps.containsKey(paramName)) {
				Field field = fieldMaps.get(paramName);
				ReflectUtil.setFieldValue(obj, paramName,
						ValueUtil.castValue(paramValue, field.getType()));
			}
		}
		return obj;
	}

	/**
	 * 将请求参数转map.
	 * 
	 * @param req
	 * @return
	 * @author wuqing
	 * @date 2014年8月10日 下午8:08:09
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getParamsMap(HttpServletRequest req) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> enumeration = req.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String name = enumeration.nextElement();
			map.put(name, req.getParameter(name));
		}
		return map;
	}
}
