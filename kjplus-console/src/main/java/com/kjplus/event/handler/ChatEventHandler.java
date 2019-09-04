package com.kjplus.event.handler;


import java.io.IOException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.kjplus.event.ChatEvent;
import com.kjplus.socket.MsgWebSocketCtrl;

@Component
@Scope("prototype") 
public class ChatEventHandler implements ApplicationListener<ChatEvent> {

	@Resource(name = "taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;

	private Logger logger = Logger.getLogger(ChatEventHandler.class);

	public void onApplicationEvent(final ChatEvent event) {
		// webSocket推送事件 利用线程池处理 
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					MsgWebSocketCtrl.sendMessage(event.getMsg());
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		});

	}
}
