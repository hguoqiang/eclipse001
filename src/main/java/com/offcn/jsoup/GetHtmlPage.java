package com.offcn.jsoup;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetHtmlPage {

	// 抓取网页内容
	public String getHtml(String url) {

		 /*// 获取HTTPClient对象
		 CloseableHttpClient httpClient = new DefaultHttpClient();
		
		 // 设置响应时间，设置传输源码时间，设置代理服务器ip(防止封ip)
		 httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
		 10000) // 链接时间
		 .setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000) // 传输时间
		 .setParameter(ConnRouteParams.DEFAULT_PROXY, new
		 HttpHost("119.5.1.20", 22));*/

		//1，设置请求头，随时更换，防止被拒绝
		// 2. ip 要随时换 购买代理服务器
		// 3,让线程睡眠 ，随机数，随机睡眠
		
		// 获取HTTPClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 获取get请求方法
		HttpGet get = new HttpGet(url);

		// 发送请求,获取响应
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String string = null;
		if (response.getStatusLine().getStatusCode() == 200) {

			HttpEntity entity = response.getEntity();
			try {
				string = EntityUtils.toString(entity, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return string;
	}

}
