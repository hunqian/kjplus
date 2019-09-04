package com.kjplus.rest;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.ybk.basic.util.Util;

import com.kjplus.constant.UploadConstant;
import com.mq.util.DateUtil;

@Controller
// 文件上传控制器类
public class FileUploadCtrl implements ServletContextAware { // 实现ServletContextAware接口，获取本地路径

	private static final int BUFFER_SIZE = 10 * 1024;

	private static Logger logger = Logger.getLogger(FileUploadCtrl.class);

	private ServletContext servletContext;

	// 实现接口中的setServletContext方法
	public void setServletContext(ServletContext servletContext) { 
		this.servletContext = servletContext;
	}

	private static Hashtable<String, String> DIRS = new Hashtable<String, String>();

	// 上传普通图片文件,将文件上传请求映射到该方法
	@RequestMapping(value = "/uploadimgfile.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> uploadimgfile(@RequestParam("imgfile") CommonsMultipartFile mFile, HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		
		mFile.getSize();
		String filename = mFile.getOriginalFilename();
		int pos = filename.lastIndexOf(".");
		String postfix = "";
		String fileType;
		String tofilename = null;
		if (pos > 0) {
			UUID uuid = UUID.randomUUID();
			tofilename = uuid.toString();
			postfix = filename.substring(pos);
			fileType = filename.substring(pos + 1).toLowerCase();
			filename = filename.substring(0, pos);
		} else {
			tofilename = filename;
			fileType = "未知";
		}
		if (UploadConstant.ALLOW_UPLOAD_PIC.indexOf(fileType) == -1) {
			map.put("msg", "上传图片格式不正确!");
			map.put("success", false);
			return map;
		}
		
		String path = UploadConstant.IMAGE_FILE_DIR + "imgfile/" + DateUtil.currDay() + "/";
		path = Util.replaceAll(path, "\\", "/");
		String toSrc = path + tofilename + postfix;
		String toUrl = UploadConstant.IMAGE_SERVER_URL + "imgfile/" + DateUtil.currDay() + "/" + tofilename + postfix;
		checkDir(path);
		try {
			mFile.getFileItem().write(new File(toSrc));
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("url", toUrl);
			//map2.put("path", toSrc);
			map.put("data", data);
			map.put("result", 1);
			map.put("message", "上传成功！");
		} catch (Exception e) {
			logger.error(e);
			map.put("failure", true);
			map.put("result", -1);
			map.put("message", e.getMessage());
		}
		return map;
	}

	private void checkDir(String dir) {
		if (DIRS.containsKey(dir))
			return;
		File f = new File(dir);
		if (!f.exists())
			f.mkdirs();
		DIRS.put(dir, "Y");
		return;
	}

}