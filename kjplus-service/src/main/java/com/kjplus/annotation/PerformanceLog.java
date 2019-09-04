package com.kjplus.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author niuzhiwei
 *         <p>
 *         日志记录注解 必须插入methodType方法类型 和 bizType业务类型
 *         <p>
 *         ElementType.METHOD 用于描述方法(只能在方法中使用此注解)
 *         <p>
 *         RetentionPolicy.RUNTIME在运行时有效（即运行时保留）定义注解的生命周期
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PerformanceLog {

	// 方法类型 如SELECT查询
	MethodType methodType();

	// 业务类型 可能是报表
	BizType bizType();
}
