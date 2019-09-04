package com.kjplus.util;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kjplus.constant.Constant;


public class SpringUtil {

	private static Log logger = LogFactory.getLog(SpringUtil.class);
	
	private static ApplicationContext factory = getApplicationContext();

	static {
		getApplicationContext();
	}

	public static void setFactoryBean(ApplicationContext factory) {
		SpringUtil.factory = factory;
	}

	/**
	 * 获得Spring框架应用上下文对象
	 * 
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		// 判断如果 ApplicationContext 的对象 ＝＝ NULL
		if (factory == null) {
			if (logger.isDebugEnabled())
				logger.debug("===================================Init Spring's ApplicationContext=========================================");
			try {
				// ConsolePrinter.println("******init spring application context");
				factory = new ClassPathXmlApplicationContext(new String[] { 
						//"beans-mq.xml"
						Constant.SPRING_INIT_XML_FILE
					});
			} catch (Exception e1) {
				if (logger.isDebugEnabled())
					logger.debug("err = " + e1.getMessage());
				e1.printStackTrace();
			}
		}
		// 返回ApplicationContext
		return factory;
	}
}