package com.push.message;

//消息基类，如实现自定义消息，可继承此类
public abstract class BaseMessage {

	// 获取消息类型
	public abstract String getType();

	// 转json
	public abstract String toString();
}
