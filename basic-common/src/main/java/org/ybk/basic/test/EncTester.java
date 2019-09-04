package org.ybk.basic.test;

import java.net.URLDecoder;

public class EncTester {
	
	public static void main(String args[]) {
		
		String elename = "众人";
		String[] splits = elename.split("[\\[\\(]");
		for(String s:splits)
			System.out.println(s);
		
		/*String coding = "gbk";
		if(args.length == 0){
			System.out.println("调用：EncTester -coding gbk -str %C9%ED");
			System.exit(0);
		}
		String v = null;
		String str = null;
		for(int i=0;i<args.length;i++){
			if("-coding".equalsIgnoreCase(args[i]))
				coding = args[i+1];
			else if("-str".equalsIgnoreCase(args[i]))
				str = args[i+1];
		}
		
		if(str == null || str.length() == 0){
			System.out.println("[err]空转换字符");
			System.exit(0);
		}
		try{
			String str2 = URLDecoder.decode(str, coding);
			System.out.println(str+"->"+str2);
		}catch(Exception e){}
*/		
		
	}
}
