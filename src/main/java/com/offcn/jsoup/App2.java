package com.offcn.jsoup;

public class App2 {

	public static void main(String[] args) {
		// 要拼接的url
		StringBuffer url = new StringBuffer();
		
		// 首页  http://www.xinfadi.com.cn/marketanalysis/0/list/1.shtml
		// 尾页  http://www.xinfadi.com.cn/marketanalysis/0/list/14323.shtml
		
		String preUrl = "http://www.xinfadi.com.cn/marketanalysis/0/list/";
		String sufUrl = ".shtml";

			

		startWork(url, preUrl, sufUrl, 14201, 14323);
	}


	public static void startWork(StringBuffer url, String preUrl, String sufUrl, int fromPage, int endPage) {

		GetHtmlPage getHtmlPage = new GetHtmlPage();
		
		
		//解析到mysql
		HtmlParseToMysql parse = new HtmlParseToMysql();
		
		// 修改页码拼接请求，一次循环处理一个页面
		
		for (int i = fromPage; i <= endPage; i++) {
			// 清空urlBuffer，重新组装url

			if (url.capacity() > 0) {
				url.delete(0, url.capacity());
			}
			url.append(preUrl).append(i).append(sufUrl);

			// 访问url，获取html
			String html = getHtmlPage.getHtml(url.toString());
			//解析并完成
			parse.parseHtmlToMySql(html);

		

			System.out.println("第" + i + "页完成");
		}
		
	}

}
