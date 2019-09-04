package org.ybk.basic.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//工具类
public class MsgDigestUtil {
	//private static Logger logger = Logger.getLogger(MsgDigestUtil.class);
	
	//SHA-1算法
	public static final String ALGO_SHA1 = "SHA-1";

	//SHA-256算法
	public static final String ALGO_SHA256 = "SHA-256";

	//SHA-512算法
	public static final String ALGO_SHA512 = "SHA-512";

	//MD2算法
	public static final String ALGO_MD2 = "MD2";

	//MD5算法
	public static final String ALGO_MD5 = "MD5";

	public static String md5(String source) {
		StringBuffer sb = new StringBuffer(32);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(source.getBytes("utf-8"));
			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
		} catch (Exception e) {
			//logger.error("Can not encode the string '" + source + "' to MD5!",e);
			return null;
		}
		return sb.toString();
	}

	/**
	 * encrypted password based on JCA algorithm of message digest
	 * 
	 * @param plainText
	 *            orginal password text
	 * @param algorithm
	 *            name of algorithm
	 * @return encrypted password
	 */
	public static String encrypte(String plainText, String algorithm) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(plainText.getBytes());
		byte[] b = md.digest();
		StringBuilder output = new StringBuilder(32);
		for (int i = 0; i < b.length; i++) {
			String temp = Integer.toHexString(b[i] & 0xff);
			if (temp.length() < 2) {
				output.append("0");
			}
			output.append(temp);
		}
		return output.toString();
	}

	public static void main(String[] args) {
		String source = "pass";
		/*String digest5 = MsgDigestUtil.md5(source);
		String digest52 = MsgDigestUtil.encrypte(source,MsgDigestUtil.ALGO_MD5);
		System.out.println(digest5);
		System.out.println(digest52);*/
		System.out.println("MD5:"+MsgDigestUtil.encrypte(source,MsgDigestUtil.ALGO_MD5));
		System.out.println("MD2:"+MsgDigestUtil.encrypte(source,MsgDigestUtil.ALGO_MD2));
		System.out.println("SHA1:"+MsgDigestUtil.encrypte(source,MsgDigestUtil.ALGO_SHA1));
		System.out.println("SHA256:"+MsgDigestUtil.encrypte(source,MsgDigestUtil.ALGO_SHA256));
		System.out.println("SHA512:"+MsgDigestUtil.encrypte(source,MsgDigestUtil.ALGO_SHA512));
	}
}
