import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.ybk.basic.util.DateUtil;
import org.ybk.basic.util.StringUtil;

import com.kjplus.constant.Constant;

public class TestApiCall {

	public static void main(String argc[]){
		String opentoken = "IASLVc6bs6fnNjhNlWGwCe8o5IwZMTug";
		callFoo(opentoken);
	}
	
	private static void callFoo(String opentoken){
		
		String result = "";
		int timestatmp = DateUtil.getCurTimeInSec();
		//String url = "http://localhost:8080/kjplus/usck/foo.html?opentoken=" + opentoken;
		//String url = "http://59.110.42.139:9595/usck/foo.html?opentoken=" + opentoken;
		String url = "http://localhost:8080/kjplus/usck/logout.html?opentoken=" + opentoken;
		url += "&timestamp=" + timestatmp;
		String headCheckMd5 = StringUtil.md5(Constant.USER_CHECK_HEAD_TAG + opentoken + timestatmp);
		
		HttpGet httpRequst = new HttpGet(url);// 创建HttpPost对象

		try {
			Header h = new BasicHeader(Constant.USER_CHECK_HEAD, headCheckMd5);
			httpRequst.addHeader(h);
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
		System.out.println(result);
	}
}
