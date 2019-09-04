package org.ybk.basic.image.impl;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * 使用javax image io生成缩略图
 * 
 * @author kingapex 2010-7-10下午11:43:05
 */
public class JavaImageIOCreator {
	private String srcFile;
	private String destFile;

	private static Map<String, String> extMap;
	static {
		extMap = new HashMap<String, String>(5);
		extMap.put("jpg", "JPEG");
		extMap.put("jpeg", "JPEG");
		extMap.put("gif", "GIF");
		extMap.put("png", "PNG");
		extMap.put("bmp", "BMP");
	}

	public JavaImageIOCreator(String sourcefile, String targetFile) {
		this.srcFile = sourcefile;
		this.destFile = targetFile;
	}

	public void resize(int w, int h) {
		String ext = ImageFileUtil.getFileExt(srcFile).toLowerCase();

		BufferedImage image;
		try {
			Image img = Toolkit.getDefaultToolkit().getImage(srcFile);
			// image = ImageIO.read(new File(srcFile));
			image = ImageFileUtil.toBufferedImage(img);
			double xFactor = w * 1.0 / image.getWidth();
			double yFactor = h * 1.0 / image.getHeight();
			double factor = 0.0;
			//以宽度为
			if(xFactor>=yFactor)
				factor = xFactor;
			else
				factor = yFactor;
			ImageIO.write(Lanczos.resizeImage(image, factor), ext, new File(destFile));
		} catch (IOException e) {
			throw new RuntimeException("生成缩略图错误", e);
		}
	}

	public static void main(String args[]) {
		String srcfile = "E:/temp/image/showqrcode.jpg";
		String destfile = "E:/temp/image/showqrcode-180.jpg";
		JavaImageIOCreator creator = new JavaImageIOCreator(srcfile, destfile);
		creator.resize(180, 180);
	}
}
