package com.offcn.jsoup;

public class App {
	// 大白次2017年11月1-30 两页数据
	// 拼接url， preUrl+页码+sufUrl 循环访问每个页面。
	// 第一页
	// http://www.xinfadi.com.cn/marketanalysis/1/list/1.shtml?prodname=%E5%A4%A7%E7%99%BD%E8%8F%9C&begintime=2017-11-01&endtime=2017-11-30
	// 第二页
	// http://www.xinfadi.com.cn/marketanalysis/1/list/2.shtml?prodname=%E5%A4%A7%E7%99%BD%E8%8F%9C&begintime=2017-11-01&endtime=2017-11-30
	
	
	
	// 大白菜 2016年 11月1-30 两页数据
	// 第一页 http://www.xinfadi.com.cn/marketanalysis/1/list/1.shtml?prodname=%E5%A4%A7%E7%99%BD%E8%8F%9C&begintime=2016-11-01&endtime=2016-11-30
	// 第二页http://www.xinfadi.com.cn/marketanalysis/1/list/2.shtml?prodname=%E5%A4%A7%E7%99%BD%E8%8F%9C&begintime=2016-11-01&endtime=2016-11-30
	
	
	
	
	
	public static void main(String[] args) {
		// 要拼接的url
		StringBuffer url = new StringBuffer();

		// 大白次 2017年11月1-30 两页数据
//		String preUrl = "http://www.xinfadi.com.cn/marketanalysis/1/list/";
//		String sufUrl = ".shtml?prodname=%E5%A4%A7%E7%99%BD%E8%8F%9C&begintime=2017-11-01&endtime=2017-11-30";
//
//		Excel excel = new Excel("d:/chart/大白菜2017年11月1-30.xlsx", "白菜1");
		
//		// 大白菜 2016年 11月1-30 两页数据
//		String preUrl = "http://www.xinfadi.com.cn/marketanalysis/1/list/";
//		String sufUrl = ".shtml?prodname=%E5%A4%A7%E7%99%BD%E8%8F%9C&begintime=2016-11-01&endtime=2016-11-30";
//
//		Excel excel = new Excel("d:/chart/大白菜2016年11月1-30.xlsx", "白菜1");
		
		
		
		// 首页  http://www.xinfadi.com.cn/marketanalysis/0/list/1.shtml
		// 尾页  http://www.xinfadi.com.cn/marketanalysis/0/list/14323.shtml
		String preUrl = "http://www.xinfadi.com.cn/marketanalysis/0/list/";
		String sufUrl = ".shtml";

		Excel excel = new Excel("d:/chart/2016年1月-2016年12月 30日产品表明细.xlsx", "表2");	

		startWork(url, preUrl, sufUrl, 1, 2,excel);
	}
	
	
	
	
	

	public static void startWork(StringBuffer url, String preUrl, String sufUrl, int fromPage, int endPage,Excel excel) {

		GetHtmlPage getHtmlPage = new GetHtmlPage();
		

		// 创建解析器到excel
		HtmlParse parse = new HtmlParse();
		
		
		// 修改页码拼接请求，一次循环处理一个页面
		for (int i = fromPage; i <= endPage; i++) {
			// 清空urlBuffer，重新组装url

			if (url.capacity() > 0) {
				url.delete(0, url.capacity());
			}
			url.append(preUrl).append(i).append(sufUrl);

			// 访问url，获取document
			String html = getHtmlPage.getHtml(url.toString());
			
			

			if (i > 1) {
				// 解析document,第二页及以后页解析
				parse.parseHtmlToExcel(html, excel);
			} else {
				// 第一页解析
				parse.parseHtml2ToExcel(html, excel);
			}

			System.out.println("第" + i + "页完成");
		}
		
		// 数据存档到excel
		excel.saveFile();
		System.out.println("一共完成：" + excel.size() + "条");
	}

}
