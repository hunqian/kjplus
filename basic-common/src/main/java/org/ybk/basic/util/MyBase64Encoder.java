package org.ybk.basic.util;

public class MyBase64Encoder {
	private static char[] codec_table = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
		'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
		'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
		'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
		'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
		'7', '8', '9', '+', '/' };

public MyBase64Encoder() {
}

public static String encode(byte[] a) {
	int totalBits = a.length * 8;
	int nn = totalBits % 6;
	int curPos = 0;// process bits
	StringBuffer toReturn = new StringBuffer();
	while (curPos < totalBits) {
		int bytePos = curPos / 8;
		switch (curPos % 8) {
		case 0:
			toReturn.append(codec_table[(a[bytePos] & 0xfc) >> 2]);
			break;
		case 2:
			toReturn.append(codec_table[(a[bytePos] & 0x3f)]);
			break;
		case 4:
			if (bytePos == a.length - 1) {
				toReturn.append(codec_table[((a[bytePos] & 0x0f) << 2) & 0x3f]);
			} else {
				int pos = (((a[bytePos] & 0x0f) << 2) | ((a[bytePos + 1] & 0xc0) >> 6)) & 0x3f;
				toReturn.append(codec_table[pos]);
			}
			break;
		case 6:
			if (bytePos == a.length - 1) {
				toReturn.append(codec_table[((a[bytePos] & 0x03) << 4) & 0x3f]);
			} else {
				int pos = (((a[bytePos] & 0x03) << 4) | ((a[bytePos + 1] & 0xf0) >> 4)) & 0x3f;
				toReturn.append(codec_table[pos]);
			}
			break;
		default:
			// never hanppen
			break;
		}
		curPos += 6;
	}
	if (nn == 2) {
		toReturn.append("==");
	} else if (nn == 4) {
		toReturn.append("=");
	}
	return toReturn.toString();
}

public static void main(String[] args) throws Exception {

	MyBase64Encoder encoder = new MyBase64Encoder();
	sun.misc.BASE64Encoder sunEncoder = new sun.misc.BASE64Encoder();
	String teststr = "这是test字符串中English混合!！您现在的位置：.Net中文社区>> .Net编程>>正文内容 C#中任意类型数据转成JSON格式数据输出将对象序列化为 JavaScript 对象表示法 (JSON)，并将 JSON 数据反序列化为对象。 此类不能继承。";
	byte[] testBytes = teststr.getBytes("GBK");
	//byte[] testBytes = new byte[1024 * 1024 * 2];
	long start = System.currentTimeMillis();
	for (int i = 0; i < 1000; i++) {
		sunEncoder.encode(testBytes);
	}

	long end = System.currentTimeMillis();
	System.out.println("[sun encoder]use time :" + (System.currentTimeMillis() - start));
	start = System.currentTimeMillis();
	for (int i = 0; i < 1000; i++) {
		String s = encoder.encode(testBytes);
		//System.out.println(s);
	}
	System.out.println("[our encoder]use time :" + (System.currentTimeMillis() - start));
	
	//BASE64Encoder encoder = new BASE64Encoder();
	teststr = "这是test字符串中English混合!！";
	byte[] bytes = teststr.getBytes("GBK");
	String encodeStr = MyBase64Encoder.encode(bytes);
	
	//sun.misc.BASE64Decoder sunDecoder = new sun.misc.BASE64Decoder();
	//bytes = sunDecoder.decodeBuffer(encodeStr);
	bytes = Base64.decode(encodeStr);
	String teststr2 = new String(bytes,"GBK");
	System.out.println(teststr2);

}

}
