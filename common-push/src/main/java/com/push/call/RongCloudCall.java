/**
 * 融云 Server API java 客户端
 * create by kitName
 * create datetime : 2017-03-13 
 * 
 * v2.0.1
 */
package com.push.call;

import java.util.concurrent.ConcurrentHashMap;

import com.push.method.*;

//融云所有推送push 消息发送  对用户的操作 通过此类调用
public class RongCloudCall {

	private static ConcurrentHashMap<String, RongCloudCall> rongCloud = new ConcurrentHashMap<String, RongCloudCall>();

	public User user;
	public Message message;
	public Wordfilter wordfilter;
	public Group group;
	public Chatroom chatroom;
	public Push push;
	public SMS sms;

	private RongCloudCall(String appKey, String appSecret) {
		user = new User(appKey, appSecret);
		message = new Message(appKey, appSecret);
		wordfilter = new Wordfilter(appKey, appSecret);
		group = new Group(appKey, appSecret);
		chatroom = new Chatroom(appKey, appSecret);
		push = new Push(appKey, appSecret);
		sms = new SMS(appKey, appSecret);

	}

	public static RongCloudCall getInstance(String appKey, String appSecret) {
		if (null == rongCloud.get(appKey)) {
			rongCloud.putIfAbsent(appKey, new RongCloudCall(appKey, appSecret));
		}
		return rongCloud.get(appKey);
	}

}