package com.kjplus.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import com.kjplus.annotation.PerformanceLog;

/**
 * 
 * @author niuzhiwei 请求日志记录
 */
public class ReqLogAspect {

	// 请求开始时间
	private Long reqStartTime;
	// 请求结束时间
	private Long reqEndTime;
	// 响应时间
	private Long respTime;

	private static Logger logger = Logger.getLogger(ReqLogAspect.class);

	/**
	 * 前置通知
	 * 
	 * @param joinPoint
	 *            连接点 可获取连接点：方法运行时的入参列表、方法签名对象、目标对象、代理对象本身
	 */
	public void before(JoinPoint joinPoint) {
		// 请求的类
		String className = joinPoint.getTarget().getClass().getName();
		// 请求类中的方法
		String methodName = joinPoint.getSignature().getName();
		// 获取请求开始时间
		reqStartTime = System.currentTimeMillis();
		logger.info("请求开始时间为:" + reqStartTime);
		logger.info("执行的类为:" + className);
		logger.info("执行类的方法为:" + methodName);

	}

	public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
		// 异常类名称
		String className = joinPoint.getTarget().getClass().getName();
		// 异常方法名称
		String methodName = joinPoint.getSignature().getName();
		// 自定义异常信息字符串
		String Exception = "执行类为:" + className + "执行方法为：" + methodName + "异常类为:" + ex.getClass() + "异常信息为:"
				+ ex.getMessage();
		// 打印异常信息
		logger.warn(Exception);
		logger.info(Exception);
	}

	/**
	 * 后置通知
	 * 
	 * @param joinPoint
	 *            连接点 可获取连接点：方法运行时的入参列表、方法签名对象、目标对象、代理对象本身
	 */
	public void after(JoinPoint joinPoint) {
		// 获取云行类的对象
		Class<?> Class = joinPoint.getTarget().getClass();
		// 获取运行类对象的方法数组
		Method[] methods = Class.getMethods();
		// 获取运行时方法名称
		String methodName = joinPoint.getSignature().getName();

		for (Method method : methods) {
			PerformanceLog performanceLog = method.getAnnotation(PerformanceLog.class);
			// 方法上有此注解，并且是正在运行的方法进行日志记录
			if (performanceLog != null && method.getName().endsWith(methodName)) {
				logger.info("获取到运行方法注解的方法类型为：" + performanceLog.methodType());
				logger.info("获取到运行方法注解的业务类型为：" + performanceLog.bizType());
			}
		}
		// 请求结束时间
		reqEndTime = System.currentTimeMillis();
		// 响应时间
		respTime = reqEndTime - reqStartTime;
		if (respTime > 1000) {
			// 入库
		}
		// 获取方法运行时长超过
		logger.info("请求结束时间为:" + reqEndTime);
		logger.info("执行方法耗时为：:" + respTime);
	}
}
