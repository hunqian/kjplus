package com.mq.service.impl;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.log4j.Logger;

import com.mq.constant.Constant;
import com.mq.event.StartEvent;
import com.mq.util.ActiveMqConnectionUtil;

//连接丢失监听
public class ConnErrorListener implements javax.jms.ExceptionListener {

	private static Logger logger = Logger.getLogger(StartEvent.class);

	public void onException(JMSException exception) {
		Thread tQueue = new Thread(new Runnable() {
			public void run() {
				try {
					// 初始queue队列监听
					Connection queueConnection = ActiveMqConnectionUtil.getConnection();
					queueConnection.setExceptionListener(new ConnErrorListener());
					queueConnection.start();
					Session queueSession = queueConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
					Destination queueDestination = queueSession.createQueue(Constant.MQ_MESSAGE_QUEUE);
					MessageConsumer queueMessageConsumer = queueSession.createConsumer(queueDestination);
					queueMessageConsumer.setMessageListener(new MessageQueueReceiver());
					logger.info("[queue]连接产生异常！处理机制已处理完成");
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
					logger.info("[topic]连接产生异常！处理机制已处理完成");
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});
		tTopic.start();
		tQueue.start();
	}

}
