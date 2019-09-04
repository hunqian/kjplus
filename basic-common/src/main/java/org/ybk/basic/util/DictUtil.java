package org.ybk.basic.util;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.ybk.basic.file.ReadFromFile;
import org.ybk.basic.http.HttpUtil2;

import com.alibaba.fastjson.*;

/**
 * dict词典数据工具类
 * 
 * @author rumble
 * @date 2013-10-8
 * @version 1.0
 */
public class DictUtil {

	private static final String BAIDU_COOKIE = "BAIDUID=BC1522E4EF56D7DEFC73FEC96AD3AC19:FG=1; BIDUPSID=BC1522E4EF56D7DEFC73FEC96AD3AC19; PSTM=1491399555; BDUSS=zJxQ2N6OVBXNVRKem1kbjcyZnoyeno0eWpLZTZNalpxVC1GSTVCalBzeEJUUjFaSVFBQUFBJCQAAAAAAAAAAAEAAACSUKcUcnVtYmxlMzIzAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEHA9VhBwPVYT; cflag=15%3A3; locale=zh; to_lang_often=%5B%7B%22value%22%3A%22zh%22%2C%22text%22%3A%22%u4E2D%u6587%22%7D%2C%7B%22value%22%3A%22en%22%2C%22text%22%3A%22%u82F1%u8BED%22%7D%5D; REALTIME_TRANS_SWITCH=1; FANYI_WORD_SWITCH=1; HISTORY_SWITCH=1; SOUND_SPD_SWITCH=1; SOUND_PREFER_SWITCH=1; H_PS_PSSID=22583_1462_21125_20928; Hm_lvt_64ecd82404c51e03dc91cb9e8c025574=1492532194,1492563451; Hm_lpvt_64ecd82404c51e03dc91cb9e8c025574=1492563451; hasSeenTips=1; from_lang_often=%5B%7B%22value%22%3A%22en%22%2C%22text%22%3A%22%u82F1%u8BED%22%7D%2C%7B%22value%22%3A%22zh%22%2C%22text%22%3A%22%u4E2D%u6587%22%7D%5D";

	//已经处理的关键字
	private static Hashtable<String, String> PROCED_KEYWORDS = new Hashtable<String, String>();
	
	private static final Object TERM_PRIV[][] = new Object[][]{
		{"it",10000},{"is",10000},{"so",10000},{"are",10000},{"were",10000},{"was",10000}
		,{"to",10000},{"be",10000},{"being",10000},{"been",10000},{"was",10000},{"was",10000}
		,{"do",10000},{"did",10000},{"does",10000},{"the",10000},{"this",10000},{"that",10000}
		,{"shi",8000},{"she",8000},{"he",8000},{"his",8000},{"i",8000},{"me",8000},{"my",8000},{"us",8000},{"we",8000}
		,{"which",6000},{"who",6000},{"what",6000},{"why",6000},{"where",6000},{"whether",6000}
		,{"how",6000},{"sorry",6000},{"may",6000},{"must",6000},{"should",6000}
		,{"a",10},{"b",10},{"c",10},{"d",10},{"e",10},{"f",10},{"g",10},{"h",10},{"j",10},{"k",10},{"l",10},{"m",10}
		,{"n",10},{"o",10},{"p",10},{"q",10},{"r",10},{"s",10},{"t",10},{"u",10},{"v",10},{"w",10},{"x",10},{"y",10},{"z",10}
	}; 
	
	//获得相关的百度字典
	public static List<String> fetchBaiduWords(String word) {

		List<String> words = new ArrayList<String>();
		if(Util.isNull(word))
			return words;
		Hashtable<String, String> wordHash = new Hashtable<String, String>();
		Map<String, String> params = new HashMap<String, String>();
		String url = "http://fanyi.baidu.com/sug";
		String host = "fanyi.baidu.com";
		String encoding = "UTF-8";
		String referer = "http://fanyi.baidu.com/";
		JSONObject jsonObj = null;
		JSONArray jsonDatas = null;
		String kw = null;
		String kv = null;
		List<String> kwList = null;
		String kwRes = null;
		for(int i=1; i<word.length(); i++){
			kw = word.substring(0,i);
			if(PROCED_KEYWORDS.containsKey(kw))
				continue;
			params.put("kw", kw);
			String respJson = HttpUtil2.doPost(url, host, encoding, params, referer, BAIDU_COOKIE);
			if(Util.isNull(respJson))
				continue;
			jsonObj = JSONObject.parseObject(respJson);
			if(jsonObj == null)
				continue;
			PROCED_KEYWORDS.put(kw, "Y");
			jsonDatas = jsonObj.getJSONArray("data");
			for(int j=0;j <jsonDatas.size(); j++){
				kw = jsonDatas.getJSONObject(j).getString("k");
				kv = jsonDatas.getJSONObject(j).getString("v");
				if(kv.contains("abbr."))
					continue;
				kwRes = Util.getRexStr(kv, "^[a-z]{1,}[\\.]");
				if(Util.isNull(kwRes))
					continue;
				kwList = Util.str2Array(kw," ");
				if(kwList.size()>1)
					continue;
				if(!wordHash.containsKey(kw)){
					wordHash.put(kw, "Y");
					words.add(kw);
				}
			}
		}
		return words;
	}
	
	public static int fetchEngDict(String file){
	    List<String> lines = ReadFromFile.readFileAllLines(file,"GBK");
	    Hashtable<String, String> lineHash = new Hashtable<String, String>();
	    String line = null;
	    
	    int newWord = 0;
	    try{
		    
	    	for(int i=0; i<lines.size(); i++){
		    	lineHash.put(lines.get(i), "Y");
		    }
	    	
	    	for(int i=0; i<lines.size(); i++){
	    	//for(int i=0; i<10; i++){
	    		line = lines.get(i);
	    		System.out.println("["+(i+1)+"]处理=" + line);
		    	List<String> words = fetchBaiduWords(line);
		    	for(String wd: words){
		    		if(lineHash.containsKey(wd))
		    			continue;
		    		if(wd.contains("..."))
		    			continue;
		    		lines.add(wd);
		    		lineHash.put(wd, "Y");
		    		newWord++;
		    		FileUtil.appendText(file, wd+"\r\n");
		    	}
		    	Thread.sleep(10);
		    }
	    }catch(Exception ioe){
	    	ioe.printStackTrace();
	    }
	    return newWord;
	}
	
	public static void transferDic(String file, String destFile) {
		Hashtable<String, Integer> TERM_HASH = new Hashtable<String, Integer>(); 
		for(int i=0; i<TERM_PRIV.length; i++){
			TERM_HASH.put((String)TERM_PRIV[i][0], (Integer)TERM_PRIV[i][1]);
		}
		
	    List<String> lines = ReadFromFile.readFileAllLines(file,"GBK");
	    try{
	    	File f = new File(destFile);
			if(f.exists())
				f.delete();
		    for(String line: lines){
		    	if(TERM_HASH.containsKey(line.toLowerCase()))
		    		FileUtil.appendText(destFile, line+" "+TERM_HASH.get(line.toLowerCase())+" m\r\n");
		    	else
		    		FileUtil.appendText(destFile, line+" 100 m\r\n");
		    }
	    }catch(IOException ioe){
	    	ioe.printStackTrace();
	    }
	}
	
	//去重
	public static void dedupDic(String file, String destFile) {
		Hashtable<String, String> DUP_HASH = new Hashtable<String, String>(); 		
	    List<String> lines = ReadFromFile.readFileAllLines(file,"GBK");
	    //List<String> newlines = new ArrayList<String>(); 
	    try{
	    	File f = new File(destFile);
			if(f.exists())
				f.delete();
		    for(String line: lines){
		    	line = line.toLowerCase();
		    	if(line.contains(" "))
		    		continue;
		    	if(DUP_HASH.containsKey(line))
		    		continue;
		    	//newlines.add(line);
		    	DUP_HASH.put(line, "Y");
		    	FileUtil.appendText(destFile, line+"\r\n");
		    }
	    }catch(IOException ioe){
	    	ioe.printStackTrace();
	    }
	}	
	
	public static void main(String[] args) {
		/*List<String> words = fetchBaiduWords("Accept");
		System.out.println(words);
		
		words = fetchBaiduWords("Accept");
		System.out.println(words);*/
		
		String file = "E:/Workspace-15/kejun-admin/src/main/dict/dict_eng.txt";
		
		/*String file = "E:/Workspace-15/kejun-admin/src/main/dict/dict_eng.txt";
		int newWord = 1;
		int round = 1;
		while(newWord>0){
			System.out.println("[处理]轮次=" + round++);
			newWord = fetchEngDict(file);
		}*/
		
		
	    //String destFile = "E:/Workspace-15/kejun-admin/src/main/dict/dict_eng_ddp.txt";
		//dedupDic(file, destFile);
		
		//String file = "E:/docs/题库大王/技术/dict_eng.txt";
	    String destFile = "E:/docs/题库大王/技术/dict_eng_jieba.txt";
	    transferDic(file, destFile);
	}
	
}
