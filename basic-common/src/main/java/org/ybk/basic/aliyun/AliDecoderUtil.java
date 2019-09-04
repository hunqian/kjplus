package org.ybk.basic.aliyun;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.ybk.basic.aliyun.HttpUtils;
import org.ybk.basic.image.ImageUtil;
import org.ybk.basic.util.Util;

import com.alibaba.fastjson.JSONObject;

public class AliDecoderUtil {
	
	public static void main(String[] args) {
		String baseDir = "D:/temp/imgdecoder/";
		File dir = new File(baseDir);
		File files[] = dir.listFiles(); 
		String fname = null;
		String checkCode = null;
		int p = 0;
		for(int i=0; i<files.length ; i++){
			fname = files[i].getName();
			p = fname.lastIndexOf(".");
			if(p < 0)
				continue;
			fname = fname.substring(0,p);
			//System.out.println(fname.substring(0,p) + "," + files[i].getAbsolutePath());
			checkCode = decCheckCode(files[i].getAbsolutePath());
			if(!fname.equals(checkCode)){
				System.out.println("[错误验证]" + fname +"," + checkCode);
			}else
				System.out.println("[正确验证]" + fname);
		}
	}
	
	
	public static String decCheckCode(String fileName){
		
		String host = "http://ali-checkcode2.showapi.com";
		String path = "/checkcode";
		String method = "POST";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE 6b26cac348a14443b7f0f62414c6a126");
		Map<String, String> querys = new HashMap<String, String>();
		Map<String, String> bodys = new HashMap<String, String>();
		bodys.put("convert_to_jpg", "0");
		bodys.put("img_base64", ImageUtil.getImageBase64(fileName));
		bodys.put("typeId", "2000");

		try {
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			//System.out.println(response.toString());
			String json = EntityUtils.toString(response.getEntity());
			if(Util.isNull(json))
				return null;
			JSONObject jsonObj = JSONObject.parseObject(json);
			if(jsonObj == null)
				return null;
			if(!jsonObj.containsKey("showapi_res_body"))
				return null;
			jsonObj = jsonObj.getJSONObject("showapi_res_body");
			if(!jsonObj.containsKey("Result"))
				return null;
			else
				return jsonObj.getString("Result").toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
