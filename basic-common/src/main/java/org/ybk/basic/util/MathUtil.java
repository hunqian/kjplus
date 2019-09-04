package org.ybk.basic.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MathUtil {

	//获得对象的int值
	public static int getObjIntVl(Object obj){
		return getObjIntVl(obj, 0);
	}
	
	//获得对象的int值
	public static int getObjIntVl(Object obj, int defVl){
		int t = 0;
		if(obj instanceof BigInteger){
			t = ((BigInteger)obj).intValue();
		}else if(obj instanceof BigDecimal){
			t = ((BigDecimal)obj).intValue();
		}else if(obj instanceof Long){
			t = ((Long)obj).intValue();
		}else if(obj instanceof Integer){
			t = ((Integer)obj).intValue();
		}else
			t = defVl;
		return t;
	}
}
