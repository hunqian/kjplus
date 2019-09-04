package com.mq.service.impl;

import javax.jms.*;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.mq.exception.MsgException;
import com.mq.handler.MessageTopicReceiverHandler;
import com.mq.util.FatchApplicationContext;

/**
 * 
 * @author niuzhiwei
 *         <p>
 *         此类为抽象类只定义部分消息实现
 *         <p>
 *         具体业务行为有自己是想
 */
@Component
@Scope("prototype")
public class MessageTopicReceiver implements MessageListener {

	private static Logger logger = Logger.getLogger(MessageTopicReceiver.class);

	private ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) FatchApplicationContext.getApplicationContext()
			.getBean("taskExecutor");

	private static MessageTopicReceiverHandler messasgeTopicReceiverHandler = new MessageTopicReceiverHandler();

	// 监听消息的类型转换
	public void onMessage(Message message) {
		// 先确认 已经收到消息 在进行消息后续操作
		try {
			// 承认消息已经消费
			message.acknowledge();
			logger.info("Topic消息确认成功,消息ID为:{" + message.getJMSMessageID() + "}");
		} catch (JMSException e1) {
			logger.error(e1.getMessage());
		}
		System.out.println(message);
		TextMessage cmdTextMessage = null;
		MapMessage mapMessage = null;
		StreamMessage streamMessage = null;
		BytesMessage bytesMessage = null;
		try {
			if (message instanceof TextMessage) {// 文本消息类型
				cmdTextMessage = (TextMessage) message;
				messasgeTopicReceiverHandler.topicTextMessageHandler(cmdTextMessage);
			} else if (message instanceof MapMessage) {// 流消息
				mapMessage = (MapMessage) message;
				messasgeTopicReceiverHandler.topicMapMessageHandler(mapMessage);
			} else if (message instanceof StreamMessage) {// 键对值消息
				streamMessage = (StreamMessage) message;
				messasgeTopicReceiverHandler.topicStreamMessageHandler(streamMessage);
			} else if (message instanceof BytesMessage) {// 字节消息
				bytesMessage = (BytesMessage) message;
				messasgeTopicReceiverHandler.topicBytesMessageHandler(bytesMessage);
			} else if (message instanceof ObjectMessage) {// 对象消息
				final ObjectMessage objectMessage = (ObjectMessage) message;
				taskExecutor.execute(new Runnable() {
					public void run() {
						try {
							messasgeTopicReceiverHandler.topicObjectMessageHandler(objectMessage);
						} catch (Exception e) {
							logger.error(e.getMessage());
						}
					}
				});
			} else {
				throw new MsgException("无此消息类型");
			}
		} catch (MsgException e) {
			logger.error(e.getMessage());
		}
	}
}