package com.mq.event;

import javax.jms.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.mq.constant.Constant;
import com.mq.service.impl.*;
import com.mq.util.ActiveMqConnectionUtil;

/**
 * 
 * @author niuzhiwei
 *         <p>
 *         实现了servlet上下文监听
 *         <p>
 *         在tomcat启动时运行类中
 *         <p>
 *         （contextInitialized(ServletContextEvent arg0) ）方法
 *         <p>
 *         完成active的topic订阅者和queue的消费者
 * 
 */
public class StartEvent implements ServletContextListener {

	private static Logger logger = Logger.getLogger(StartEvent.class);

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		Thread tQueue = new Thread(new Runnable() {
			public void run() {
				try {
					// 初始queue队列监听
					Connection queueConnection = ActiveMqConnectionUtil.getConnection();
					queueConnection.setExceptionListener(new ConnErrorListener());
					queueConnection.start();
					// 默认 不开启事务 不开启事务是需要指定消息确认方式
					Session queueSession = queueConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
					Destination queueDestination = queueSession.createQueue(Constant.MQ_MESSAGE_QUEUE);
					MessageConsumer queueMessageConsumer = queueSession.createConsumer(queueDestination);
					queueMessageConsumer.setMessageListener(new MessageQueueReceiver());
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});

		Thread tTopic = new Thread(new Runnable() {
			public void run() {
				try {
					// 初始化topic发布/订阅监听
					Connection topicConnection = ActiveMqConnectionUtil.getConnection();
					// 诱捕丢失的连接
					topicConnection.setExceptionListener(new ConnErrorListener());
					topicConnection.setClientID(Constant.MQ_CLENT_CHAT_ID);
					topicConnection.start();
					Session topicSession = topicConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
					Topic topicDestination = topicSession.createTopic(Constant.MQ_MESSAGE_TOPIC);
					// 创建持久化订阅
					TopicSubscriber ts = topicSession.createDurableSubscriber(topicDestination, Constant.MQ_CLENT_CHAT_ID);
					// 设置消息监听者 TopicSubscriber topic持久化 必须以此方式创建持久化监听
					// unsubscribe
					ts.setMessageListener(new MessageTopicReceiver());
					// 设置消费者
					MessageConsumer topicMessageConsumer = topicSession.createConsumer(topicDestination);
					// 设置消费者监听
					topicMessageConsumer.setMessageListener(ts.getMessageListener());
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});

		tTopic.start();
		tQueue.start();
		logger.info("初始化监听成功");
	}
}
