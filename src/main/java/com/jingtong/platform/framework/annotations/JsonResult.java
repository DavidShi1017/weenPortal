package com.jingtong.platform.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于指示字段�?��转换成JSON对象 �?��include和exclude不会同时使用
 * 
 * example:
 * 
 * @JsonResult(include = { "id", "name" }) private List<User> users = new
 *                     List<User>(); ... getter
 * 
 * @author tingjia.chentj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JsonResult {

	/**
	 * 记录集字段的名字
	 */
	String field();

	/**
	 * 总记录数字段名字
	 * 
	 * @return
	 */
	String total() default "";

	/**
	 * 包含的属�?
	 */
	String[] include() default {};

	/**
	 * 排除的属�?
	 */
	String[] exclude() default {};

	/**
	 * 请求固定JSON格式
	 * 
	 * @return
	 */
	String type() default "";

	/**
	 * 返回的JS请求格式JSONP
	 * 
	 * @return
	 */
	String responseFormat() default "";
}