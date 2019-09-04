package org.ybk.basic.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {
	
	/**
	 * 从Request对象中获得客户端的域名，处理了HTTP代理服务器和Nginx的反向代理截取域名
	 * 
	 * @param request
	 * @return ip
	 */
	public static String getVisitHost(HttpServletRequest request) {
		String remoteHost = request.getRemoteAddr();
		String forwardedHost = request.getHeader("Host");

		String host = null;
		if(Util.isNotNull(forwardedHost))
			host = forwardedHost;
		if(Util.isNull(host))
			host = remoteHost;
		int p = 0;
		p = host.indexOf("://");
		if(p>-1)
			host = host.substring(p+"://".length());
		p = host.lastIndexOf("/");
		if(p>-1)
			host = host.substring(0,p);
		if(host.startsWith("www."))
			host = host.substring(4);
		return host;
	}
	
	/**
	 * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
	 * 
	 * @param request
	 * @return ip
	 */
	public static String getLocalIp(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		String forwarded = request.getHeader("X-Forwarded-For");
		String realIp = request.getHeader("X-Real-IP");

		String ip = null;
		if (realIp == null) {
			if (forwarded == null) {
				ip = remoteAddr;
			} else {
				ip = remoteAddr + "/" + forwarded.split(",")[0];
			}
		} else {
			return realIp;
			/*
			 * if (realIp.equals(forwarded)) { ip = realIp; } else { if
			 * (forwarded != null) { forwarded = forwarded.split(",")[0]; } ip =
			 * realIp + "/" + forwarded; }
			 */
		}
		return ip;
	}

	public static List<ReqParam> parseReq(String requestUrl) {
		List<ReqParam> params = new ArrayList<ReqParam>();
		if (Util.isNull(requestUrl))
			return params;
		int pos = requestUrl.indexOf("?");
		if(pos>-1)
			requestUrl = requestUrl.substring(pos+1);
		String[] ps = requestUrl.split("[&]");
		for(int i=0;i<ps.length;i++){
			String[] ps0 = ps[i].split("[\\=]"); 
			if(ps0.length == 2){
				ReqParam p = new ReqParam(ps0[0],ps0[1]);
				params.add(p);
			}
		}
		return params;
	}
	
	public static String composeUrl(String path, Object[][] params){
		String u = path;
		if(Util.isNull(path))
			return "";
		if(params == null ||params.length == 0)
			return u;
		u += "?";
		u += params[0][0].toString() + "=" + params[0][1].toString();
		for(int i=1;i<params.length; i++)
			u += "&" + params[i][0].toString() + "=" + params[i][1].toString();
		return u;
	}
	
	public static void main(String argc[]){
		/*String url = "/android/jszgzhx/solutions?exerciseId=35005679&ids=1763946%2C1763972%2C1763990%2C1764024%2C1764044%2C1764066%2C1764094%2C1764102%2C1764108%2C1764114%2C1764120%2C1764128%2C1764132%2C1764134%2C1764138%2C1764140%2C1764142%2C1764146%2C1764152%2C1764154&platform=android19&version=1.0.2.2&vendor=tencent&av=3&app=zj&deviceId=uzwvh3zaZS9UVECxCnoSFw==&kav=1";
		List<ReqParam> params =  parseReq(url);
		for(ReqParam p:params){
			System.out.println(p);
		}*/
		Object[][] params = new Object[][]{{"catgid",123},{"num",111},{"channel","abc"},{"time",DateUtil.currTime()}};
		String returnUrl = HttpRequestUtil.composeUrl("/qbmiscexe.html", params);;
		System.out.println(returnUrl);
	}
}
