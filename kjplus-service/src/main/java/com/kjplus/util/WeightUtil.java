package com.kjplus.util;

import java.math.BigDecimal;

import org.ybk.basic.util.DateUtil;

public class WeightUtil {

	private static Double wightTime = 0.6;// 时间 值设定
	private static Double wightMsg = 0.5;// 消息权重 值设定
	private static Double currentTime = Double.valueOf(DateUtil.getCurTimeInSec());// 当前时间
	private static final Double M = 100000000D;// 时间唯一除值

	/**
	 * 算法 第一偏度值为 未读消息 其次为时间 BigDecimal.ROUND_HALF_UP:表示的就是4舍5入
	 * 
	 * @param W
	 *            未读消息数
	 * @param S
	 *            发送消息的时间
	 * @return
	 * 
	 */
	public static Double getWeight(double W, double S) {
		boolean isNull = W <= 0.0 || S <= 0.0 ? true : false;
		if (isNull)
			return 0.0;
		// --------------------------------------------权重------------------------------------------------
		Double weight = ((W * wightMsg) + ((S / M) * wightTime ));
		Double Remainder = (weight / 100.00);// 倍数 // 计算百倍数
		while (Remainder > 100) {
			Remainder = Remainder / 100.00;
		}
		// 最后权重
		double lastWeight = new BigDecimal(Remainder).setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(lastWeight);
		// --------------------------------------------时间向量------------------------------------------------
		Double vector = ((currentTime / M) + (S / M));
		while (vector > 100) { 
			vector = vector / 100.00;
		}
		double timeVector = new BigDecimal(vector).setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(timeVector);
		// -----------------------------------------最后权重数----------------------------------------
		double lastVl = lastWeight + timeVector;
		System.out.println(lastVl);
		// -----------------------------------------未读消息数 除以最后综合 权重+向量产生的 值----------------------
		// 得到最近联系人的最终权重 ----------------------------------------
		lastVl = new BigDecimal(lastVl).setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
		// 返回最后权重值
		return lastVl;
	}
}
