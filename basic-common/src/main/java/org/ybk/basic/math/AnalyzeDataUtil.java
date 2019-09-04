package org.ybk.basic.math;

import java.util.*;

public class AnalyzeDataUtil {

	// 获得最热门品种
	public static List<String> getTopHotProds(List<String> refs, int topn) {
		String code = null;
		int maxNum = -1;
		int curNum = 0;
		Hashtable<String, Integer> statics = new Hashtable<String, Integer>();
		for (int i = 0; i < refs.size(); i++) {
			code = (String) refs.get(i);
			if (!statics.containsKey(code)) {
				statics.put(code, 1);
			} else {
				curNum = statics.get(code);
				statics.put(code, curNum + 1);
				if (curNum > maxNum)
					maxNum = curNum;
			}
		}
		List<String> hotProds = new ArrayList<String>();
		while (statics.size() > 0 && hotProds.size() < topn && maxNum > 0) {
			Enumeration<String> keys = statics.keys();
			while (keys.hasMoreElements()) {
				code = keys.nextElement().toString();
				if (statics.get(code) == maxNum) {
					hotProds.add(code);
					// 打印
					// System.out.println("["+code+"]"+maxNum);
					statics.remove(code);
					if (hotProds.size() >= topn)
						break;
				}
			}
			maxNum--;
		}
		return hotProds;
	}

}
