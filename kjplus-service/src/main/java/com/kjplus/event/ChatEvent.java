package com.kjplus.event;

import org.springframework.context.ApplicationEvent;

import com.kjplus.dto.MessageDto;

//聊天事件
public class ChatEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7160940672156058828L;

	private MessageDto msg;// 聊天消息对象

	public ChatEvent(Object source, MessageDto msg) {
		super(source);
		this.msg = msg;
	}

	public MessageDto getMsg() {
		return msg;
	}

	public void setMsg(MessageDto msg) {
		this.msg = msg;
	}

}
