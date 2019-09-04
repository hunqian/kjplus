package com.kjplus.socket;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
//import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

//注册为监听类
//@WebListener
public class RequestLister implements ServletRequestListener {

	public void requestInitialized(ServletRequestEvent sre) {
		// 将所有request请求都携带上httpSession
		((HttpServletRequest) sre.getServletRequest()).getSession();
	}

	public void requestDestroyed(ServletRequestEvent arg0) {
	}
}
