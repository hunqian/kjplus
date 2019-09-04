package org.ybk.basic.util;

import java.io.IOException;
import sun.misc.*;

public class Base64 {
	public static String getEncodedText(byte[] bytes) {

		try {
			MyBase64Encoder encoder = new MyBase64Encoder();
			String text = encoder.encode(bytes);
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static byte[] decode(String src) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return decoder.decodeBuffer(src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		try {
			// String s = "我的汉子会发发发发发发发长的汉字正淡淡的淡淡不够高广告广告广告唱歌尴尬的发";
			String s = "Welcome to DateServer v ";
			// msg = Base64.getEncodedText(s.getBytes("GBK"));

			byte[] bytes = s.getBytes("UTF-8");
			String encode = Base64.getEncodedText(bytes);
			System.out.println("[转换后]" + encode);

			byte[] dbytes = Base64.decode(encode);
			/*
			 * for (int i = 0; i < bytes.length; i++) {
			 * System.out.println(dbytes[i]); }
			 */
			System.out.println("[转换]" + new String(dbytes));
		} catch (Exception e) {
		}
	}
}
