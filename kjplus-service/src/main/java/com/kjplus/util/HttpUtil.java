package com.kjplus.util;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author niuzhiwei
 *         <p>
 *         用于调用api接口时使用
 */
@SuppressWarnings("deprecation")
public class HttpUtil {

	private static Logger logger = Logger.getLogger(HttpUtil.class);
	// testURL
	public static final String URL_ADDRESS = ResourceBundle.getBundle("url", Locale.CHINA).getString("url_address");

	// 封装http请求
	public static String doGet(String url, Map<String, Object> params) {
		StringBuffer urlStr = new StringBuffer();
		urlStr.append(url + "?1=1");
		if (!params.isEmpty() || params != null) {
			for (String key : params.keySet()) {
				if (key == null)
					continue;
				Object obj = params.get(key);
				if (obj == null)
					continue;
				if (obj instanceof String) {
					urlStr.append("&" + key + "=" + (String) obj);
				}
				if (obj instanceof Integer) {
					urlStr.append("&" + key + "=" + (Integer) obj);
				}
			}
		}
		logger.info("[URL]:" + urlStr.toString());
		String result = "";
		try {
			// 根据地址获取请求
			HttpGet request = new HttpGet(urlStr.toString());// 这里发送get请求
			// 获取当前客户端对象
			@SuppressWarnings("resource")
			HttpClient httpClient = new DefaultHttpClient();
			// 通过请求对象获取响应对象
			HttpResponse response = httpClient.execute(request);
			// 判断网络连接状态码是否正常(0--200都数正常)
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	// 封装http请求
	public static String doGet(String url, Map<String, Object> params, final Map<String, String> headers) {
		StringBuffer urlStr = new StringBuffer();
		urlStr.append(url + "?1=1");
		if (!params.isEmpty() || params != null) {
			for (String key : params.keySet()) {
				if (key == null)
					continue;
				Object obj = params.get(key);
				if (obj == null)
					continue;
				if (obj instanceof String) {
					urlStr.append("&" + key + "=" + (String) obj);
				}
				if (obj instanceof Integer) {
					urlStr.append("&" + key + "=" + (Integer) obj);
				}
			}
		}
		logger.info("[URL]:" + urlStr.toString());
		String result = "";
		try {
			// 根据地址获取请求
			HttpGet request = new HttpGet(urlStr.toString());// 这里发送get请求
			if (!headers.isEmpty() || headers != null) {
				for (String key : headers.keySet()) {
					if (key == null)
						continue;
					Object obj = headers.get(key);
					if (obj == null)
						continue;
					if (obj instanceof String) {
						request.setHeader(key, (String) obj);
					}
				}
			}
			// 获取当前客户端对象
			@SuppressWarnings("resource")
			HttpClient httpClient = new DefaultHttpClient();
			// 通过请求对象获取响应对象
			HttpResponse response = httpClient.execute(request);
			// 判断网络连接状态码是否正常(0--200都数正常)
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	// 封装http请求
	public static String doPost(String url, Map<String, Object> params) {
		StringBuffer urlStr = new StringBuffer();
		urlStr.append(url + "?1=1");
		if (!params.isEmpty() || params != null) {
			for (String key : params.keySet()) {
				if (key == null)
					continue;
				Object obj = params.get(key);
				if (obj == null)
					continue;
				if (obj instanceof String) {
					urlStr.append("&" + key + "=" + (String) obj);
				}
				if (obj instanceof Integer) {
					urlStr.append("&" + key + "=" + (Integer) obj);
				}
			}
		}
		logger.info("[URL]:" + urlStr.toString());
		String result = "";
		try {
			// 根据地址获取请求
			HttpPost request = new HttpPost(urlStr.toString());
			// 获取当前客户端对象
			@SuppressWarnings("resource")
			HttpClient httpClient = new DefaultHttpClient();
			// 通过请求对象获取响应对象
			HttpResponse response = httpClient.execute(request);
			// 判断网络连接状态码是否正常(0--200都数正常)
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	// 封装http请求
	public static String doPost(String url, Map<String, Object> params, final Map<String, String> headers) {
		StringBuffer urlStr = new StringBuffer();
		urlStr.append(url + "?1=1");
		if (!params.isEmpty() || params != null) {
			for (String key : params.keySet()) {
				if (key == null)
					continue;
				Object obj = params.get(key);
				if (obj == null)
					continue;
				if (obj instanceof String) {
					urlStr.append("&" + key + "=" + (String) obj);
				}
				if (obj instanceof Integer) {
					urlStr.append("&" + key + "=" + (Integer) obj);
				}
			}
		}
		logger.info("[URL]:" + urlStr.toString());
		String result = "";
		try {
			// 根据地址获取请求
			HttpPost request = new HttpPost(urlStr.toString());
			if (!headers.isEmpty() || headers != null) {
				for (String key : headers.keySet()) {
					if (key == null)
						continue;
					Object obj = headers.get(key);
					if (obj == null)
						continue;
					if (obj instanceof String) {
						request.setHeader(key, (String) obj);
					}
				}
			}
			// 获取当前客户端对象
			@SuppressWarnings("resource")
			HttpClient httpClient = new DefaultHttpClient();
			// 通过请求对象获取响应对象
			HttpResponse response = httpClient.execute(request);
			// 判断网络连接状态码是否正常(0--200都数正常)
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
