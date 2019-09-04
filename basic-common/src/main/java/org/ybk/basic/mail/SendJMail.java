package org.ybk.basic.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.ybk.basic.util.Util;

public class SendJMail {// 发送邮件

	public static void sendMail() {
		// String host = "192.168.1.98"; // 指定的smtp服务器，本机的局域网IP
		String host = "localhost"; // 本机smtp服务器
		// String host = "smtp.163.com"; // 163的smtp服务器
		// String from = "admin@ybk007.cn"; // 邮件发送人的邮件地址
		String from = "datacube888@126.com"; // 邮件发送人的邮件地址
		String to = "986597712@qq.com"; // 邮件接收人的邮件地址
		final String username = "datacube888"; // 发件人的邮件帐户
		final String password = "datacb!@#$6587"; // 发件人的邮件密码

		// 创建Properties 对象
		Properties props = System.getProperties();

		// 添加smtp服务器属性
		/*
		 * props.put("mail.smtp.host", host); props.put("mail.smtp.port",
		 * "1225"); props.put("mail.smtp.auth", "true");
		 */

		props.put("mail.smtp.host", "smtp.126.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");

		// 创建邮件会话
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// 定义邮件信息
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// message.setSubject(transferChinese("我有自己的邮件服务器了"));
			message.setSubject("I hava my own mail server");
			message.setText("From now, you have your own mail server, congratulation!");

			// 发送消息
			session.getTransport("smtp").send(message);
			// Transport.send(message); //也可以这样创建Transport对象发送
			System.out.println("SendMail Process Over!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	// 测试发送
	public static void testSendMail() {
		Map<String, String> params = new HashMap<String, String>();
		params.put(MailConstant.MAIL_HOST, "smtp.126.com");
		params.put(MailConstant.MAIL_FROM, "datacube888@126.com");
		params.put(MailConstant.MAIL_USER, "datacube888");
		params.put(MailConstant.MAIL_PASS, "datacb!@#$6587");
		String receivers = "986597712@qq.com; 769285144@qq.com";
		String subject = "国内首个科学家讲给小朋友听的亲子类科教产品上线了";
		String content = "近日，国内首个面向亲子科学教育的音频节目——科学队长正式上线，这是国内最具有影响力的科学传播和教育平台“知识分子”推出的音频付费产品，也是目前国内首次邀请一线科学家担纲主讲人的音频产品。";
		sendMail(subject, content, receivers, params);
	}

	// 发送邮件
	public static int sendMail(String subject, String content, String receivers, Map<String, String> params) {

		// 直接返回
		if (Util.isNull(receivers))
			return -1;

		String from = params.get(MailConstant.MAIL_FROM); // 邮件发送人的邮件地址
		final String username = params.get(MailConstant.MAIL_USER); // 发件人的邮件帐户
		final String password = params.get(MailConstant.MAIL_PASS); // 发件人的邮件密码

		// 创建Properties 对象
		Properties props = System.getProperties();
		props.put("mail.smtp.host", params.get(MailConstant.MAIL_HOST));
		if (params.containsKey(MailConstant.MAIL_PORT))
			props.put("mail.smtp.port", params.get(MailConstant.MAIL_HOST));
		else
			props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");

		// 创建邮件会话
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		int sendTotal = 0;
		try {
			List<String> toArr = Util.str2Array(receivers);
			if (toArr.size() == 1)
				toArr = Util.str2Array(receivers, ";");
			for (String to : toArr) {
				long btime = System.currentTimeMillis();
				// 定义邮件信息
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.addRecipients(Message.RecipientType.TO, Util.trim(to));
				if (Util.isNotNull(subject))
					message.setSubject(subject);
				else
					message.setSubject("[NoSubject]");
				message.setText(content);
				// 发送消息
				session.getTransport("smtp").send(message);
				long etime = System.currentTimeMillis();
				sendTotal++;
				// Transport.send(message); //也可以这样创建Transport对象发送
				System.out.println("[SendMail]" + to + ",时间=" + (etime - btime));
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return sendTotal;
	}

	// 接受邮件
	public static void getMail() {
		String host = "localhost";
		final String username = "zph";
		final String password = "zph";

		// 创建Properties 对象
		Properties props = new Properties();

		// 创建邮件会话
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// 获取邮箱的pop3存储
			Store store = session.getStore("pop3");
			store.connect(host, username, password);

			// 获取inbox文件
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY); // 打开，打开后才能读取邮件信息

			// 获取邮件消息
			Message message[] = folder.getMessages();

			for (int i = 0, n = message.length; i < n; i++) {
				System.out.println(i + ": " + message[i].getFrom()[0] + "\t" + message[i].getSubject());
				try {
					message[i].writeTo(System.out);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 关闭资源
			folder.close(false);
			store.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("GetMail Process Over!");
	}

	// 邮件主题中文字符转换
	public static String transferChinese(String strText) {
		try {
			strText = MimeUtility.encodeText(new String(strText.getBytes(), "GB2312"), "GB2312", "B");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strText;
	}

	public static void main(String[] args) {
		SendJMail.testSendMail();
		// SendJMail.sendMail();
		// HelloJMail.getMail();
	}
}