package com.offcn.jsoup;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.offcn.pojo.Product;

public class App3 {

	private static Connection connection = C3P0Utils.getConnection();
	
	private static PreparedStatement ps=null;
	
	public static void main(String[] args) {
		// 要拼接的url
		StringBuffer url = new StringBuffer();
		
		// 首页  http://www.xinfadi.com.cn/marketanalysis/0/list/1.shtml
		// 尾页  http://www.xinfadi.com.cn/marketanalysis/0/list/14323.shtml
		
		String preUrl = "http://www.xinfadi.com.cn/marketanalysis/0/list/";
		String sufUrl = ".shtml";

			

		startWork(url, preUrl, sufUrl, 1, 14338);
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
			Document doc = Jsoup.parse(html);

			Element table = doc.getElementsByClass("hq_table").first();

			Element tbody = table.children().first();

			// Elements trs =tbody.children();// 所有的 tr，第一个tr是表头，去掉，每个tr 里面有八个td,最后一个td没有用，去掉

			Element tr_1 = tbody.child(0); // 得到表头节点，去掉
			
			Elements trs = tr_1.siblingElements(); // 表头节点的所有兄弟节点

			
		
			for (int x = 0; x < trs.size(); x++) { // 遍历每一行，取前七个子元素td
				
				Product product = new Product();
				
				Element element = trs.get(x); // 得到每一行是tr，
				// 得到前七个td的文本内容
				String td1 = element.child(0).text();// 名字
				String td2 = element.child(1).text();// 最低价
				String td3 = element.child(2).text();// 平均价
				String td4 = element.child(3).text();// 最高价
				String td5 = element.child(4).text();// 规格类型
				String td6 = element.child(5).text();// 计量单位
				String td7 = element.child(6).text();// 发布时间
				
				
				String newTd2 = StringUtils.strSpilt(td2);
				String newTd3 = StringUtils.strSpilt(td3);
				String newTd4 = StringUtils.strSpilt(td4);
				
				

				product.setName(td1);
				product.setMin_price(Double.parseDouble(newTd2));
				product.setAverage_price(Double.parseDouble(newTd3));
				product.setMax_price(Double.parseDouble(newTd4));
				product.setType(td5);
				product.setMeasure_unit(td6);
				product.setRelease_date(td7);
				
				try {
					ps = connection.prepareStatement(
							"insert into productthree (name,min_price,average_price,max_price,type,measure_unit,release_date)values(?,?,?,?,?,?,?)");

					ps.setString(1, product.getName());
					ps.setDouble(2, product.getMin_price());
					ps.setDouble(3, product.getAverage_price());
					ps.setDouble(4, product.getMax_price());
					ps.setString(5, product.getType());
					ps.setString(6, product.getMeasure_unit());
					ps.setString(7, product.getRelease_date());
					
					ps.addBatch();
					
					try {
						ps.executeBatch();
					//Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				

			}
			

			System.out.println("第" + i + "页完成");
		}
		
	}

}
