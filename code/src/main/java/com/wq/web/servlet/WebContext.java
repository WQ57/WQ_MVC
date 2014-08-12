package com.wq.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebContext {

	private static ThreadLocal<HttpServletRequest> requestThread = new ThreadLocal<HttpServletRequest>();

	private static ThreadLocal<HttpServletResponse> responseThread = new ThreadLocal<HttpServletResponse>();

	public static void setResponse(HttpServletResponse _response) {
		responseThread.set(_response);
	}

	public static HttpServletResponse getResponse() {
		return responseThread.get();
	}

	public static void setResquest(HttpServletRequest _request) {
		requestThread.set(_request);
	}

	public static HttpServletRequest getRequest() {
		return requestThread.get();
	}
}
