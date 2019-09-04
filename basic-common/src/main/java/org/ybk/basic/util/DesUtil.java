package org.ybk.basic.util;

import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加密工具类
 * 
 * @author 宋陆
 * @date 2013-10-8
 * @version 1.0
 */
public class DesUtil {

	private static final String KEY = "ybk007.so";

	public static String encrypt(String strDataToEncrypt) {
		byte[] key = KEY.getBytes();

		Provider sunJCE = new com.sun.crypto.provider.SunJCE();
		Security.addProvider(sunJCE);

		String strAlgorithm = "DES";
		SecretKeySpec keySpec = null;
		DESKeySpec deskey = null;
		String strResult = "";

		try {
			deskey = new DESKeySpec(key);
			keySpec = new SecretKeySpec(deskey.getKey(), "DES");
			Cipher cipher = Cipher.getInstance(strAlgorithm);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			byte[] utf8 = strDataToEncrypt.getBytes("UTF8");
			byte[] enc = cipher.doFinal(utf8);
			strResult = new sun.misc.BASE64Encoder().encode(enc);
			//strResult = Base64.getEncodedText(enc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strResult;
	}

	/**
	 * This function decrypt a given string using the DES algorithm.
	 * 
	 * @param strDataToDecrypt
	 *            The String to decrypt
	 * @param strKey
	 *            The generated key used to decrypt
	 * @return The encrypted string
	 */
	public static String decrypt(String strDataToDecrypt) {
		byte[] key = KEY.getBytes();
		Provider sunJCE = new com.sun.crypto.provider.SunJCE();
		Security.addProvider(sunJCE);

		String strAlgorithm = "DES";
		SecretKeySpec keySpec = null;
		DESKeySpec deskey = null;
		String strResult = "";

		try {
			deskey = new DESKeySpec(key);
			keySpec = new SecretKeySpec(deskey.getKey(), "DES");

			Cipher cipher = Cipher.getInstance(strAlgorithm);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);

			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(strDataToDecrypt);
			byte[] utf8 = cipher.doFinal(dec);
			return new String(utf8, "UTF8");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return strResult;
	}
	
	public static void main(String[] args) {
		try{
			String text = "123456";
			String encryptText = encrypt(text); 
			System.out.println(encryptText);
			System.out.println(decrypt(encryptText));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
