package org.ybk.basic.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.MD5;
import org.ybk.basic.util.Util;

//base64 PreProc运处理
public class ImageBasePpUtil {

	//内容预处理，将base64编码的内容转换成图
	public static String procContent(String content, String basePath, String baseUrl) throws IOException, ParserException{

		String imageDataUrl = null;
		String fileType = null;
		String filename = null;
		File f = null;
		
		if(Util.isNull(content)){
			return "";
		}
		if(Util.isNull(basePath) || Util.isNull(baseUrl))
			return content;
		
		// 实例化一个本地html文件的Parser
		Parser parser = Parser.createParser(content, "UTF-8");
		NodeClassFilter imgFilter = new NodeClassFilter(ImageTag.class);
		NodeList imgList = parser.extractAllNodesThatMatch(imgFilter);
		if(imgList.size() == 0)
			return content;
		
		for (int i = 0; i < imgList.size(); i++) {
			ImageTag imgTag = (ImageTag) imgList.elementAt(i);
			imageDataUrl = imgTag.getAttribute("src");
			if (!imageDataUrl.startsWith("data:image/")) {
				continue;
			}
			
			// 写入
			int p = imageDataUrl.indexOf(";base64,");
			int startIndex = p + ";base64,".length();
			fileType = imageDataUrl.substring("data:image/".length(), p);
			filename = MD5.MD5Encode(imageDataUrl.substring(startIndex)) + ".";
			String curday = DateUtil.currDay();
			String path = basePath + "/imgextract/" + curday + "/" + filename + fileType;
			String url = baseUrl + "/imgextract/" + curday + "/" + filename + fileType;

			p = content.indexOf(imageDataUrl);
			content = content.substring(0, p) + url + content.substring(p + imageDataUrl.length());

			f = new File(path);
			if (f.exists()) {
				continue;
			}
			checkDir(path);

			// keep only the image data
			imageDataUrl = imageDataUrl.substring(startIndex, imageDataUrl.length());

			// convert the image data String to a byte[]
			byte[] dataArr = DatatypeConverter.parseBase64Binary(imageDataUrl);
			InputStream in = new ByteArrayInputStream(dataArr);
			BufferedImage fullSize = ImageIO.read(in);
			ImageIO.write(fullSize, fileType, new File(path));
		}
		return content;
	}
	
	private static void checkDir(String dir) {
		File f = new File(dir);
		if (!f.exists())
			f.mkdirs();
		return;
	}
}
