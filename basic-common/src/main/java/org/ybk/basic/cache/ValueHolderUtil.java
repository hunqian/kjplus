package org.ybk.basic.cache;

import java.util.Enumeration;
import java.util.Hashtable;

import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.Util;

public class ValueHolderUtil {
	// 120秒
	private static final int DEF_INTEVAL = 120;
	// 默认最大长度
	private static final int DEF_MAX_LEN = 1000;
	// 默认名称
	public static final String DEF_NAME = "SMSCODE";

	// instance
	private static ValueHolderUtil instance = null;

	private static final String NAME_APPENDIX = ".name";
	private static final String ITVL_APPENDIX = ".itvl";
	private static final String MAX_LEN = ".maxlen";
	private static final String CUR_LEN = ".curlen";

	// 保存一些参数
	private static Hashtable<String, Integer> PARAM_HASH = new Hashtable<String, Integer>();
	// 保存值
	private static Hashtable<String, Integer> VALUE_HASH = new Hashtable<String, Integer>();

	private ValueHolderUtil() {
	}

	public static ValueHolderUtil getInstance(String name) {
		return getInstance(name, -1, -1);
	}

	public static ValueHolderUtil getInstance() {
		return getInstance(null, -1, -1);
	}

	public static ValueHolderUtil getInstance(String name, int defInterval, int defMaxLen) {
		if (instance == null) {
			instance = new ValueHolderUtil();
		}
		if (Util.isNull(name))
			name = DEF_NAME;
		if (defInterval > 0)
			PARAM_HASH.put(name + ITVL_APPENDIX, defInterval);
		else{
			if(!PARAM_HASH.containsKey(name + ITVL_APPENDIX))
				PARAM_HASH.put(name + ITVL_APPENDIX, DEF_INTEVAL);
		}
		if (defMaxLen > 0) {
			PARAM_HASH.put(name + MAX_LEN, defMaxLen);
		} else {
			if(!PARAM_HASH.containsKey(name + MAX_LEN))
				PARAM_HASH.put(name + MAX_LEN, DEF_MAX_LEN);
		}
		if(!PARAM_HASH.containsKey(name + CUR_LEN))
			PARAM_HASH.put(name + CUR_LEN, 0);
		return instance;
	}

	// 获得默认长度
	private int getDefMaxLen() {
		return getDefMaxLen(null);
	}

	private int getDefMaxLen(String name) {
		if (Util.isNotNull(name)) {
			return PARAM_HASH.get(name + MAX_LEN);
		} else
			return PARAM_HASH.get(DEF_NAME + MAX_LEN);
	}

	// 获得默认时间间隔
	private int getDefInterval(String name) {
		if (Util.isNotNull(name)) {
			return PARAM_HASH.get(name + ITVL_APPENDIX);
		} else
			return PARAM_HASH.get(DEF_NAME + ITVL_APPENDIX);
	}

	public boolean check(String key) {
		return check(DEF_NAME,key);
	}
	
	//返回true，越界
	public boolean check(String name,String key) {
		String name2 = name + key;
		if(!VALUE_HASH.containsKey(name2))
			return false;
		else{
			int interval = VALUE_HASH.get(name2);
			int defInterval = getDefInterval(name);
			int curTimeInsec = DateUtil.getCurTimeInSec();
			if(curTimeInsec-interval<=defInterval)
				return true;
			else
				return false;
		}
	}
	
	public void put(String key, int curTime) {
		put(DEF_NAME,key,curTime);
	}
	
	public void put(String name, String key, int curTime) {
		String name2 = name + key;
		if (!VALUE_HASH.containsKey(name2)) {
			VALUE_HASH.put(name2, curTime);
		} else {
			int maxlen = getDefMaxLen(name);
			name2 = name + CUR_LEN;
			int curlen = PARAM_HASH.get(name2);
			VALUE_HASH.put(name2, curTime);
			if(curlen>=maxlen){
				//清除
				refreshHash(name);
			}
		}
	}
	
	private void refreshHash(String name){
		Enumeration<String> keys = VALUE_HASH.keys();
		String key = null;
		int defInterval = getDefInterval(name);
		while(keys.hasMoreElements()){
			key = keys.nextElement();
			if(!key.startsWith(name))
				continue;
			int curTime = DateUtil.getCurTimeInSec();
			if(curTime-VALUE_HASH.get(key)>defInterval){
				VALUE_HASH.remove(key);
			}
		}
	}
	
	public static void main(String argc[]){
		String TYPE_NAME = "CODE";
		int total = 22;
		ValueHolderUtil.getInstance(TYPE_NAME,1,5000);
		for(int i=0;i<total;i++){
			ValueHolderUtil.getInstance(TYPE_NAME).put(TYPE_NAME,i+"", DateUtil.getCurTimeInSec());
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){}
		}
		
		for(int i=0;i<total;i++){
			System.out.println("[seq:"+i+"]="+ValueHolderUtil.getInstance(TYPE_NAME).check(TYPE_NAME,i+""));
		}	
	}
}
