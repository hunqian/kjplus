package org.ybk.basic.math;

import java.math.BigDecimal;

//数学工具
public class MathUtil {
	
	//四舍五入
	public static double convert(double value) {
		long l1 = Math.round(value * 100); // 四舍五入
		double ret = l1 / 100.0; // 注意：使用 100.0 而不是 100
		return ret;
	}
	
	//四舍五入,dotNum小数点后位数
	public static double convertDouble(double value,int dotNum) {
		double factor = 1.0;
		for(int i=0;i<dotNum;i++)
			factor *= 10.0;
		long l1 = Math.round(value * factor); // 四舍五入
		double ret = l1 / factor; // 注意：使用 100.0 而不是 100
		return ret;
	}

	//四舍五入,dotNum小数点后位数
	public static float convertFloat(float value,int dotNum) {
		float factor = (float)1.0;
		for(int i=0;i<dotNum;i++)
			factor *= 10.0;
		long l1 = Math.round(value * factor); // 四舍五入
		float ret = l1 / factor; // 注意：使用 100.0 而不是 100
		return ret;
	}
	
	public static String conv2Plain(String str) {
		BigDecimal bigDecimal = new BigDecimal(str);
		String result = bigDecimal.toPlainString();
		// System.out.println(result);
		return result;
	}
	
	public static void main(String arc[]){
		double v = 39.969;
		System.out.println(convertDouble(1.2345,2));
	}
}
