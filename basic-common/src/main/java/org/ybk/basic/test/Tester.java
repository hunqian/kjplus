package org.ybk.basic.test;

import org.apache.commons.lang.StringEscapeUtils;

public class Tester {
	
	public static void main(String args[]) {
		long curtime = System.currentTimeMillis();
		System.out.println(curtime/1000);
		System.out.println(curtime/1000+3*24*60*60);
		String s = StringEscapeUtils.unescapeHtml("<p>&nbsp;</p>");
		System.out.println(s);
		String s2 = StringEscapeUtils.escapeHtml("<a>\"</a>");
		System.out.println(s2);
	}

}
