package org.ybk.basic.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * <p>Title: 提供一些常用的操作字符串的函数</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company:  </p>
 * @author
 * @version 
 */

public final class StringUtils {
	public static final String EMPTY_STRING = "";
	public static final String COMMA = ",";
	public static final char DOT = '.';
	public final static char DELIMITER = ';';


	public static String doNull(String str) {
		return str == null ? "" : str;
	}

	public static String deleteWhitespace(String str) {
		if (str == null) {
			return null;
		}
		int sz = str.length();
		StringBuffer buffer = new StringBuffer(sz);
		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				buffer.append(str.charAt(i));
			}
		}
		return buffer.toString();
	}

	/**
	 * 用新字符串<param>newString</param>替换指定字符串<param>line</param>
	 * 中的所有旧字符串<param>oldString</param>
	 * @param line 执行替换操作的字符串
	 * @param oldString 将被替换掉的旧字符串
	 * @param newString 替换后的新字符串
	 * @return 返回执行替换操作后的字符串
	 */
	public static final String replace(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	public static String replaceOnce(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = line.indexOf(oldString);
		if (i < 0) {
			return line;
		}
		else {
			StringBuffer buf = new StringBuffer(line.substring(0, i));
			buf.append(newString);
			buf.append(line.substring(i + oldString.length()));
			return buf.toString();
		}
	}

	/**
	 * 用新字符串<param>newString</param>替换指定字符串<param>line</param>
	 * 中的所有旧字符串<param>oldString</param><P>
	 * 但在匹配旧字符串时并不区分大小写
	 * @param line 执行替换操作的字符串
	 * @param oldString 将被替换掉的旧字符串
	 * @param newString 替换后的新字符串
	 * @return 返回执行替换操作后的字符串
	 */
	public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 用新字符串<param>newString</param>替换指定字符串<param>line</param>
	 * 中的所有旧字符串<param>oldString</param><P>并返回替换的次数
	 * @param line 执行替换操作的字符串
	 * @param oldString 将被替换掉的旧字符串
	 * @param newString 替换后的新字符串
	 * @param count 替换的次数
	 * @return 返回执行替换操作后的字符串
	 */
	public static final String replace(String line, String oldString,
			String newString, int[] count) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			int counter = 0;
			counter++;
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		}
		return line;
	}

	/**
	 * 判断字符串是否有内容。有内容意味着字符串不能为空且长度不为零。
	 * @param s 需要判断的字符串
	 * @return 有内容则返回<code>true</code>，否则返回<code>false</code>
	 */
	public static final boolean hasContent(String s) {
		return (s != null) && (s.trim().length() > 0);
	}

	/**
	 * 将一个字符串从某位置开始以某字符作为分隔符进行分隔(得到每段作为字符串的字符串数组).
	 * String list[] = StringUtils.splitString("AAAA,BBBB,CCCC,DDDDD",0,',')
	 *   list 为  { "AAAA","BBBB","CCCC","DDDD" }
	 * @param  str  被分隔的字符串
	 * @param  istart 开始位置
	 * @param  delimiter  分隔符
	 * @return  分隔的结果
	 */
	public static final String[] splitString(String str, int istart,
			char delimiter) {
		if (str == null) {
			return null;
		}
		int sl = str.length();
		int n = 0;
		for (int i = istart; i < sl; i++) {
			if (str.charAt(i) == delimiter) {
				n++;
			}
		}
		String[] sa = new String[n + 1];
		int i = istart, j = 0;
		for (; i < sl; ) {
			int iend = str.indexOf(delimiter, i);
			if (iend < 0) {
				break;
			}
			sa[j++] = str.substring(i, iend);
			i = iend + 1;
		}
		sa[j++] = str.substring(i);
		return sa;
	}

	public static final String[] splitString(String str) {
		return splitString(str, DELIMITER);
	}

	/**
	 * 将一个字符串以某字符作为分隔符进行分隔(得到每段作为字符串的字符串数组).
	 * @param  str  被分隔的字符串
	 * @param  delimiter  分隔符
	 * @return  分隔的结果
	 */
	public static final String[] splitString(String str, char delimiter) {
		return splitString(str, 0, delimiter);
	}

	/**
	 * 从数组中连接一个具有分隔符的字符串
	 * @param array 数组
	 * @param delim 分隔符
	 * @return 连接后的字符串
	 */
	public static String join(Object array, String delim) {
		Class c = array.getClass();
		if (!c.isArray()) {
			return array.toString();
		}
		//      Class componentType = c.getComponentType();
		//      int length = Array.getLength(array);
		String s = "";
		for (int i = 0; i < Array.getLength(array); i++) {
			if (i > 0) {
				s += delim;
			}
			Object o = Array.get(array, i);
			s += o;
		}
		return s;
	}

	public static String join(List list, String delim) {
		if (list == null || list.size() < 1) {
			return null;
		}
		return join(list.toArray(), delim);
	}

	/**
	 * 根据不可逆MD5算法，产生一加密值
	 * @param data 要加密的字符串
	 * @return 加密后的字符串
	 */
	public synchronized static final String md5(String data) {
		return md5(data, true);
	}

	public static String md5(String data, boolean zero) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return null;
		}
		byte[] resultByte = messageDigest.digest(data.getBytes());
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < resultByte.length; ++i) {
			int v = 0xFF & resultByte[i];
			if (v < 16 && zero)
				result.append("0");
			result.append(Integer.toHexString(v));
		}
		return result.toString();
	}


	/**
	 * 转化字节数组到十六位字符串。<br>
	 * 反向操作请参考{@link #toByteArray}
	 * @param res 字节数组
	 * @return 十六位字符串
	 */
	public static String toHexString(byte[] res) {
		if (res == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer(res.length << 1);
		for (int i = 0; i < res.length; i++) {
			String digit = Integer.toHexString(0xFF & res[i]);
			if (digit.length() == 1) {
				digit = '0' + digit;
			}
			sb.append(digit);
		}
		return sb.toString().toUpperCase();
	}

	public static byte[] toByteArray(String hexString) {
		if (hexString == null) {
			return null;
		}
		int arrLength = hexString.length() >> 1;
		byte buff[] = new byte[arrLength];
		for (int i = 0; i < arrLength; i++) {
			int index = i << 1;
			String digit = hexString.substring(index, index + 2);
			buff[i] = (byte) Integer.parseInt(digit, 16);
		}
		return buff;
	}

	public static StringBuffer getStringBuffer(Reader reader) throws IOException {
		StringBuffer buf = null;
		if (reader != null) {
			buf = new StringBuffer();
			BufferedReader bReader = new BufferedReader(reader);
			String str = bReader.readLine();
			if (str != null) {
				buf.append(str);
				while ((str = bReader.readLine()) != null) {
					buf.append('\n');
					buf.append(str);
				}
			}
		}
		return buf;
	}

	public static String getString(Reader reader) throws IOException {
		StringBuffer buf = getStringBuffer(reader);
		if (buf == null) {
			return null;
		}
		else {
			return buf.toString();
		}
	}

	public static String trimQuotes(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		char chars[] = str.toCharArray();
		int l = 0;
		int r = chars.length - 1;
		if (chars[l] == '"' || chars[l] == '\'' && chars[l] == chars[r]) {
			l++;
			r--;
			return new String(chars, l, r);
		}
		else {
			return str;
		}
	}

	public static String convertNewlines(String input) {
		if (input == null) {
			return null;
		}
		input = replace(input, "\r\n", "\n");
		input = replace(input, "\n", "<BR>");
		return input;
	}

	public static String replaceNewlines(String input) {
		if (input == null) {
			return null;
		}
		input = replace(input, "\r\n\t", "");
		return input;
	}

	public static String escapeHTML(String string) {
		return null;
	}

	public static String javaMethod(String input) {
		boolean skipLine = false, skipPara = false, readyForSL = false,
		readyForSP = false;
		StringBuffer tokenbuf = new StringBuffer();
		String methodName = null;
		Vector tokens = new Vector();
		char c;
		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			if (skipLine || skipPara) {
				if (skipLine) {
					if (c == '\n') {
						skipLine = false;
					}
				}
				if (skipPara) {
					if (c == '*') {
						if (i < input.length() - 1 && input.charAt(i + 1) == '/') {
							skipPara = false;
						}
						i++;
					}
				}
				continue;
			}
			if (readyForSL || readyForSP) {
				if (readyForSL) {
					if (c == '/') {
						readyForSL = false;
						skipLine = true;
						readyForSP = false;
						continue;
					}
					readyForSL = false;
				}
				if (readyForSP) {
					if (c == '*') {
						readyForSP = false;
						skipPara = true;
						readyForSL = false;
						continue;
					}
					readyForSP = false;
				}
			}
			if (c == '/') {
				readyForSL = true;
				readyForSP = true;
				continue;
			}
			if (c == '\n' || c == '\t' || c == '\r' || c == ' ' || c == '\f') {
				if (tokenbuf.length() != 0) {
					String token = tokenbuf.toString();
					if (token.indexOf('(') != -1) {
						methodName = token;
						break;
					}
					else {
						tokens.add(token);
						tokenbuf.setLength(0);
					}
				}
			}
			else {
				tokenbuf.append(c);
			}
		}
		if (methodName != null && methodName.length() != 0) {
			methodName = methodName.substring(0, methodName.indexOf('('));
		}
		return methodName;
	}

	public static boolean isEmail(String s) {
		if (StringUtils.hasContent(s)) {
			int i = 1;
			int sLength = s.length();
			while ((i < sLength) && (s.charAt(i) != '@')) {
				i++;
			}
			if ((i >= sLength - 1) || (s.charAt(i) != '@')) {
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
	public  static Timestamp StringtoTimestamp(String strtime){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =new Date();
		try {
			date = df.parse(strtime);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		String time = df.format(date);
		Timestamp ts = Timestamp.valueOf(time);
		return ts;
	}
	
	//获得默认返回值
	public static String getVl(String value){
		return getVl(value,"");
	}
	
	public static String getVl(String value,String defValue){
		if(Util.isNull(value))
			return defValue;
		else
			return value;
	}
	
	public  static String DateToString(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date); 
	}
	
	//根据tag进行解析
	public static String composeLines(List<String> contents,String tag){
		if(contents == null || contents.size() ==0)
			return "";
		StringBuffer buf = new StringBuffer();
		buf.append(contents.get(0));
		for(int i=1;i<contents.size();i++){
			buf.append(tag);
			buf.append(contents.get(i));
		}
		return buf.toString();
	}
	
	//根据tag进行解析
	public static List<String> parseLines(String content,String tag){
		List<String> lines = new ArrayList<String>();
		if(Util.isNull(content))
			return lines;
		content = content.trim();
		int p = 0;
		String line = null;
		while(p>-1){
			p = content.indexOf(tag);
			if(p>-1){
				line = content.substring(0,p);
				content = content.substring(p+tag.length());
			}else{
				line = content;
			}
			lines.add(line);
		}
		return lines;
	}
}
