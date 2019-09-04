package org.ybk.basic.test;

import java.io.*;
import java.util.*;

public class PropUtil implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1521530818443272651L;

	private Properties prop;
	private FileInputStream is;

	/**
	 * 
	 * 构造函数
	 * 
	 * @param filename
	 * 
	 * */

	public PropUtil(String filename) {
		prop = new Properties();
		try {
			is = new FileInputStream(filename);
			prop.load(is);
			if (is != null)
				is.close();
		} catch (IOException e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 取得属性
	 * 
	 * @param 属性名
	 * 
	 * @return 属性值
	 * 
	 * */

	public String getProperties(String PropertyName) {
		return prop.getProperty(PropertyName);
	}

	public static void main(String args[]) {
		PropUtil p = new PropUtil("E:/tools/tomcat-6.0.29/webapps/ybk007/WEB-INF/classes/jdbc.properties");
		System.out.println(p.getProperties("url"));
	}
}
