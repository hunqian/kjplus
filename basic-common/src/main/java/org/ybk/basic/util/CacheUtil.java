package org.ybk.basic.util;

import java.util.Hashtable;

public class CacheUtil {

	private static Hashtable<String,String> caches = new Hashtable<String,String>();
	
	public static String getVl(String key){
		if(Util.isNull(key))
			return "";
		if(caches.containsKey(key))
			return caches.get(key);
		else
			return "";
	}
	
	public static boolean contains(String key){
		if(Util.isNull(key))
			return false;
		return caches.containsKey(key);
	}
	
	public static void put(String key,String vl){
		if(Util.isNull(key)||Util.isNull(vl))
			return;
		caches.put(key, vl);
	}
}
