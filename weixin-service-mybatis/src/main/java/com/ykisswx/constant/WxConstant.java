package com.ykisswx.constant;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class WxConstant {
	
	//一天的秒数
	public static final int ONE_DAY_INSEC = 24*60*60;
	public static final int ONE_HOUR_INSEC = 60*60;

	//二维码
	public static final String QR_SCENE = "QR_SCENE";
	public static final String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
	public static final String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";
	
	//Yes/No/Err
	public static final String FLAG_YES = "Y";
	public static final String FLAG_NO = "N";
	public static final String FLAG_ERR = "E";
	
	//保存文件的位置
	public static final String WX_BASE_DIR = ResourceBundle.getBundle("wxconfig",Locale.CHINA).getString("wx_base_dir")+File.separator;

	public static final String WX_BASE_URL = ResourceBundle.getBundle("wxconfig",Locale.CHINA).getString("wx_base_url")+File.separator;
	
	//基础域名,移到accout中
	//public static final String WX_BASE_DOMAIN = ResourceBundle.getBundle("config",Locale.CHINA).getString("wx_base_domain");

	//默认头像
	public final static String DEFAULT_FACE = ResourceBundle.getBundle("config",Locale.CHINA).getString("default_face");
	
	//openEvent类型,C处理结束/P处理中/U未处理/E异常
	public static final String OPEN_EVENT_ERRORED = "E";
	public static final String OPEN_EVENT_UNPROCESSED = "U";
	public static final String OPEN_EVENT_PROCESSING = "P";
	public static final String OPEN_EVENT_COMPLETED = "C";
	
	//匹配模式，V模糊/E精确/N不匹配
	public static final String ARTICLE_TAGMATCH_VAGUE = "V";
	public static final String ARTICLE_TAGMATCH_EXACT = "E";
	public static final String ARTICLE_TAGMATCH_NON = "N";
	
	// 参数名称
	public static final String PARAM_TEMP_QRCODE_VALID_DAY = "TEMP_QRCODE_VALID_DAY";
	//暂定为10年
	public static final String PARAM_PERMANANT_QRCODE_VALID_DAY = "PERMANANT_QRCODE_VALID_DAY";
	public static final String PARAM_SYSTEM_SHIFT_PERMIT = "SYSTEM_SHIFT_PERMIT";
	public static final String PARAM_LOWLEVEL_QRCODE_ENABLED = "LOWLEVEL_QRCODE_ENABLED";
	public static final String PARAM_PAY_MINCASH_RATE = "PAY_MINCASH_RATE";
	
	//用户状态
	public static final String MEMBER_FLAG_YES = "Y";
	public static final String MEMBER_FLAG_NO = "N";
	public static final String MEMBER_FLAG_LOCK = "L";

	//用户类型
	public static final String MEMBER_TYPE_AGENT = "A";
	public static final String MEMBER_TYPE_PERSON = "P";
	//厂商类型
	public static final String MEMBER_TYPE_MAP = "M";
	
	//默认手机国家代码
	public static final String DEF_MOBILE_COUNTYR_CODE = "86";
	
	//微信公共号类型,S服务号/B订阅号
	public static final String WX_ACC_TYPE_SERVICE = "S";
	public static final String WX_ACC_TYPE_BROAD = "B";
	
}
