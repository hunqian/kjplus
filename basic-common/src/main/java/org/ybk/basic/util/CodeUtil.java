package org.ybk.basic.util;

import java.util.Hashtable;

public class CodeUtil {
	
	public static void testGenCode(){
		Hashtable<String,String> codes = new Hashtable<String,String>();
		String code = null;
		//for(int seq=0;seq<Integer.MAX_VALUE;seq++){
		for(int seq=0;seq<100;seq++){
			code = genCode(seq);
			if(codes.containsKey(code)){
				System.out.println("[重复]"+seq+","+code);
			}else{
				System.out.println("["+seq+"]="+code);
				codes.put(code,"Y");
			}
		}
	}
	
	//产生编码，0-9,A-Z,一共36个，先长度为1的，然后长度长度为2,长度依次增长
	public static String genCode(int seq){
		//先最后一位
		String code = genDigiCode(seq%36);
		while(seq/36>0){
			seq = seq/36-1;
			code = genDigiCode(seq%36) + code;
		}
		return code;
	}
	
	public static String genLetterCode(int seq){
		//先最后一位
		String code = String.valueOf((char)('A'+seq%26));
		while(seq/26>0){
			seq = seq/26-1;
			code = String.valueOf((char)('A'+seq%26)) + code;
		}
		return code;
	}
	
	//产生一位编码
	private static String genDigiCode(int seq){
		char c = '0';
		if(seq<26){
			c = (char)(65+seq);
		}else if(seq<36){
			c = (char)(48+seq-26);
		}
		return String.valueOf(c);
	}
			
	public static void main(String argcs[]){
		testGenCode();
	}
}
