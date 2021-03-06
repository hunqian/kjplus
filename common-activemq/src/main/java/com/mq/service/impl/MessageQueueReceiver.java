package com.mq.service.impl;

import javax.jms.*;

import org.apache.log4j.Logger;

import com.mq.exception.MsgException;
import com.mq.handler.MessageQueueReceiverHandler;

/**
 * 
 * @author niuzhiwei
 *         <p>
 *         此类为抽象类只定义部分消息实现
 *         <p>
 *         具体业务行为有自己是想
 */
public final class MessageQueueReceiver implements MessageListener {

	private static Logger logger = Logger.getLogger(MessageQueueReceiver.class);

	private static MessageQueueReceiverHandler messasgeReceiverHandler = new MessageQueueReceiverHandler();

	// 监听消息的类型转换
	public void onMessage(Message message) {
		// 先确认 已经收到消息 在进行消息后续操作
		try {
			// 承认消息已经消费
			message.acknowledge();
			logger.info("Queue消息确认成功,消息ID为:{" + message.getJMSMessageID() + "}");
		} catch (JMSException e1) {
			logger.error(e1.getMessage());
		}
		TextMessage cmdTextMessage = null;
		MapMessage mapMessage = null;
		StreamMessage streamMessage = null;
		BytesMessage bytesMessage = null;
		ObjectMessage objectMessage = null;
		try {
			if (message instanceof TextMessage) {// 文本消息类型
				cmdTextMessage = (TextMessage) message;
				messasgeReceiverHandler.queueTextMessageHandler(cmdTextMessage);
			} else if (message instanceof MapMessage) {// 流消息
				mapMessage = (MapMessage) message;
				messasgeReceiverHandler.queueMapMessageHandler(mapMessage);
			} else if (message instanceof StreamMessage) {// 键对值消息
				streamMessage = (StreamMessage) message;
				messasgeReceiverHandler.queueStreamMessageHandler(streamMessage);
			} else if (message instanceof BytesMessage) {// 字节消息
				bytesMessage = (BytesMessage) message;
				messasgeReceiverHandler.queueBytesMessageHandler(bytesMessage);
			} else if (message instanceof ObjectMessage) {// 对象消息
				objectMessage = (ObjectMessage) message;
				messasgeReceiverHandler.queueObjectMessageHandler(objectMessage);
			} else {
				throw new MsgException("无此消息类型");
			}

		} catch (MsgException e) {
			logger.error(e.getMessage());
		}
	}
}