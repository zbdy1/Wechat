package cxg.weChat.httpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * 请求基类。
 * 
 * @author Administrator
 *
 */
public class WeChatQuest {
	private String path = "";
	List<NameValuePair> params = null;
	private HttpContext httpContext = null;
	private HashMap<String, String> header;
	private boolean postFlag = false;

	public WeChatQuest(String path, List<NameValuePair> params) {
		this.path = path;
		this.params = params;
	}

	public WeChatQuest(String path, List<NameValuePair> params, boolean postFlag) {
		this.path = path;
		this.params = params;
		this.postFlag = postFlag;
	}

	public void setHttpContext(HttpContext httpContext) {
		this.httpContext = httpContext;
	}

	public HttpContext getHttpContext() {
		return httpContext;
	}

	public String run() {
		String result = null;
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest post = null;
		if (postFlag) {
			post = new HttpPost(path);
			try {
				((HttpPost) post).setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (params.size() > 0) {
				path += "?" + params.get(0).getName() + "="
						+ params.get(0).getValue();
				for (int i = 1; i < params.size(); i++) {
					path += "&" + params.get(i).getName() + "="
							+ params.get(i).getValue();
				}
			}
		}
		post = new HttpGet(path);
		if (header != null) {
			for (String key : header.keySet()) {
				post.addHeader(key, header.get(key));
			}
		}
		try {
			if (httpContext == null) {
				httpContext = HttpContextCenter.getContext();
			}
			HttpResponse response = client.execute(post, httpContext);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				InputStreamReader isr = new InputStreamReader(resEntity.getContent());
				BufferedReader br = new BufferedReader(isr);
				StringBuffer sb = new StringBuffer();
				String s = null;
				while ((s = br.readLine()) != null) {
					sb.append(new String(s.getBytes("UTF-8")));
				}
				result = unicode2Str(sb.toString());

			}
			if (resEntity != null) {
				resEntity.consumeContent();
			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public HashMap<String, String> getHeader() {
		return header;
	}

	public void setHeader(HashMap<String, String> header) {
		this.header = header;
	}

	public static String doGet(String url, List<NameValuePair> params) {
		String result = null;
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest post = null;
		if (params.size() > 0) {
			url += "?" + params.get(0).getName() + "="
					+ params.get(0).getValue();
			for (int i = 1; i < params.size(); i++) {
				url += "&" + params.get(i).getName() + "="
						+ params.get(i).getValue();
			}
		}
		post = new HttpGet(url);
		try {
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(response.getEntity(),"UTF-8");// 返回json格式：
				if (entity != null) {
					entity.consumeContent();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * post请求
	 * 
	 * @param url
	 * @param json
	 * @return
	 */

	public static String doPost(String url, String json) {
		String result = null;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		try {
			StringEntity s = new StringEntity(str2Unicode(json.replaceAll("%", "%25")));
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// 发送json数据需要设置contentType
			System.out.println(s.toString());
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				result = EntityUtils.toString(res.getEntity(),"UTF-8");
				//result = EntityUtils.toString(res.getEntity());// 返回json格式：
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	public static String str2Unicode(String str) {
		StringBuffer sb = new StringBuffer();
		char[] charArr = str.toCharArray();
		for (char ch : charArr) {
			if (ch > 128) {
				sb.append("\\u" + Integer.toHexString(ch));
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
	public String unicode2Str(String str) {
		StringBuffer sb = new StringBuffer();
		String[] arr = str.split("\\\\u");
		int len = arr.length;
		sb.append(arr[0]);
		for (int i = 1; i < len; i++) {
			String tmp = arr[i];
			char c = (char) Integer.parseInt(tmp.substring(0, 4), 16);
			sb.append(c);
			sb.append(tmp.substring(4));
		}
		return sb.toString();
	}
}
