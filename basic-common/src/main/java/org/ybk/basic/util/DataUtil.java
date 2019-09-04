package org.ybk.basic.util;

import java.util.ArrayList;
import java.util.List;

import com.ybk.bean.AvgPrice;

public class DataUtil {
	
	//list中的AvgPrice对象列表是按照时间进行顺序排序的，本方法就是按照时间天的顺序
	//对缺少的天数以默认值进行填充
	public static List<AvgPrice> paddingDataList(List list,int frmday,int today,double defValue){
		//填充
		List<AvgPrice> plist = new ArrayList<AvgPrice>();
		AvgPrice ap = null;
		if(list.size()==0){
			for(int i=0;i<frmday;i++){
				plist.add(new AvgPrice(Util.currDateOffset(frmday-i),defValue));
			}
		}else{
			int dayPointer = -1;
			ap = (AvgPrice)list.get(0);
			int frmday0 = Util.getDayOff(ap.getD())-1;
			//前置填充
			if(frmday0<frmday){
				for(int i=frmday;i>frmday0;i--){
					plist.add(new AvgPrice(Util.currDateOffset(0-i),defValue));
					dayPointer = i;
				}
			}
			if(dayPointer==-1){
				ap = (AvgPrice)list.get(0);
				dayPointer = Util.getDayOff(ap.getD())-1;
			}else
				dayPointer--;
			for(int i=0;i<list.size();){
				ap = (AvgPrice)list.get(i);
				frmday0 = Util.getDayOff(ap.getD())-1;
				if(dayPointer == frmday0){
					//增加
					plist.add(ap);
					i++;
					dayPointer--;
					continue;
				}else{
					int padNum = dayPointer-frmday0;
					for(int j=0;j<padNum;j++){
						//dayPointer--;
						plist.add(new AvgPrice(Util.currDateOffset(0-dayPointer--),defValue));
					}
				}
			}
			//判断尾巴是否填充
			ap = (AvgPrice)plist.get(plist.size()-1);
			int today0 = Util.getDayOff(ap.getD())-1;
			if(today0>today){
				for(int i=today0-1;i>=today;i--){
					plist.add(new AvgPrice(Util.currDateOffset(0-i),defValue));
				}
			}
		}
		return plist;
	}
	
	private static void traverseList(List<AvgPrice> plist){
		for(AvgPrice ap:plist){
			System.out.println(ap.getD()+","+ap.getP());
		}
	}
	
	
	
	
	public static void main(String args[]){
		List<AvgPrice> list = new ArrayList<AvgPrice>();
		int frmday = 10;
		int today = 2;
		//只有头和尾巴
		list.add(new AvgPrice(Util.currDateOffset(0-frmday),10));
		list.add(new AvgPrice(Util.currDateOffset(-8),18));
		list.add(new AvgPrice(Util.currDateOffset(-6),15));
		list.add(new AvgPrice(Util.currDateOffset(-5),17));
		list.add(new AvgPrice(Util.currDateOffset(0-today),20));
		List<AvgPrice> plist = paddingDataList(list,frmday,3,1);
		traverseList(plist);
	}
}
