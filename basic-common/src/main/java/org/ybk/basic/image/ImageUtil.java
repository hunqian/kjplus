package org.ybk.basic.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.xmlbeans.impl.util.Base64;

import sun.misc.BASE64Encoder;

public class ImageUtil {

	/**
	 * 将svg字符串转换为png
	 * 
	 * @param svgCode
	 *            svg代码
	 * @param pngFilePath
	 *            保存的路径
	 * @throws TranscoderException
	 *             svg代码异常
	 * @throws IOException
	 *             io错误
	 */
	public static void convertToPng(String svgCode, String pngFilePath) throws IOException, TranscoderException {

		File file = new File(pngFilePath);
		FileOutputStream outputStream = null;
		try {
			file.createNewFile();
			outputStream = new FileOutputStream(file);
			convertToPng(svgCode.getBytes("utf-8"), outputStream);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将svgCode转换成png文件，直接输出到流中
	 * 
	 * @param svgCode
	 *            svg代码
	 * @param outputStream
	 *            输出流
	 * @throws TranscoderException
	 *             异常
	 * @throws IOException
	 *             io异常
	 */
	public static void convertToPng(byte[] bytes, OutputStream outputStream) throws TranscoderException, IOException {
		try {
			// byte[] bytes = svgCode.getBytes("utf-8");
			PNGTranscoder t = new PNGTranscoder();
			TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
			TranscoderOutput output = new TranscoderOutput(outputStream);
			t.transcode(input, output);
			outputStream.flush();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 从网络Url中下载文件
	 * 
	 * @param urlStr
	 * @param fileName
	 * @param savePath
	 * @throws IOException
	 */
	public static void downImageFromUrl(String urlStr, String savePath) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置超时间为3秒
		conn.setConnectTimeout(3 * 1000);
		// 防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		// 得到输入流
		InputStream inputStream = conn.getInputStream();
		// 获取自己数组
		byte[] getData = readInputStream(inputStream);

		int p = savePath.lastIndexOf(File.separator);
		String path = savePath;
		if (p > -1)
			path = savePath.substring(0, p);
		// 文件保存位置
		File saveDir = new File(path);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		File file = new File(savePath);
		FileOutputStream fos = new FileOutputStream(file);

		// convertToPng(getData,fos);
		fos.write(getData);
		if (fos != null) {
			fos.close();
		}

		if (inputStream != null) {
			inputStream.close();
		}
		// System.out.println("info:"+url+" download success");

	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author:
	 * @CreateTime:
	 * @return
	 */
	public static String getImageBase64(String imgFile) {
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64.encode(data));
		// 加密
		//BASE64Encoder encoder = new BASE64Encoder();
		//return encoder.encode(data);
	}

	public static void main(String argc[]) {
		String url = "http://gallery.fbcontent.cn/api/tarzan/images/1517097e7c91e1d.svg";
		String savePath = "d:/temp/";
		String fileName = "1517097e7c91e1d.png";
		try {
			downImageFromUrl(url, savePath + fileName);
			/*
			 * }catch(TranscoderException te){ te.printStackTrace();
			 */
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
