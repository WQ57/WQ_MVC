package com.wq.web.servlet.render;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.wq.common.util.JsonUtil;
import com.wq.web.servlet.exception.WqMvcException;

/**
 * ajax请求json渲染器.
 * 
 * @author wuqing
 * @date 2014年8月9日 下午4:54:54
 */
public class JsonRender implements Render {

	public void render(Object returnObj, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			PrintWriter p = resp.getWriter();
			String json = JsonUtil.toStr(returnObj);
			p.print(json);
			p.flush();
		} catch (JsonGenerationException e) {
			throw new WqMvcException(e);
		} catch (JsonMappingException e) {
			throw new WqMvcException(e);
		} catch (IOException e) {
			throw new WqMvcException(e);
		}
	}

}
