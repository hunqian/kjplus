package org.ybk.basic.image.scale;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import com.mortennobel.imagescaling.ResampleOp;

public class MortenResizer implements Runnable{

	private BufferedImage sourceImage;
	//private BufferedImage scaledImage;
	private String destFile = null;
	private String formatName = null;
	private int width = -1;
	private int height = -1;
	
	public MortenResizer(BufferedImage sourceImage, String formatName, String destFile){
		this.sourceImage = sourceImage;
		this.formatName = formatName;
		this.destFile = destFile;
	}
	
	public MortenResizer(BufferedImage sourceImage, String formatName, 
			String destFile, int width, int height){
		this.sourceImage = sourceImage;
		this.formatName = formatName;
		this.destFile = destFile;
		this.height = height;
		this.width = width;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	public void setHeight(int height){
		this.height = height;
	}
	
	public void run(){
		try{
			ResampleOp resampleOp = new ResampleOp(width,height);
			BufferedImage scaledImage = resampleOp.filter(sourceImage,null);
			ImageIO.write(scaledImage, formatName, new File(destFile));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
