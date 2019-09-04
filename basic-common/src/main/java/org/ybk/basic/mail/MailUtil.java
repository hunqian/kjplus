package org.ybk.basic.mail;

import java.text.MessageFormat;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class MailUtil {
	private static String SERVER_HOST = "localhost";
	private static String SERVER_PORT = "25";
	private static String USER_NAME = "admin@locolhost";
	private static String USER_PASS = "1234";

	public static void sendMail(String subject, String toAddr, Object[] objreps, String tempPath) throws MailException {
		// 封装请求，这个处理很牛，一般人做不到。。。
		/*
		 * MultipartRequest re=new
		 * MultipartRequest(request,".",1024*1024,"gbk"); // 接收邮件类容 String from
		 * = re.getParameter("from"); String to = re.getParameter("to"); String
		 * subject = re.getParameter("subject");
		 */
		// 我把内容去掉了，为了是用模板
		// String context = re.getParameter("context");
		// String secret = re.getParameter("secret");
		// String filePath=re.getFilesystemName("file");

		// 邮件的服务器地址，邮件服务器的服务器名
		// String mailServerName = "smtp.qq.com";
		try {
			// 获得系统属性
			Properties prop = System.getProperties();
			// 设置属性
			prop.setProperty("mail.smtp.host", SERVER_HOST);
			prop.setProperty("mail.smtp.port", SERVER_PORT);
			prop.setProperty("mail.smtp.auth", "true");

			MyAuthenticator au = new MyAuthenticator(USER_NAME, USER_PASS);
			// 建立邮件发送的连接
			Session session = Session.getDefaultInstance(prop, au);
			// 创建邮件信息的载体
			Message message = new MimeMessage(session);
			// 设置邮件发送地址
			message.setFrom(new InternetAddress(USER_NAME));

			// 点到点的发送
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddr));
			// 设置邮件主题
			message.setSubject(subject);
			// 邮件发送时间
			message.setSentDate(new Date());

			/*
			 * 取得模板邮件的xml地址，这里用xml好处可大了，可以换行编写啊，
			 * 如果你用properties文件，一个网页文件的内容都写在一行，看着都不舒服
			 */
			// String
			// path=this.getServletContext().getRealPath("/")+"mailTemplate\\MyXml.xml";
			// 这个类下面贴，就几行代码，没什么神奇的
			String str = new ReaderXML().read(tempPath);
			// Object[] obj = new Object[]{username,sessid};
			// 这个MessageFormat是个好东西，不了解的去jdk看看，在java.text下面
			str = MessageFormat.format(str, objreps);
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setContent(str, "text/html;charset=gbk");

			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			message.setContent(mp);
			// 发送
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MailException("[发送邮件失败]" + toAddr);
		}
	}

	public static void sendMail(String subject, String toAddr, Object[] objreps, String tempPath, Properties params)
			throws MailException {
		// 邮件的服务器地址，邮件服务器的服务器名
		try {
			// 获得系统属性
			Properties prop = System.getProperties();
			// 设置属性
			prop.setProperty("mail.smtp.host", params.getProperty("host"));
			prop.setProperty("mail.smtp.port", params.getProperty("port"));
			prop.setProperty("mail.smtp.auth", "true");

			if (params.containsKey("is_ssl")) {
				String isSsl = params.getProperty("is_ssl").toUpperCase();
				if (isSsl.startsWith("Y")) {
					prop.setProperty("mail.smtp.socketFactory.port", params.getProperty("port"));
					prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				}
			}
			MyAuthenticator au = new MyAuthenticator(params.getProperty("user"), params.getProperty("pass"));
			// 建立邮件发送的连接
			Session session = Session.getDefaultInstance(prop, au);
			// 创建邮件信息的载体
			Message message = new MimeMessage(session);
			// 设置邮件发送地址
			message.setFrom(new InternetAddress(params.getProperty("from")));

			// 点到点的发送
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddr));
			// 设置邮件主题
			message.setSubject(subject);
			// 邮件发送时间
			message.setSentDate(new Date());

			/*
			 * 取得模板邮件的xml地址，这里用xml好处可大了，可以换行编写啊，
			 * 如果你用properties文件，一个网页文件的内容都写在一行，看着都不舒服
			 */
			// String
			// path=this.getServletContext().getRealPath("/")+"mailTemplate\\MyXml.xml";
			// 这个类下面贴，就几行代码，没什么神奇的
			String str = new ReaderXML().read(tempPath);
			// Object[] obj = new Object[]{username,sessid};
			// 这个MessageFormat是个好东西，不了解的去jdk看看，在java.text下面
			str = MessageFormat.format(str, objreps);
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setContent(str, "text/html;charset=gbk");

			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			message.setContent(mp);
			// 发送
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MailException("[发送邮件失败]" + toAddr);
		}
	}

	public static void receiveMail(Properties params) throws MailException {
		String host = params.getProperty("host");
		String port = params.getProperty("port");
		String username = params.getProperty("user");
		String password = params.getProperty("pass");

		// 获得系统属性
		Properties props = System.getProperties();
		// 设置属性
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", port);
		props.setProperty("mail.smtp.auth", "true");

		// 获取会话
		MyAuthenticator au = new MyAuthenticator(username, password);
		// 建立邮件发送的连接
		Session session = Session.getDefaultInstance(props, au);
		// 获取Store对象，使用POP3协议，也可能使用IMAP协议
		try {
			Store store = session.getStore("pop3");
			// 连接到邮件服务器
			// store.connect();
			store.connect(host, username, password);
			// 获取该用户Folder对象，并以只读方式打开
			Folder folder = store.getFolder("inbox");
			folder.open(Folder.READ_ONLY);
			// 检索所有邮件，按需填充
			Message message[] = folder.getMessages();
			for (int i = 0; i < message.length; i++) {
				// 打印出每个邮件的发件人和主题
				System.out.println(i + ":" + message[i].getFrom()[0] + "\t" + message[i].getSubject());
				/*
				 * String contenttype = message[i].getContentType();
				 * if(message[i].isMimeType("multipart/*")){ Multipart multipart
				 * = (Multipart)message[i].getContent(); int counts =
				 * multipart.getCount(); for(int j=0;j<counts;j++){
				 * getMailContent(multipart.getBodyPart(i)); } }
				 */
				// System.out.println((String)message[i].getContent());
			}
			folder.close(false);
			store.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getMailContent(Part part) throws Exception {
		String contenttype = part.getContentType();
		StringBuffer bodytext = new StringBuffer();
		int nameindex = contenttype.indexOf("name");
		boolean conname = false;
		if (nameindex != -1)
			conname = true;
		if (part.isMimeType("text/plain") && !conname) {
			bodytext.append((String) part.getContent());
		} else if (part.isMimeType("text/html") && !conname) {
			bodytext.append((String) part.getContent());
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			int counts = multipart.getCount();
			for (int i = 0; i < counts; i++) {
				getMailContent(multipart.getBodyPart(i));
			}
		} else if (part.isMimeType("message/rfc822")) {
			getMailContent((Part) part.getContent());
		} else {
		}
	}

	public static void main(String[] args) {
		/*
		 * Object[] obj = new Object[]{"测试用户","123456"};
		 * EMailUtil.sendMail("测试修改", "jiangleibo@gmail.com", obj,
		 * "E:/tools/ybkWeb/src/main/webapp/mailTemplate/forgetpwd.xml");
		 */

		// 测试发送
		Properties params = new Properties();
		params.put("host", "localhost");
		params.put("port", "25");
		params.put("user", "admin@localhost");
		params.put("pass", "1234");
		params.put("from", "admin@localhost");
		/*
		 * Object[] obj = newObject[]{"测试用户",
		 * "o98s4dfikjwkh2vy8zn3a6l9ip48j0w84h4zrlcr3iwc630xso39m4ra68utz713"};
		 * MailUtil.sendMail("密码找回ybk007.cn", "test@localhost", obj,
		 * "E:/tools/ybkMobe/ybk007/src/main/webapp/mailTemplate/forgetpwd.xml"
		 * ,params);
		 */
		params.put("user", "test");
		MailUtil.receiveMail(params);
	}
}