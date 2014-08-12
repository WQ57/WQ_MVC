package com.wq.web.servlet.exception;

/**
 * 异常.
 * 
 * @author qingwu
 * @date 2014-8-7 下午4:17:17
 */
public class WqMvcException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5991090075115183901L;

	public WqMvcException() {
		super();
	}

	public WqMvcException(String message, Throwable cause) {
		super(message, cause);
	}

	public WqMvcException(String message) {
		super(message);
	}

	public WqMvcException(Throwable cause) {
		super(cause);
	}
}
