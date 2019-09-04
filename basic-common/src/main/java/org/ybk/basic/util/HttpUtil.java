package org.ybk.basic.util;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

import javax.net.ssl.SSLContext;  

import javax.net.ssl.TrustManager;  
import javax.net.ssl.X509TrustManager;  
import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.ResponseHandler;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.conn.ClientConnectionManager;  
  
import org.apache.http.conn.scheme.Scheme;  
import org.apache.http.conn.scheme.SchemeRegistry;  
import org.apache.http.conn.scheme.SchemeSocketFactory;  
import org.apache.http.conn.ssl.SSLSocketFactory;  
import org.apache.http.impl.client.BasicResponseHandler;  
import org.apache.http.impl.client.ClientParamsStack;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.params.DefaultedHttpParams;  
import org.apache.http.params.HttpParams; 

import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreProtocolPNames;

public class HttpUtil {
	private static Logger logger = Logger.getLogger(HttpUtil.class);
	private static DefaultHttpClient httpclient = new DefaultHttpClient();

	public static boolean fetchImageFileHttps(String url, String host, List<Cookie> cookies, String destFile) {

		//DefaultHttpClient client = new DefaultHttpClient();
		DefaultHttpClient httpclient2 = new DefaultHttpClient();  
		HttpGet httpget = null;
		try {
			// java.net.URISyntaxException: Illegal character in query at index
			URL url2 = new URL(url);
			URI uri2 = new URI(url2.getProtocol(), url2.getHost(), url2.getPath(), url2.getQuery(), null);

			SSLContext ctx = SSLContext.getInstance("SSL");
			// Implementation of a trust manager for X509 certificates
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}
				public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
				}
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ClientConnectionManager ccm = httpclient2.getConnectionManager();
			// register https protocol in httpclient's scheme registry
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));

			httpget = new HttpGet(uri2);
			
			Header head1 = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			Header head3 = new BasicHeader("Accept-Language", "zh-cn,zh;q=0.5");
			Header head4 = new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			Header head5 = new BasicHeader("Accept-Encoding", "gzip, deflate");
			Header head6 = new BasicHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; rv:6.0.1) Gecko/20100101 Firefox/6.0.1");
			Header head7 = new BasicHeader("Connection", "Keep-Alive");
			Header head8 = new BasicHeader("Cache-Control", "no-cache");
			Header head9 = new BasicHeader("Host", host);

			httpget.addHeader(head1);
			httpget.addHeader(head3);
			httpget.addHeader(head4);
			httpget.addHeader(head5);
			httpget.addHeader(head6);
			httpget.addHeader(head7);
			httpget.addHeader(head8);
			httpget.addHeader(head9);

			for (Cookie cookie : cookies)
				httpclient2.getCookieStore().addCookie(cookie);

			httpclient2.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			// 模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
			httpclient2.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");

			/*
			 * if (Util.isNotNull(referer)) httpget.setHeader("Referer",
			 * referer);
			 */

			InputStream is = null;
			try {
	            //responseBody = httpclient2.execute(httpget, responseHandler);  
				is = httpclient2.execute(httpget).getEntity().getContent();
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
				byte[] buf = new byte[2048];
				int length = is.read(buf);
				while (length != -1) {
					out.write(buf, 0, length);
					length = is.read(buf);
				}
				is.close();
				out.close();
			} catch (IOException ioe) {
				logger.error(ioe);
				return false;
			} catch (Exception e) {
				logger.error(e);
				return false;
			} finally {
				IOUtils.closeQuietly(is);
			}
		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			if (httpget != null)
				httpget.abort();
			httpclient.getConnectionManager().shutdown();
		}
		return true;
	}

	public static boolean fetchImageFile(String url, String host, List<Cookie> cookies, String destFile) {

		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpget = null;
		try {
			// java.net.URISyntaxException: Illegal character in query at index
			/*
			 * URL url2 = new URL(url); URI uri2 = new URI(url2.getProtocol(),
			 * url2.getHost(), url2.getPath(), url2.getQuery(), null); httpget =
			 * new HttpGet(uri2);
			 */
			httpget = new HttpGet(url);

			Header head1 = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			Header head3 = new BasicHeader("Accept-Language", "zh-cn,zh;q=0.5");
			Header head4 = new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			Header head5 = new BasicHeader("Accept-Encoding", "gzip, deflate");
			Header head6 = new BasicHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; rv:6.0.1) Gecko/20100101 Firefox/6.0.1");
			Header head7 = new BasicHeader("Connection", "Keep-Alive");
			Header head8 = new BasicHeader("Cache-Control", "no-cache");
			Header head9 = new BasicHeader("Host", host);

			httpget.addHeader(head1);
			httpget.addHeader(head3);
			httpget.addHeader(head4);
			httpget.addHeader(head5);
			httpget.addHeader(head6);
			httpget.addHeader(head7);
			httpget.addHeader(head8);
			httpget.addHeader(head9);

			for (Cookie cookie : cookies)
				client.getCookieStore().addCookie(cookie);

			client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			// 模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
			client.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");

			InputStream is = null;
			try {
				is = client.execute(httpget).getEntity().getContent();
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
				byte[] buf = new byte[2048];
				int length = is.read(buf);
				while (length != -1) {
					out.write(buf, 0, length);
					length = is.read(buf);
				}
				is.close();
				out.close();
			} catch (IOException ioe) {
				logger.error(ioe);
				return false;
			} catch (Exception e) {
				logger.error(e);
				return false;
			} finally {
				IOUtils.closeQuietly(is);
			}
		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			if (httpget != null)
				httpget.abort();
			httpclient.getConnectionManager().shutdown();
		}
		return true;
	}

	public boolean fetchFile(String url, String encoding, String host, String referer, String path) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		try {
			String filename = URLDecoder.decode(url, encoding);
			int index = filename.lastIndexOf("/");
			filename = filename.substring(index + 1);
			// path = path.substring(0,index);
			File file = new File(path);
			if (!file.exists())
				file.mkdir();
			file = new File(path + File.separator + filename);
			// File file = new File(path+filename);

			// TODO:修改了，删除操作
			/*
			 * if(file.exists()) return false;
			 */
			if (file.exists())
				file.delete();

			Header head1 = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			if (Util.isNotNull(referer)) {
				Header head2 = new BasicHeader("Referer", referer);
				httpget.addHeader(head2);
			}
			Header head3 = new BasicHeader("Accept-Language", "zh-cn,zh;q=0.5");
			Header head4 = new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			Header head5 = new BasicHeader("Accept-Encoding", "gzip, deflate");
			Header head6 = new BasicHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; rv:6.0.1) Gecko/20100101 Firefox/6.0.1");
			Header head7 = new BasicHeader("Connection", "Keep-Alive");
			Header head8 = new BasicHeader("Cache-Control", "no-cache");
			Header head9 = new BasicHeader("Host", host);

			httpget.addHeader(head1);
			httpget.addHeader(head3);
			httpget.addHeader(head4);
			httpget.addHeader(head5);
			httpget.addHeader(head6);
			httpget.addHeader(head7);
			httpget.addHeader(head8);
			httpget.addHeader(head9);

			client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			// 模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
			client.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");

			if (Util.isNotNull(referer))
				httpget.setHeader("Referer", referer);

			InputStream is = null;
			try {
				is = client.execute(httpget).getEntity().getContent();

				FileOutputStream out = FileUtils.openOutputStream(file);
				try {
					IOUtils.copy(is, out);
				} finally {
					IOUtils.closeQuietly(out);
				}
			} catch (IOException ioe) {
				logger.error(ioe);
				return false;
				// ioe.printStackTrace();
			} catch (Exception e) {
				logger.error(e);
				return false;
				// e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(is);
			}
		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			httpget.abort();
			httpclient.getConnectionManager().shutdown();
		}
		return true;
	}
	
	public static void main(String argc){
		/*String url = "https://user.qunar.com/captcha/api/image?k={en7mni(z&p=ucenter_login&c=ef7d278eca6d25aa6aec7272d57f0a9a&t=1435746146524";
		HttpUtil.fetchImageFileHttps(url,);*/
	}
}
