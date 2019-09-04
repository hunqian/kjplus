package com.kjplus.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.kjplus.dto.MessageDto;
import com.kjplus.event.ChatEvent;
import com.kjplus.service.IMqHandlerService;
import com.mq.call.MessageReceiverEvent;
import com.mq.constant.Constant;
import com.mq.message.MsgCommand;
import com.mq.util.FatchApplicationContext;

//消息处理类
//实现Callable接口（Callable接口支持返回值，而Runnable接口不支持），并重写其call方法，将要业务代码写在call方法中
@Service("mqHandlerService")
@Scope("prototype")
public class MqHandlerService implements IMqHandlerService, ApplicationListener<MessageReceiverEvent> {

	private static Logger logger = Logger.getLogger(MqHandlerService.class);
	// 开启多线程执行
	@Resource(name = "taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;

	public void onApplicationEvent(final MessageReceiverEvent event) {
		// mq消息事件 继续产生消息事件 利用线程池 执行
		taskExecutor.execute(new Runnable() {
			public void run() {
				logger.info("监听到java消息产生的事件了");
				String liserType = event.getListerType();
				String msgType = event.getMsgType();
				if (Constant.MSG_TYPE_OBJECT.equals(msgType) && Constant.LISTER_TYPE_TOPIC.equals(liserType)) {
					// 获取消息命令
					MsgCommand cmd = (MsgCommand) event.getCmd();
					// 获取消息数据对象 通过 webSocket发送给指定用户
					MessageDto msg = (MessageDto) cmd.getMsgBody();
					// 发送消息事件
					FatchApplicationContext.getApplicationContext().publishEvent(new ChatEvent(this, msg));
				}
			}
		});
	}

}
