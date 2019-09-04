package org.ybk.basic.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.ybk.basic.util.Util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CreateImage {

	/**
	 * 根据提供的文字生成jpg图片
	 * 
	 * @param backJpgPath
	 *            String 背景图片路径
	 * @param imageDTO_list
	 *            小图片dto(第个元素都是LetterDTO) list
	 * @param letterDTO_list
	 *            文字dto(第个元素都是ImageDTO_list) list
	 * @param newJPGFileName
	 *            String 生成的新JPG图片名称
	 */
	public boolean createJpg(String backImageFileName, List imageDTO_list, List letterDTO_list, String newJPGFileName)
			throws IOException {
		boolean ret = false;
		// 宽度 高度
		File backJpg_file = new File(backImageFileName);
		BufferedImage back_bufferdImage = ImageIO.read(backJpg_file);

		Graphics2D graphics2D = back_bufferdImage.createGraphics();
		// 去除锯齿(当设置的字体过大的时候,会出现锯齿)
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		if (letterDTO_list != null && letterDTO_list.size() > 0) {
			for (int i = 0; i < letterDTO_list.size(); i++) {
				LetterDto letterDTO = (LetterDto) letterDTO_list.get(i);
				if ((letterDTO != null) && (letterDTO.getColor() != null) && (letterDTO.getFont() != null))
					graphics2D.setColor(letterDTO.getColor()); // 字的颜色
				Font letter_Font = letterDTO.getFont();
				graphics2D.setFont(letter_Font);
				FontMetrics letter_FontMetrics = graphics2D.getFontMetrics(letter_Font);
				int letter_Ascent = letter_FontMetrics.getAscent();
				int letter_leading = letter_FontMetrics.getLeading();
				// 在指定坐标除添加文字
				graphics2D.drawString(letterDTO.getLetter(), letterDTO.getX(), letterDTO.getY() + letter_Ascent - letter_leading);
			}
		}
		
		graphics2D.dispose();// 释放资源
		if (imageDTO_list != null) {
			String imgFileName = null;
			for (int i = 0; i < imageDTO_list.size(); i++) {
				ImageDto imageDTO = (ImageDto) imageDTO_list.get(i);
				if (imageDTO == null)
					continue;
				imgFileName = imageDTO.getImageFileName();
				if(Util.isNull(imgFileName))
					continue;
				
				BufferedImage smallImage_bufferedImage = null;
				if(imgFileName.startsWith("http")){
					URL u = new URL(imgFileName);
					smallImage_bufferedImage = ImageIO.read(u);
				}else{
					File smallImage_file = new File(imgFileName);
					smallImage_bufferedImage = ImageIO.read(smallImage_file);
				}
				int width = smallImage_bufferedImage.getWidth(); // 图片宽度
				int height = smallImage_bufferedImage.getHeight(); // 图片高度
				if(width>imageDTO.getMax() || height > imageDTO.getY()){
					double ratio = 0.0;
					if(width > height)
						ratio = imageDTO.getMax()*1.0 / width;
					else
						ratio = imageDTO.getMax()*1.0/ height;
					int w = (int)(width*ratio);
					int h = (int)(height*ratio);
					BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			        image.getGraphics().drawImage(smallImage_bufferedImage, 0, 0, w, h, null);
			        
			        // 从图片中读取RGB
					int[] imageArray = new int[w * h];
					imageArray = image.getRGB(0, 0, w, h, imageArray, 0, w);
					back_bufferdImage.setRGB(imageDTO.getX(), imageDTO.getY(), w, h, imageArray, 0, w);
				}else{
					// 从图片中读取RGB
					int[] imageArray = new int[width * height];
					imageArray = smallImage_bufferedImage.getRGB(0, 0, width, height, imageArray, 0, width);
					back_bufferdImage.setRGB(imageDTO.getX(), imageDTO.getY(), width, height, imageArray, 0, width);
				}
			}
		}
		FileOutputStream out = null;
		try {
			System.out.println("newJPGFileName:"+newJPGFileName);
			out = new FileOutputStream(newJPGFileName); // 指定输出文件
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(back_bufferdImage);
			param.setQuality(50f, true);
			encoder.encode(back_bufferdImage, param); // 存盘
			ret = true;
		} finally {
			out.flush();
			out.close();
		}
		return ret;
	}

	public static void main(String[] args) {
		try {
			List<LetterDto> letters = new ArrayList<LetterDto>();
			LetterDto letterDTO = new LetterDto();

			letterDTO.setLetter("有效至:15-07-01");
			letterDTO.setX(10);
			letterDTO.setY(135);

			Font font = Font.decode("宋体");
			font = font.deriveFont(Float.parseFloat("14"));
			letterDTO.setFont(font);
			letterDTO.setColor(Color.black);
			letters.add(letterDTO);

			List<ImageDto> images = new ArrayList<ImageDto>();
			ImageDto imageDTO = new ImageDto();
			imageDTO.setImageFileName("e:/temp/image/showqrcode-120.jpg");
			imageDTO.setX(10);
			imageDTO.setY(10);
			images.add(imageDTO);

			CreateImage createImage = new CreateImage();
			String backImageFileName = "e:/temp/image/dabai_back-16X9.jpg";
			String newJPGFileName = "e:/temp/image/a1.jpg";

			createImage.createJpg(backImageFileName, images, letters, newJPGFileName);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}