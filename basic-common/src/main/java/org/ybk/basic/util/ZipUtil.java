package org.ybk.basic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提供的针对文件的加压解压的操作工具类
 * 
 * @author linwei
 * 
 */
public class ZipUtil {

	private final static Logger log = LoggerFactory.getLogger(ZipUtil.class);

	/**
	 * 执行压缩文件的解压,并自动生成文件的操作
	 * 
	 * @param in
	 * @param outputDirectory
	 * @return
	 * 
	 */
	public static Map<String, Object> unZip(InputStream in, String outputDirectory) {

		if (in == null)
			return null;

		ZipEntry zipEntry = null;
		FileOutputStream out = null;
		String uniqueName, iconUrl = null;
		Map<String, Object> map = new HashMap<String, Object>();
		ZipInputStream zipIn = new ZipInputStream(in);
		try {
			while ((zipEntry = zipIn.getNextEntry()) != null) {
				// 如果是文件夹路径方式，本方法内暂时不提供操作
				if (zipEntry.isDirectory()) {
					// String name = zipEntry.getName();
					// name = name.substring(0, name.length() - 1);
					// File file = new File(outputDirectory + File.separator +
					// name);
					// file.mkdir();
				} else {
					// 如果是文件，则直接在对应路径下生成
					//uniqueName = getSavedFileName(zipEntry.getName());
					//TODO:
					uniqueName = "TOOTOTO";
					File path = new File(outputDirectory + File.separator);
					if (!path.exists())
						path.mkdirs();

					iconUrl = outputDirectory + File.separator + uniqueName;
					File file = new File(iconUrl);
					file.createNewFile();
					out = new FileOutputStream(file);
					int b = 0;
					while ((b = zipIn.read()) != -1) {
						out.write(b);
					}
					out.close();
					map.put(zipEntry.getName(), iconUrl);
				}
			}
			return map;
		} catch (Exception ex) {
			log.error("in unZip(InputStream in,String outputDirectory) has an error,e is " + ex);
			return null;
		} finally {
			IOUtils.closeQuietly(zipIn);
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * 解压压缩文件流，并根据解压层次来判断，是否只解析第一层，还是解析所有数据 map<name,inputstream>
	 * 
	 * @param in
	 * @param parseLevel
	 * @return
	 */
	public static Map<String, Object> unZip(InputStream in, int parseLevel) {

		if (in == null)
			return null;

		ZipEntry zipEntry = null;
		FileOutputStream out = null;
		Map<String, Object> map = new HashMap<String, Object>();
		ZipInputStream zipIn = new ZipInputStream(in);
		try {
			while ((zipEntry = zipIn.getNextEntry()) != null) {
				// 如果是文件夹路径方式，本方法内暂时不提供操作
				if (zipEntry.isDirectory()) {
					// String name = zipEntry.getName();
					// name = name.substring(0, name.length() - 1);
					// File file = new File(outputDirectory + File.separator +
					// name);
					// file.mkdir();
				} else {
					// 如果是文件，则直接存放在Map中
					String name = zipEntry.getName();
					// 把压缩文件内的流转化为字节数组，够外部逻辑使用(之后关闭流)
					byte[] bt = IOUtils.toByteArray(zipIn);
					map.put(name, bt);
				}
			}
			return map;
		} catch (Exception ex) {
			log.error("in unZip(InputStream in,int parseLevel) has an error,e is " + ex);
			return null;
		} finally {
			IOUtils.closeQuietly(zipIn);
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}

	/** 压缩多个文件 */
	public static void zipFile(String[] filePaths, String zipPath) {
		try {
			File zipFile = new File(zipPath);
			ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
			
			for(String filePath:filePaths){
				File file = new File(filePath);
				InputStream input = new FileInputStream(file);			
				zipOut.putNextEntry(new ZipEntry(file.getName()));
				int temp = 0;
				while ((temp = input.read()) != -1) {
					zipOut.write(temp);
				}
				input.close();
			}
			zipOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		String filePaths[] = new String[]{"E:/temp/img/jxp.png","E:/temp/img/fuwu.jpg"};
		String zipPath = "E:/temp/img/jxp.zip";
		ZipUtil.zipFile(filePaths, zipPath);
	}

}
