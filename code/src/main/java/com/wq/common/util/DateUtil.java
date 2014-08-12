package com.wq.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wq.web.servlet.exception.WqException;

/**
 * 时间工具类.
 * 
 * @author wuqing
 * @date 2014年8月10日 下午7:57:20
 */
public class DateUtil {

	public static String format_01 = "yyyy-MM-dd HH:mm:ss";

	public static String format_02 = "yyyy-MM-dd";

	public static Map<Integer, String> formatMap = new HashMap<Integer, String>();

	static {
		formatMap.put(format_01.length(), format_01);
		formatMap.put(format_02.length(), format_02);
	}

	/**
	 * 字符串转日期.
	 * 
	 * @param str
	 * @return
	 * @author wuqing
	 * @date 2014年8月10日 下午8:01:10
	 */
	public static Date strToDate(String str) {
		if (str == null) {
			return null;
		}
		return strToDate(str, formatMap.get(str.length()));
	}

	/**
	 * 字符串转日期.
	 * 
	 * @param str
	 * @param format
	 * @return
	 * @author wuqing
	 * @date 2014年8月10日 下午8:01:19
	 */
	public static Date strToDate(String str, String format) {
		if (str == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(str);
		} catch (ParseException e) {
			throw new WqException(e);
		}
	}
}
