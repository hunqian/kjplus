package org.ybk.sms;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.ybk.basic.util.StringUtil;
import org.ybk.basic.util.Util;

import com.alibaba.fastjson.JSONObject;

public class SmsUtil {
	private static Logger logger = Logger.getLogger(SmsUtil.class);
	
	//创世花信短信发送
	public static Map<String,Object> sendHuaxinSms(String userid,String acc,String pass,String phones,String content) {
		String encoding = "UTF-8";
		//String userid = "AC00104";
		//String account = "AC00104";
		//String password = StringUtil.md5("ac0314PASS").toUpperCase();
		String password = StringUtil.md5(pass).toUpperCase();
		//String curTimeStr = Util.getTimeStr(Calendar.getInstance().getTime());
		Map<String,String> params = new HashMap<String,String>(); 
		params.put("userid", userid);
		params.put("account", acc);
		params.put("password", password);
		params.put("mobile", phones);
		params.put("content", content);
		params.put("sendTime", "");
		//params.put("sendTime", curTimeStr);
		params.put("action", "send");
		params.put("extno", "");
		return sendHuaxinSmsGet(encoding,params);	
	}

	//创世华信Ad短信发送
	public static Map<String,Object> sendHuaxinAdSms(String userid,String acc,String pass,String phones,String content) {
		String encoding = "UTF-8";
		//String password = StringUtil.md5(pass).toUpperCase();
		Map<String,String> params = new HashMap<String,String>(); 
		params.put("userid", userid);
		params.put("account", acc);
		params.put("password", pass);
		params.put("mobile", phones);
		params.put("content", content);
		params.put("sendTime", "");
		params.put("action", "send");
		params.put("extno", "");
		return sendHuaxinAdSms(params);	
	}
	
	//创世华信短信发送
	private static Map<String,Object> sendHuaxinSmsGet(String encoding,Map<String,String> params) {
		String url = "http://dx.ipyy.net/smsJson.aspx";
		HttpClient client = new HttpClient();
		GetMethod get = null;
		Map<String,Object> results = new HashMap<String,Object>();
		try{			
			StringBuffer paramBuf = new StringBuffer();
			Iterator<String> keys = params.keySet().iterator();
			String k = null;
			while(keys.hasNext()){
				k = keys.next();
				paramBuf.append(k).append("=");
				paramBuf.append(URLEncoder.encode(params.get(k),encoding));
				paramBuf.append("&");
			}
			String u = url + "?" + paramBuf.substring(0,paramBuf.length()-1);
			get = new GetMethod(u);
			client.executeMethod(get);
			
			String result = new String(get.getResponseBodyAsString().getBytes(encoding));
			logger.debug("[结果]"+result); // 打印返回消息状态
			
			JSONObject json = JSONObject.parseObject(result);
			Set<String> keySet = json.keySet();
			for(String ks:keySet){
				results.put(ks, json.get(ks));
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(get != null)
				get.releaseConnection();
		}
		return results;
	}
	
	//创世华信短信发送
	private static Map<String,Object> sendHuaxinAdSms(Map<String,String> params) {
		String url = "http://125.208.3.91:8888/sms.aspx";
		String encoding = "UTF-8";
		HttpClient client = new HttpClient();
		GetMethod get = null;
		Map<String,Object> results = new HashMap<String,Object>();
		try{
			StringBuffer paramBuf = new StringBuffer();
			Iterator<String> keys = params.keySet().iterator();
			String k = null;
			while(keys.hasNext()){
				k = keys.next();
				paramBuf.append(k).append("=");
				paramBuf.append(URLEncoder.encode(params.get(k),encoding));
				paramBuf.append("&");
			}
			String u = url + "?" + paramBuf.substring(0,paramBuf.length()-1);
			get = new GetMethod(u);
			client.executeMethod(get);
			
			long len = get.getResponseContentLength();
			byte b[] = new byte[(int)len];
			get.getResponseBodyAsStream().read(b);
			String result = new String(b,encoding);
			//String result = new String(get.getResponseBodyAsString().getBytes(encoding));
			logger.debug("[结果]"+result); // 打印返回消息状态
			System.out.println("[结果]"+result);
			
			JAXBContext context2 = JAXBContext.newInstance(HuaxinADResult.class);
			Unmarshaller unmarshaller = context2.createUnmarshaller();
			HuaxinADResult resultXml = (HuaxinADResult) unmarshaller.unmarshal(new StringReader(result));
			results.put("message",resultXml.getMessage());
			results.put("remainpoint",resultXml.getRemainpoint());
			results.put("returnstatus",resultXml.getReturnstatus());
			results.put("successCounts",resultXml.getSuccessCounts());
			results.put("taskID",resultXml.getTaskID());
		}catch(Exception ioe){
			ioe.printStackTrace();
		}finally{
			if(get != null)
				get.releaseConnection();
		}
		return results;
	}
		
	public static void main(String[] args) {
		String phones = "13910924649";
		String curTimeStr = Util.getTimeStr(Calendar.getInstance().getTime());
		String content = "【刷课君】代学生，迎接4.23联考,沈栋●卢璟等名师为您免费直播http://w.url.cn/s/AsUcCpH";
		/*String acc = "AC00104";
		String pass = "ac0314PASS";
		Map<String,Object> result = sendHuaxinSms(acc,pass,phones,content);
		System.out.println("res:" + result.get("returnstatus"));*/
		
		String userid = "10024";
		String acc = "ACYX0026";
		String pass = "ACAD0314Pass";
		Map<String,Object> result = sendHuaxinAdSms(userid,acc,pass,phones,content);
		System.out.println("res:" + result.get("returnstatus"));
		
	}

}
