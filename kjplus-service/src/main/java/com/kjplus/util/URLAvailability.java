package com.kjplus.util;

import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;

public class URLAvailability {

	private static Logger logger = Logger.getLogger(URLAvailability.class);

	private static URL url;
	private static HttpURLConnection con;
	private static int state = -1;

	/**
	 * 检测当前URL是否可连接或是否有效
	 * 
	 * @param urlStr
	 *            指定URL网络地址
	 * @param num
	 *            指定检测次数
	 * @return boolean true为可用 false为不可用
	 * 
	 */
	public synchronized static boolean isConnect(String urlStr, int num) {
		int counts = 0;
		if (urlStr == null || urlStr.length() <= 0) {
			return false;
		}
		while (counts < num) {
			try {
				url = new URL(urlStr);
				con = (HttpURLConnection) url.openConnection();
				state = con.getResponseCode();
				logger.info("url连接状态为:" + state);
				if (state == 200) {
					logger.info("URL可用！");
					return true;
				}
				break;
			} catch (Exception ex) {
				counts++;
				logger.error("URL不可用，连接第 " + counts + " 次");
				urlStr = null;
				continue;
			}
		}
		return false;
	}
}
