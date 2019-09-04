package com.kjplus.util;

import java.util.UUID;

import org.ybk.basic.util.Util;

//自用的字符处理方法
public class MStringUtil {

	public static String trimStr(String str) {
		if (Util.isNull(str))
			return "";
		else
			return str;
	}

	// 对json字符串的属性值的第一个字符转换成大写
	public static String upcapJsonStr(String str) {
		if (Util.isNull(str))
			return str;
		String fieldRex = "\"[a-zA-z_\\.]{1,}\":";
		String rexRes = Util.getRexStr(str, fieldRex);
		int pos = 0;
		StringBuffer newStr = new StringBuffer();
		while (Util.isNotNull(rexRes)) {
			pos = str.indexOf(rexRes);
			newStr.append(str.substring(0, pos));
			rexRes = rexRes.substring(0, 2).toUpperCase() + rexRes.substring(2);
			newStr.append(rexRes);
			str = str.substring(pos + rexRes.length());
			rexRes = Util.getRexStr(str, fieldRex);
		}
		newStr.append(str);
		return newStr.toString();
	}

	// 获取uuid
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String res = String.valueOf(uuid);
		res = res.replaceAll("\\-", "");
		return res;
	}

}
