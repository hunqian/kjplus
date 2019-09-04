package org.ybk.basic.util;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.ImageIcon;

public class FileUtil {
	
	/**
     * 保存文件：使用FileWriter
     */
    public static void saveFileWriter(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean appendText(String fileName, String content) throws IOException{
    	return appendText(fileName, content, "gb2312");
    }
    /**
	 * 在文档后附加内容
	 * 
	 * @param textName
	 * @param date
	 * @return
	 */
	public static boolean appendText(String fileName, String content, String encoding) throws IOException{
		//内容
    	if(Util.isNull(content))
    		return false;
    	//路径
    	if(Util.isNull(fileName))
    		return false;
    	fileName = checkDir(fileName);
    	OutputStreamWriter fw = null;
    	FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName,true);
			fw = new OutputStreamWriter(fos, encoding);
			fw.append(content);			
		}finally{
			if (fw != null)
				fw.close();
		}
		return true;
	}
    
    /**
     * 以gbk格式保存文件
     */
    public static boolean saveFile(String fileName, String content) throws IOException{
    	//内容
    	if(Util.isNull(content))
    		return false;
    	//路径
    	if(Util.isNull(fileName))
    		return false;
    	fileName = checkDir(fileName);
    	OutputStreamWriter osw = null;
        try {
        	osw = new OutputStreamWriter(new FileOutputStream(fileName),"gbk");
        	osw.write(content,0,content.length());
        	osw.flush();
        }finally{
        	if(osw != null)
        		osw.close();
        }
        return true;
    }
    
    //检查路径是否存在
    public static String checkDir(String fname){
    	String fileName = Util.replaceAll(fname, "/", File.separator);
    	fileName = Util.replaceAll(fileName, "\\", File.separator);
    	int index = fileName.lastIndexOf(File.separator);
    	if(index<0)
    		return null;
    	//检验路径是否存在
    	File dir = new File(fileName.substring(0,index));
    	if(!dir.exists())
    		dir.mkdirs();
    	return fileName;
    }
    
    public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		// boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			/*
			 * if (hasAlpha) { transparency = Transparency.BITMASK; }
			 */

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			// int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
			/*
			 * if (hasAlpha) { type = BufferedImage.TYPE_INT_ARGB; }
			 */
			bimage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}
    
    public static void main(String[] args) {
        String fileName = "d:/temp/newTemp.txt";
        String content = "new append中文!";
        String content2 = "新一行中文!\n\r";
        //按方法A追加文件
        try{
        	//FileUtil.saveFile(fileName, content);
        	FileUtil.appendText(fileName, content2);
        }catch(IOException e){
        	e.printStackTrace();
        }
    }
    

}
