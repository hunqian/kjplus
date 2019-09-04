package org.ybk.basic.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.ybk.basic.util.DateUtils;
import org.ybk.basic.util.MD5;

public class OpenHttpUtil {
    
    public static String getSortUrl(Map<String,Object> map){
    	try{
	        ArrayList<String> list = new ArrayList<String>();
	        for(Map.Entry<String,Object> entry:map.entrySet()){
	            list.add(entry.getKey() + "=" + URLEncoder.encode(entry.getValue().toString(),"UTF-8"));
	        }
	        int size = list.size();
	        String [] arrayToSort = list.toArray(new String[size]);
	        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
	        StringBuffer buf = new StringBuffer();
	        for(String as:arrayToSort){
	        	buf.append("&").append(as);
	        }
	        return buf.substring(1);
    	}catch(UnsupportedEncodingException ue){
    		return "";
    	}
    }
        
    public static String md5Url(String url,String hashkey,String certkey){
    	if(url == null || url.length() == 0)
    		return "";
    	//url += "&time=" + DateUtils.getCurTimeInSec();
    	String md5Value = MD5.MD5Encode(url+"&"+hashkey+"="+certkey);
    	return md5Value;
    }
    
    public static void main(String argc[]){
    	Map<String,Object> req = new HashMap<String,Object>();
    	req.put("channel", "liangshishuo");
    	req.put("mobile", "18812345678");
    	req.put("nickname", "周三");
    	req.put("appid", "lszs888");
    	req.put("time", DateUtil.getCurTimeInSec());
    	String url = getSortUrl(req);
    	System.out.println(url);
    	String certUrl = md5Url(url, "cert", "952fucz15psyh43fnv0y1uo07l13m133");
    	System.out.println(certUrl);
    	
    }
}
