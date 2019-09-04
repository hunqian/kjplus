package org.ybk.basic.http;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.ybk.basic.util.Util;

public class HttpUtil2 {

	public static String doGet(String url, String encoding) {

		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		try {

			Header head1 = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			Header head3 = new BasicHeader("Accept-Language", "zh-cn,zh;q=0.5");
			Header head4 = new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			Header head5 = new BasicHeader("Accept-Encoding", "gzip, deflate");
			Header head6 = new BasicHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; rv:6.0.1) Gecko/20100101 Firefox/6.0.1");
			Header head7 = new BasicHeader("Connection", "Keep-Alive");
			Header head8 = new BasicHeader("Cache-Control", "no-cache");
			Header head9 = null;

			httpget.addHeader(head1);
			httpget.addHeader(head3);
			httpget.addHeader(head4);
			httpget.addHeader(head5);
			httpget.addHeader(head6);
			httpget.addHeader(head7);
			httpget.addHeader(head8);
			/*if (Util.isNotNull(cookies)) {
				String[] cks = cookies.split(";");
				for (String ckstr : cks) {
					if (Util.isNull(ckstr))
						continue;
					ckstr = ckstr.trim();
					if (Util.isNull(ckstr))
						continue;
					String params[] = ckstr.split("[\\=]");
					BasicClientCookie cookie = new BasicClientCookie(params[0], params[1]);
					cookie.setDomain(host);
					client.getCookieStore().addCookie(cookie);
				}
			}*/

			InputStream is = null;
			try {
				HttpResponse httpResponse = client.execute(httpget);
				boolean isZipped = false;
				Header heads[] = httpResponse.getAllHeaders();
				String hdv = null;
				String hdn = null;
				encoding = "GB2312";
				for (int j = 0; j < heads.length; j++) {
					hdv = heads[j].getValue();
					hdn = heads[j].getName();
					if (Util.isNull(hdv))
						continue;
					hdv = hdv.toLowerCase();
					hdn = hdn.toLowerCase();
					if (hdn.equals("content-encoding")) {
						if (hdv.indexOf("gzip") > -1) {
							isZipped = true;
						}
					} else if (hdn.equals("content-type")) {
						String hdvs[] = hdv.split(";");
						hdv = hdvs[hdvs.length - 1];
						if (Util.isNotNull(hdv)) {
							hdvs = hdv.split("[\\=]");
							if (hdvs.length > 0)
								encoding = hdvs[hdvs.length - 1];
						}
					}
				}

				if (isZipped) {
					is = httpResponse.getEntity().getContent();

					int totalRead = 1;
					int totalRecord = 32 * 1024;
					byte[] bt = new byte[0];
					while (totalRead > 0) {
						byte[] b = new byte[totalRecord];
						totalRead = is.read(b);
						if (totalRead > 0) {
							byte[] bt2 = new byte[totalRead + bt.length];
							System.arraycopy(bt, 0, bt2, 0, bt.length);
							System.arraycopy(b, 0, bt2, bt.length, totalRead);
							bt = new byte[bt2.length];
							System.arraycopy(bt2, 0, bt, 0, bt2.length);
						}
					}

					ByteArrayOutputStream out = new ByteArrayOutputStream();
					ByteArrayInputStream in = new ByteArrayInputStream(bt);
					GZIPInputStream gunzip = new GZIPInputStream(in);

					byte[] buffer = new byte[256];
					int n;
					while ((n = gunzip.read(buffer)) >= 0) {
						out.write(buffer, 0, n);
					}
					out.close();
					gunzip.close();
					return out.toString(encoding);
				} else {
					return EntityUtils.toString(httpResponse.getEntity(), encoding);
				}
			} catch (IOException ioe) {
				// logger.error(ioe);
				return null;
				// ioe.printStackTrace();
			} catch (Exception e) {
				// logger.error(e);
				return null;
				// e.printStackTrace();
			} catch (Throwable t) {
				t.printStackTrace();
				// e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(is);
			}
		} catch (Exception e) {
			// logger.error(e);
			return null;
		} finally {
			httpget.abort();
			client.getConnectionManager().shutdown();
		}
		return null;		
	}
	
	/*public static String doGet(String url, String encoding) {
		String result = "";
		HttpGet httpRequst = new HttpGet(url);// 创建HttpPost对象

		try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity, encoding);// 取出应答字符串
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;
	}*/

	public static String doGet(String url) {
		String result = "";
		HttpGet httpRequst = new HttpGet(url);// 创建HttpPost对象

		try {
			// httpRequst.setEntity(new StringEntity(xmlStr, HTTP.UTF_8));
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);// 取出应答字符串
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;
	}

	public static String doPost(String url, String xmlStr) {
		String result = "";
		HttpPost httpRequst = new HttpPost(url);// 创建HttpPost对象

		try {
			httpRequst.setEntity(new StringEntity(xmlStr, HTTP.UTF_8));
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);// 取出应答字符串
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;
	}

	public static String doPost(String url, String host, String encoding,
			Map<String, String> params, String referer, String cookies, Map<String, String> heads){

		String result = "";
		DefaultHttpClient client = new DefaultHttpClient();
		// 创建HttpPost对象
		HttpPost httpPost = new HttpPost(url);

		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				nvps.add(new BasicNameValuePair(key, params.get(key)));
			}
			if("utf-8".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			else if("utf8".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			else if("utf-16".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_16));
			else if("utf16".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_16));
			else if("gbk".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "gbk"));
			else if("gb2312".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "gb2312"));
			
			if(heads != null){
				Iterator<String> keys = heads.keySet().iterator();
				String k = null;
				while(keys.hasNext()){
					k = keys.next();
					if(Util.isNotNull(heads.get(k))){
						Header h = new BasicHeader(k, heads.get(k));
						httpPost.addHeader(h);
					}
				}
			}
			
			if (Util.isNotNull(referer))
				httpPost.setHeader("Referer", referer);
			if (Util.isNotNull(cookies)) {
				String[] cks = cookies.split(";");
				for (String ckstr : cks) {
					if (Util.isNull(ckstr))
						continue;
					ckstr = ckstr.trim();
					if (Util.isNull(ckstr))
						continue;
					String cks2[] = ckstr.split("[\\=]");
					BasicClientCookie cookie = new BasicClientCookie(cks2[0], cks2[1]);
					cookie.setDomain(host);
					// cookie.setPath(cfg.getPath());
					client.getCookieStore().addCookie(cookie);
				}
			}
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity, Charset.forName(encoding));// 取出应答字符串
			}
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			result = e.getMessage().toString();
		} catch (ClientProtocolException e) {
			//e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;
	
	}
	
	public static String doPost(String url, String host, String encoding,
			Map<String, String> params, String referer, String cookies){
		String result = "";
		DefaultHttpClient client = new DefaultHttpClient();
		// 创建HttpPost对象
		HttpPost httpPost = new HttpPost(url);

		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				nvps.add(new BasicNameValuePair(key, params.get(key)));
			}
			if("utf-8".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			else if("utf8".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			else if("utf-16".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_16));
			else if("utf16".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_16));
			else if("gbk".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "gbk"));
			else if("gb2312".equalsIgnoreCase(encoding))
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "gb2312"));
			
			if (Util.isNotNull(referer))
				httpPost.setHeader("Referer", referer);
			if (Util.isNotNull(cookies)) {
				String[] cks = cookies.split(";");
				for (String ckstr : cks) {
					if (Util.isNull(ckstr))
						continue;
					ckstr = ckstr.trim();
					if (Util.isNull(ckstr))
						continue;
					String cks2[] = ckstr.split("[\\=]");
					BasicClientCookie cookie = null;
					if(cks2.length == 2)
						cookie = new BasicClientCookie(cks2[0], cks2[1]);
					else
						cookie = new BasicClientCookie(cks2[0], "");
					cookie.setDomain(host);
					// cookie.setPath(cfg.getPath());
					client.getCookieStore().addCookie(cookie);
				}
			}
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity, Charset.forName(encoding));// 取出应答字符串
			}
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			result = e.getMessage().toString();
		} catch (ClientProtocolException e) {
			//e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;
	}
	
	public static String doPostForm(String url, String encoding,Map<String, String> params) {
		String result = "";
		HttpPost httpRequst = new HttpPost(url);// 创建HttpPost对象

		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				nvps.add(new BasicNameValuePair(key, params.get(key)));
			}
			if("utf-8".equalsIgnoreCase(encoding))
				httpRequst.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			else if("utf8".equalsIgnoreCase(encoding))
				httpRequst.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			else if("utf-16".equalsIgnoreCase(encoding))
				httpRequst.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_16));
			else if("utf16".equalsIgnoreCase(encoding))
				httpRequst.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_16));
			else if("gbk".equalsIgnoreCase(encoding))
				httpRequst.setEntity(new UrlEncodedFormEntity(nvps, "gbk"));
			else if("gb2312".equalsIgnoreCase(encoding))
				httpRequst.setEntity(new UrlEncodedFormEntity(nvps, "gb2312"));
			Header head1 = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,*/*;q=0.8");
			Header head3 = new BasicHeader("Accept-Language", "zh-cn,zh;q=0.5");
			//Header head4 = new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			Header head5 = new BasicHeader("Accept-Encoding", "gzip, deflate");
			Header head6 = new BasicHeader("USER-AGENT",
					"Mozilla/5.0 (Linux; Android 6.0; HUAWEI NXT-DL00 Build/HUAWEINXT-DL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/49.0.2623.105 Mobile Safari/537.36 Html5Plus/1.0");
			Header head7 = new BasicHeader("Connection", "Keep-Alive");
			Header head8 = new BasicHeader("Cache-Control", "no-cache");
			httpRequst.addHeader(head1);
			httpRequst.addHeader(head3);
			//httpRequst.addHeader(head4);
			httpRequst.addHeader(head5);
			httpRequst.addHeader(head6);
			httpRequst.addHeader(head7);
			httpRequst.addHeader(head8);
			
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity, Charset.forName("UTF-8"));// 取出应答字符串
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;
	}
	
	public static String doPostForm(String url, Map<String, String> params) {
		return doPostForm(url,"utf8",params);
	}

	public static String doGet(String url, String host, String referer, String cookies) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		try {

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
			Header head9 = null;
			if (Util.isNotNull(host))
				head9 = new BasicHeader("Host", host);

			httpget.addHeader(head1);
			httpget.addHeader(head3);
			httpget.addHeader(head4);
			httpget.addHeader(head5);
			httpget.addHeader(head6);
			httpget.addHeader(head7);
			httpget.addHeader(head8);
			if (Util.isNotNull(host))
				httpget.addHeader(head9);

			// client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
			// HttpVersion.HTTP_1_1);
			// 模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
			// client.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
			// "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");

			if (Util.isNotNull(referer))
				httpget.setHeader("Referer", referer);
			if (Util.isNotNull(cookies)) {
				String[] cks = cookies.split(";");
				for (String ckstr : cks) {
					if (Util.isNull(ckstr))
						continue;
					ckstr = ckstr.trim();
					if (Util.isNull(ckstr))
						continue;
					String params[] = ckstr.split("[\\=]");
					BasicClientCookie cookie = new BasicClientCookie(params[0], params[1]);
					cookie.setDomain(host);
					// cookie.setPath(cfg.getPath());
					client.getCookieStore().addCookie(cookie);
				}
			}

			InputStream is = null;
			try {
				HttpResponse httpResponse = client.execute(httpget);
				boolean isZipped = false;
				Header heads[] = httpResponse.getAllHeaders();
				String hdv = null;
				String hdn = null;
				String encoding = "GB2312";
				for (int j = 0; j < heads.length; j++) {
					hdv = heads[j].getValue();
					hdn = heads[j].getName();
					if (Util.isNull(hdv))
						continue;
					hdv = hdv.toLowerCase();
					hdn = hdn.toLowerCase();
					if (hdn.equals("content-encoding")) {
						if (hdv.indexOf("gzip") > -1) {
							isZipped = true;
						}
					} else if (hdn.equals("content-type")) {
						String hdvs[] = hdv.split(";");
						hdv = hdvs[hdvs.length - 1];
						if (Util.isNotNull(hdv)) {
							hdvs = hdv.split("[\\=]");
							if (hdvs.length > 0)
								encoding = hdvs[hdvs.length - 1];
						}
					}
				}

				if (isZipped) {
					is = httpResponse.getEntity().getContent();

					int totalRead = 1;
					int totalRecord = 32 * 1024;
					byte[] bt = new byte[0];
					while (totalRead > 0) {
						byte[] b = new byte[totalRecord];
						totalRead = is.read(b);
						if (totalRead > 0) {
							byte[] bt2 = new byte[totalRead + bt.length];
							System.arraycopy(bt, 0, bt2, 0, bt.length);
							System.arraycopy(b, 0, bt2, bt.length, totalRead);
							bt = new byte[bt2.length];
							System.arraycopy(bt2, 0, bt, 0, bt2.length);
						}
					}

					ByteArrayOutputStream out = new ByteArrayOutputStream();
					ByteArrayInputStream in = new ByteArrayInputStream(bt);
					GZIPInputStream gunzip = new GZIPInputStream(in);

					byte[] buffer = new byte[256];
					int n;
					while ((n = gunzip.read(buffer)) >= 0) {
						out.write(buffer, 0, n);
					}
					out.close();
					gunzip.close();
					return out.toString(encoding);
				} else {
					return EntityUtils.toString(httpResponse.getEntity(), encoding);
				}
			} catch (IOException ioe) {
				// logger.error(ioe);
				return null;
				// ioe.printStackTrace();
			} catch (Exception e) {
				// logger.error(e);
				return null;
				// e.printStackTrace();
			} catch (Throwable t) {
				t.printStackTrace();
				// e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(is);
			}
		} catch (Exception e) {
			// logger.error(e);
			return null;
		} finally {
			httpget.abort();
			client.getConnectionManager().shutdown();
		}
		return null;
	}
}
