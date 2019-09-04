package org.ybk.basic.image.scale;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.ybk.basic.util.Util;

public class MortenUtil {
	
	//截取中间
	public static BufferedImage clipCenter(BufferedImage image,int width,int height){

		BufferedImage clipImage = null;
		int w = image.getWidth()-1;
		int h = image.getHeight()-1;
		double wr = width*1.0/w;
		double hr = height*1.0/h;
		int maxh = 0;
		int maxw = 0;
		//取压缩比例最大的为参照
		if(wr>hr){
			maxh = (int)(height*1.0/width*w);
			maxw = w; 
			//3w*5h
			clipImage = image.getSubimage(0, (h-maxh)/2, maxw, maxh);
		}else{
			//5w*3h,以h为准
			maxh = h;
			maxw = (int)(width*1.0/height*h);
			clipImage = image.getSubimage((w-maxw)/2, 0,maxw, maxh);
		}
		return clipImage;
	}
	
	//压缩单个文件
	public static void compress(String sourceFile,String packSize,String packFile){
		List<String> packSizes = new ArrayList<String>();
		packSizes.add(packSize);
		List<String> packFiles = new ArrayList<String>();
		packFiles.add(packFile);
		compress(sourceFile,packSizes,packFiles);
	}
	
	//压缩一组文件	
	public static void compress(String sourceFile,List<String> packSizes,List<String> packFiles){
		
		if(packSizes == null || packSizes.size() == 0)
			return;
		if(packFiles == null || packFiles.size() == 0)
			return;
		if(packSizes.size() != packFiles.size())
			return;
		if(Util.isNull(sourceFile))
			return;
		
		int xpack = 0;
		int ypack = 0;
		int p = 0;
		String packStr = null;
		String formatName = "";
		
		try{
			p = sourceFile.lastIndexOf(".");
			if(p>-1)
				formatName = sourceFile.substring(p+1);
			BufferedImage image = ImageIO.read(new File(sourceFile));
			int w = image.getWidth();
			int h = image.getHeight();
			for(int i=0;i<packSizes.size();i++){
				packStr = packSizes.get(i);
				if(Util.isNull(packStr))
					continue;
				packStr = packStr.toUpperCase();
				p = packStr.indexOf("X");
				if(p<=-1){
					xpack = Util.parseNumVl(packStr, -1);
					ypack = (int)(h*xpack*1.0/w);
				}else if(p==0){
					ypack = Util.parseNumVl(packStr.substring(1), -1);
					xpack = (int)(w*ypack*1.0/h);
				}else{
					xpack = Util.parseNumVl(packStr.substring(0,p), -1);
					ypack = Util.parseNumVl(packStr.substring(p+1), -1);
				}
				MortenResizer r = new MortenResizer(clipCenter(image,xpack,ypack),formatName,packFiles.get(i));
				r.setWidth(xpack);
				r.setHeight(ypack);
				r.run();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		long btime = System.currentTimeMillis();
		String sourceFile = "E:/temp/img/IMG_5913.jpg";
		String destSourceFile = "E:/temp/img/IMG_5913-640X640.jpg";
		//compress(sourceFile,"640X640",destSourceFile);
		
		String baseDestSourceFile = "E:/temp/img/IMG_5913.jpg";
		List<String> destSourceFiles = new ArrayList<String>();
		destSourceFiles.add(baseDestSourceFile+"-1024.jpg");
		destSourceFiles.add(baseDestSourceFile+"-800X800.jpg");
		destSourceFiles.add(baseDestSourceFile+"-1024X768.jpg");
		destSourceFiles.add(baseDestSourceFile+"-255X255.jpg");
		List<String> packSizes = new ArrayList<String>();
		packSizes.add("1024");
		packSizes.add("800X800");
		packSizes.add("1024X768");
		packSizes.add("255X255");
		compress(sourceFile,packSizes,destSourceFiles);
		long etime = System.currentTimeMillis();
		System.out.println(etime-btime);
	}
}
