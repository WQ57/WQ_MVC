package com.wq.common.util;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.wq.testVO.A;

/**
 * json工具类测试.
 * 
 * @author wuqing
 * @date 2014年8月9日 上午9:39:56
 */
public class JsonUtilTest {

	@Test
	public void testStrToBean() throws JsonParseException,
			JsonMappingException, IOException {
		String str = "{\"str\":\"str\",\"iNum\":111,\"lNum\":444,\"date\":\"2014-08-09 09:45:51\"}";
		A a = JsonUtil.toBean(str, A.class);
		System.out.println(a.toString());
	}

	@Test
	public void testBeanToStr() throws JsonGenerationException,
			JsonMappingException, IOException {
		A a = new A();
		a.setDate(new Date());
		a.setiNum(111);
		a.setlNum(444l);
		a.setStr("str");
		System.out.println(JsonUtil.toStr(a));
	}
}
