package org.ybk.basic.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

public class SmsUtil {
	private static Logger logger = Logger.getLogger(SmsUtil.class);
	
	//private static String WC_SMS_USER = ResourceBundle.getBundle("config",Locale.CHINA).getString("wc_sms_user");
	//private static String WC_SMS_KEY = ResourceBundle.getBundle("config",Locale.CHINA).getString("wc_sms_key");
	
	/*public static void sendSmsUtf8ByWebChinese(String user,String pass,String phones,String msg) {

		HttpClient client = new HttpClient();
		PostMethod post = null;
		try{
			post = new PostMethod("http://utf8.sms.webchinese.cn");
			msg = Util.encodeGB2UTF8(msg);
			// 在头文件中设置转码
			post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");
			NameValuePair[] data = { 
					new NameValuePair("Uid", user),
					new NameValuePair("Key", pass),
					new NameValuePair("smsMob", phones),
					new NameValuePair("smsText", msg) };
			post.setRequestBody(data);
	
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:" + statusCode);
			for (Header h : headers) {
				System.out.println(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes("utf8"));
			System.out.println(result); // 打印返回消息状态
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(post != null)
				post.releaseConnection();
		}
	}*/
	
	//检验发送结果
	public static boolean checkSendResult(String result){
		return true;
	}
	
	//以GB发送
	/*public static String sendSmsByWebChinese(String phones,String msg) {
		return sendSmsGBKByWebChinese(WC_SMS_USER,WC_SMS_KEY,phones,msg);
	}*/
	
	public static String sendSmsGBKByWebChinese(String user,String pass,String phones,String msg) {
		HttpClient client = new HttpClient();
		PostMethod post = null;
		try{
			post = new PostMethod("http://gbk.sms.webchinese.cn");
			// 在头文件中设置转码
			post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");
			NameValuePair[] data = {
					new NameValuePair("Uid", user),
					new NameValuePair("Key", pass),
					new NameValuePair("smsMob", phones),
					new NameValuePair("smsText", msg) };
			post.setRequestBody(data);
	
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			logger.debug("statusCode:" + statusCode);
			System.out.println("statusCode:" + statusCode);
			for (Header h : headers) {
				logger.debug(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
			logger.debug("[结果]"+result); // 打印返回消息状态
			System.out.println("[结果]"+result);
			return result;
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(post != null)
				post.releaseConnection();
		}
		return "0";
	}
	
	public static void main(String[] args) {
		/*String user = "ybk007";
		String pass = "725aa88774b3a56e67a5";*/
		String user = "刷课君网站";
		String pass = "2f1bbdd8862310ff4feb";
		String phones = "13910924649";
		//String phones = "18131332246";
		//String phones = "13161793505";
		String msg = "发送时间测试2：" +Util.getTimeStr(Calendar.getInstance().getTime());
		sendSmsGBKByWebChinese(user,pass,phones,msg);
		
	}
}
