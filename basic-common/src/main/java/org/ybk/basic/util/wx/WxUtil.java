package org.ybk.basic.util.wx;

import java.net.URLEncoder;

import org.ybk.basic.util.Util;

public class WxUtil {
	
	public static String convertSnsapiUrl(String url, String appid,String state){
		if(Util.isNull(url))
			return "";
		if(Util.isNull(appid))
			return url;
		String url2 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid;
		url2 += "&redirect_uri=" + URLEncoder.encode(url);
		url2 += "&response_type=code&scope=snsapi_base&state="+state+"#wechat_redirect";
		return url2;
	}
	
	public static void main(String argv[]) {
		String appid = "wx4d3042227733843b";
		String url1 = "http://h5.jiesongwuyou.cn/bind.html";
		System.out.println(convertSnsapiUrl(url1,appid,"bnd100"));
	}
}
