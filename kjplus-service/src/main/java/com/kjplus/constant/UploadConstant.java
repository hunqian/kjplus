package com.kjplus.constant;

import java.util.Locale;
import java.util.ResourceBundle;

public class UploadConstant {

	//manager和console上传路径
	public static final String IMAGE_FILE_DIR = ResourceBundle.getBundle("upload", Locale.CHINA)
			.getString("image_file_upload_dir");
	public static final String IMAGE_SERVER_URL = ResourceBundle.getBundle("upload", Locale.CHINA)
			.getString("http_image_server_url");
	public static final String ALLOW_UPLOAD_PIC = ResourceBundle.getBundle("upload", Locale.CHINA)
			.getString("allow_upload_pic");
	public static final String ALLOW_UPLOAD_VIDEO = ResourceBundle.getBundle("upload", Locale.CHINA)
			.getString("allow_upload_video");
	public static final String ALLOW_UPLOAD_VOICE = ResourceBundle.getBundle("upload", Locale.CHINA)
			.getString("allow_upload_voice");
	
	//app上传路径
	public static final String APP_FILE_DIR = ResourceBundle.getBundle("upload", Locale.CHINA)
			.getString("app_file_upload_dir");
	
	public static final String APP_HTTP_URL = ResourceBundle.getBundle("upload", Locale.CHINA)
			.getString("app_http_upload_server_url");
}
