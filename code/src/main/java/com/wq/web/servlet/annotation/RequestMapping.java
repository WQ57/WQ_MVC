package com.wq.web.servlet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 跳转地址.
 * 
 * @author qingwu
 * @date 2014-8-7 下午5:53:15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface RequestMapping {

	/**
	 * 跳转地址.
	 * 
	 * @return
	 * @author qingwu
	 * @date 2014-8-7 下午5:56:04
	 */
	String value() default "";

	/**
	 * 请求方式.
	 * 
	 * @return
	 * @author qingwu
	 * @date 2014-8-7 下午5:56:09
	 */
	String method() default "";

}
