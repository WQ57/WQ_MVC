package com.wq.common.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Json工具类.
 * 
 * @author wuqing
 * @date 2014年8月9日 上午9:23:19
 */
public class JsonUtil {

	public static final ObjectMapper mapper = new ObjectMapper();

	static {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(dateFormat);
	}

	/**
	 * 字符串转json.
	 * 
	 * @param jsonStr
	 * @param targetClass
	 * @return
	 * @author wuqing
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @date 2014年8月9日 上午9:25:23
	 */
	public static <T> T toBean(String jsonStr, Class<T> targetClass)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonStr, targetClass);
	}

	/**
	 * bean转json字符串.
	 * 
	 * @param obj
	 * @return
	 * @author wuqing
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @date 2014年8月9日 上午9:30:31
	 */
	public static String toStr(Object obj) throws JsonGenerationException,
			JsonMappingException, IOException {
		return mapper.writeValueAsString(obj);
	}

}
