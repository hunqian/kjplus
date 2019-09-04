package org.ybk.basic.test;

import java.util.Calendar;

public class Misc {
	public static void main(String args[]){
		int arrs[][] = new int[][]{{1,1,1,1,1},{1,1,1,1,1,1},{1,1,2}};
		//int arrs[][] = new int[][]{{1,1,1},{1}};
		//printArrayTc(arrs);
		System.out.println(Calendar.getInstance().get(Calendar.MINUTE));
	}
	
	public static void printArrayTc(int arrs[][]){
		int line = arrs.length;
		int total = 1;
		for(int i=0;i<arrs.length;i++){
			total *= arrs[i].length;
		}
		int curindex = 0;
		int ci = 0;
		for(int i=0;i<total;i++){
			curindex = i;
			for(int j=0;j<line;j++){
				ci = curindex%arrs[j].length;
				System.out.print(ci+",");
				curindex = (curindex-ci)/arrs[j].length;
			}
			System.out.println();
		}
	}
}
