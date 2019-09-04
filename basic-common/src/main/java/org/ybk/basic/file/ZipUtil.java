package org.ybk.basic.file;

import java.io.*;
import java.util.*;
import java.util.zip.*;

import org.ybk.basic.util.Util;

public class ZipUtil {
	static final int BUFFER = 2048;

	public static void main(String argv[]) {
		// String[] filepaths = { "D:\\zip1.txt", "D:\\zip2.txt",
		// "D:\\复件 (2) chepai.txt" };
		String[] filepaths = { "D:\\复件 (2) chepai.txt" };
		// addZipFiles("D://test.zip",filepaths);
		test();
	}

	public static void test() {
		try {
			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(
					new FileOutputStream("d:/Test.zip"), 1024));
			zos.putNextEntry(new ZipEntry("中文.txt"));
			zos.write("中文".getBytes("utf-8"));
			zos.putNextEntry(new ZipEntry("にほんご .txt"));
			zos.write("にほんご".getBytes("utf-8"));
			zos.putNextEntry(new ZipEntry("EngLish.txt"));
			zos.write("EngLish".getBytes("utf-8"));
			zos.putNextEntry(new ZipEntry("Nichts zu danken.txt"));
			zos.write("Nichts zu danken!".getBytes("utf-8"));

			// zos.setEncoding("GB2312");
			// zos.setComment("12323");
			zos.closeEntry();
			zos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addZipFiles(String zipFileName, String[] fileNames) {
		if (Util.isNull(zipFileName))
			return;
		zipFileName = Util.replaceAll(zipFileName, "/", File.separator);
		zipFileName = Util.replaceAll(zipFileName, "\\", File.separator);
		int pos = -1;
		try {
			FileOutputStream f = new FileOutputStream(zipFileName);
			// 输出校验流,采用Adler32更快
			CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
			// 创建压缩输出流
			// ZipOutputStream zos = new
			// ZipOutputStream(csum,Charset.forName("UTF-8"));
			// ZipOutputStream zos = new ZipOutputStream(csum);
			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(
					new FileOutputStream(zipFileName), 1024));
			// zos.setEncoding("gbk");
			// BufferedOutputStream out = new BufferedOutputStream(zos);
			// BufferedWriter bufferedWriter = new BufferedWriter(new
			// FileWriter(zipFileName));
			// 设置Zip文件注释
			// zos.setComment(new String("中文测试".getBytes("utf-8")));

			for (String fileName : fileNames) {
				fileName = Util.replaceAll(fileName, "/", File.separator);
				fileName = Util.replaceAll(fileName, "\\", File.separator);

				// 针对单个文件建立读取流
				// BufferedReader bin = new BufferedReader(new
				// FileReader(fileName));
				InputStreamReader isr;
				isr = new InputStreamReader(new FileInputStream(fileName));
				pos = fileName.lastIndexOf(File.separator);
				if (pos > -1)
					fileName = fileName.substring(pos + 1);
				// putNextEntry 写入新条目，并定位到新条目开始处
				zos.putNextEntry(new ZipEntry(fileName));
				int b;
				// char cbuf[] = new char[1000];
				while ((b = isr.read()) != -1) {
					zos.write(b);
					// out.write(cbuf);
				}
				isr.close();
				zos.flush();
			}
			zos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void unZip(String fileName, String unZipDir) {

		try {
			// 先判断目标文件夹是否存在，如果不存在则新建，如果父目录不存在也新建
			File f = new File(unZipDir);
			if (!f.exists()) {
				f.mkdirs();
			}

			BufferedOutputStream dest = null;
			BufferedInputStream is = null;
			ZipEntry entry;
			ZipFile zipfile = new ZipFile(fileName);
			Enumeration e = zipfile.entries();
			while (e.hasMoreElements()) {
				entry = (ZipEntry) e.nextElement();
				// System.out.println("Extracting: " + entry);
				is = new BufferedInputStream(zipfile.getInputStream(entry));
				int count;
				byte data[] = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream(unZipDir + "/"
						+ entry.getName());
				// System.out.println("entry.getName(): " + entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean makeDir(String unZipDir) {
		boolean b = false;
		try {
			File f = new File(unZipDir);
			if (!f.exists()) {
				b = f.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return b;
		}
		return b;
	}
}