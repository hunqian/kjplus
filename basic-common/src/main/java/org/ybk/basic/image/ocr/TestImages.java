package org.ybk.basic.image.ocr;

import org.ybk.basic.image.DownImage;

public class TestImages {
	
	public static void  main(String args[]){
		String imgUrl = "http://qyxy.baic.gov.cn/CheckCodeCaptcha?currentTimeMillis=1435050196691";
		int total = 10;
		String fileURL = null;
		for(int i=0;i<total;i++){
			fileURL = "D:/temp/chrome/test/" + i + ".jpg";
			DownImage.makeImg(imgUrl, fileURL);
		}
	}
}
