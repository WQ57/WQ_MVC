package com.wq.common.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.wq.web.servlet.exception.WqMvcException;

/**
 * 对象工具类.
 * 
 * @author qingwu
 * @date 2014-8-8 下午5:57:36
 */
public class ValueUtil {

	@SuppressWarnings("rawtypes")
	private static List<Class> constantDefinitionObject = new ArrayList<Class>();

	static {
		constantDefinitionObject.add(int.class);
		constantDefinitionObject.add(Integer.class);
		constantDefinitionObject.add(float.class);
		constantDefinitionObject.add(Float.class);
		constantDefinitionObject.add(double.class);
		constantDefinitionObject.add(Double.class);
		constantDefinitionObject.add(long.class);
		constantDefinitionObject.add(Long.class);
		constantDefinitionObject.add(short.class);
		constantDefinitionObject.add(Short.class);
		constantDefinitionObject.add(char.class);
		constantDefinitionObject.add(Character.class);
		constantDefinitionObject.add(String.class);
		constantDefinitionObject.add(Date.class);
		constantDefinitionObject.add(BigDecimal.class);
	}

	/**
	 * 值对象 --> String.
	 * 
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午03:15:11
	 */
	public static String getString(Object value) {
		if (value == null) {
			return null;
		}
		return value.toString();
	}

	/**
	 * 值对象 --> long.
	 * 
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午03:27:21
	 */
	public static long getLong(Object value) {
		try {
			return Long.parseLong(getString(value));
		} catch (Exception e) {
			return 0L;
		}
	}

	/**
	 * 值对象 --> double.
	 * 
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午03:29:25
	 */
	public static double getDouble(Object value) {
		try {
			return Double.parseDouble(getString(value));
		} catch (Exception e) {
			return 0.0;
		}
	}

	/**
	 * 值对象 --> BigDecimal.
	 * 
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2013-3-31 下午4:12:37
	 */
	public static BigDecimal getBigDecimal(Object value) {
		return new BigDecimal(getString(value));
	}

	/**
	 * 值对象 --> int.
	 * 
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午03:29:35
	 */
	public static int getInt(Object value) {
		try {
			return Integer.parseInt(getString(value));
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 值对象 --> boolean.
	 * 
	 * @param value
	 * @return
	 * @author yangz
	 * @date 2012-10-12 上午09:00:16
	 */
	public static boolean getBoolean(Object value) {
		try {
			String v = getString(value);
			if ("1".equals(v)) {
				return true;
			} else if ("0".equals(v)) {
				return false;
			} else if ("Y".equals(v)) {
				return true;
			} else if ("N".equals(v)) {
				return false;
			} else {
				return Boolean.parseBoolean(v);
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 强行值类型转换.
	 * 
	 * @param value
	 * @param type
	 * @return
	 * @author yangz
	 * @date 2013-2-8 下午5:26:26
	 */
	public static Object castValue(Object value, Class<?> type) {
		if (int.class.isAssignableFrom(type)
				|| Integer.class.isAssignableFrom(type)) {
			return ValueUtil.getInt(value);
		} else if (long.class.isAssignableFrom(type)
				|| Long.class.isAssignableFrom(type)) {
			return ValueUtil.getLong(value);
		} else if (double.class.isAssignableFrom(type)
				|| Double.class.isAssignableFrom(type)) {
			return ValueUtil.getDouble(value);
		} else if (boolean.class.isAssignableFrom(type)
				|| Boolean.class.isAssignableFrom(type)) {
			return ValueUtil.getBoolean(value);
		} else if (String.class.isAssignableFrom(type)) {
			return ValueUtil.getString(value);
		} else if (BigDecimal.class.isAssignableFrom(type)) {
			return ValueUtil.getBigDecimal(value);
		} else if (char.class.isAssignableFrom(type)
				|| Character.class.isAssignableFrom(type)) {
			return (char) ValueUtil.getInt(value);
		} else if (java.util.Date.class.isAssignableFrom(type)) {
			return DateUtil.strToDate(getString(value));
		} else {
			throw new WqMvcException("unknow value type:" + type);
		}
	}

	/**
	 * 是否是自定义的类型.
	 * 
	 * @param cls
	 * @return
	 * @author wuqing
	 * @date 2014年8月10日 下午5:47:44
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isCustomDefinitionObject(Class cls) {
		for (Class c : constantDefinitionObject) {
			if (cls.isAssignableFrom(c)) {
				return false;
			}
		}
		return true;
	}

}
