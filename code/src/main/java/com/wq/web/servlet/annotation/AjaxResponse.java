package com.wq.web.servlet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ajax请求标签.
 * 
 * @author qingwu
 * @date 2014-8-8 下午3:34:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface AjaxResponse {

	String value() default "";

}
