package org.ybk.basic.util;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.ybk.basic.mail.MailException;
import org.ybk.basic.mail.MailUtil;

public class EMailUtil {
	
	private static Logger logger = Logger.getLogger(EMailUtil.class);
	
	private static String MAIL_SERVER = ResourceBundle.getBundle("config",Locale.CHINA).getString("mweb_mail_server");
	private static String MAIL_PORT = ResourceBundle.getBundle("config",Locale.CHINA).getString("mweb_mail_port");
	private static String MAIL_USER = ResourceBundle.getBundle("config",Locale.CHINA).getString("mweb_mail_user");
	private static String MAIL_PASS = ResourceBundle.getBundle("config",Locale.CHINA).getString("mweb_mail_pass");
	private static String MAIL_FROM_EMAIL = ResourceBundle.getBundle("config",Locale.CHINA).getString("mweb_mail_frm_email");
	private static String MAIL_IS_SSL = ResourceBundle.getBundle("config",Locale.CHINA).getString("mweb_is_ssl");
	private static String MAIL_TMP_PATH = ResourceBundle.getBundle("config",Locale.CHINA).getString("mweb_mail_tmp_path");
	
	public static void sendMail(String subject,String toAddr,Object[] objreps) throws MailException{
		//测试发送
		Properties params = new Properties();
		params.put("host", MAIL_SERVER);
		params.put("port", MAIL_PORT);
		params.put("user", MAIL_USER);
		params.put("pass", MAIL_PASS);
		params.put("from", MAIL_FROM_EMAIL);
		params.put("is_ssl", MAIL_IS_SSL);
		//Object[] obj = new Object[]{"测试用户","o98s4dfikjwkh2vy8zn3a6l9ip48j0w84h4zrlcr3iwc630xso39m4ra68utz713"};
		MailUtil.sendMail(subject, toAddr, objreps, MAIL_TMP_PATH,params);
	}
	
	public static void main(String[] args) {
		try{
			String subject = "测试一下";
			String toAddr = "jiang.leibo77@zte.com.cn";
			Object[] obj = new Object[]{"测试用户","232323",Util.currTime()};
			sendMail(subject,toAddr,obj);
			System.out.println("发送成功");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
