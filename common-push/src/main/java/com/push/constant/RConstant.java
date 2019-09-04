package com.push.constant;

import java.util.Locale;
import java.util.ResourceBundle;

public class RConstant {

	// 融云的key 和证书
	public transient static final String RONG_KEY = ResourceBundle.getBundle("push-key", Locale.CHINA).getString("Rong_Colud_key");
	public transient static final String RONG_SECRET = ResourceBundle.getBundle("push-key", Locale.CHINA).getString("Rong_Colud_Secret");
	

}
