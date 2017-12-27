<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/Jquery.1.12.4.min.js"></script>
<script type="text/javascript" src="js/echarts.js"></script>
<style type="text/css">

.box1 {
	width: 1300;
	margin: 0 auto;
	border:1px solid #000;
}
div{
margin: 0 auto;
}
table{
margin: 0 auto;
}
h3 {
	text-align: center;
	
}
span{
font-size:16px;
}
p{
text-indent:2em;
}
</style>
</head>

<body>
	<div class="box1">
		<h3>2017年11月与2016年同期大白菜价格走势 <span>（单位：元/斤）</span></h3> 
		<table border=1 style="border-collapse:collapse" width=1200px>

			<tr id=time>
				<td></td>
			</tr>
			<tr id=td2016>
				<td><font color=red>2016年</font></td>
			</tr>
			<tr id=td2017>
				<td><font color=blue>2017年</font></td>
			</tr>
		</table>

	<!-- 准备一个放图表的容器 -->
	<div id="main" style="width: 1200px; height: 300px;"></div>
	
	<div >
	<p>2017年11月1日，<span class="name"></span>大白菜批发平均价是<span id=""></span>0.35元/斤，11月30日，价格是9.30元/斤，月末比月初上涨5.68%。月末的价格比去年同期的10.33元/斤下降9.97%。</p>

    <p>月内的最高价是9.30元/斤（11月30日），比10月份的8.98元/斤上涨3.56%；比9月份的9.15元/斤上涨1.64%；比8月份的9.45元/斤下降1.59%；比7月份的8.95元/斤上涨3.91%。</p>

   <p> 月内最低价是8.70元/斤（11月2日），比10月份的8.55元/斤上涨1.75%；比9月份的8.75元/斤下降0.57%；与8月份的8.70元/斤持平；比7月份的8.10元/斤上涨7.41%。</p>

   <p> 月内的最高价比最低价高出的幅度为6.90%，波动的幅度大于10月份的5.03%。</p>

    <p>2017年11月份，新发地市场白条猪批发的平均价是8.92元/斤，比10月份的8.69元/斤上涨2.65%；比9月份的8.97元/斤下降0.56%；比8月份的9.03元/斤下降1.22%；比7月份的8.69元/斤上涨2.65%。</p>

    <p>2017年11月份的价格比2016年同期的10.18元/斤下降12.38%；比2015年同期的10.02元/斤下降10.98%；比2014年同期的8.38元/斤上涨6.44%；比2013年同期的9.76元/斤下降8.60%。</p></div>

	<div><button onclick="fun()">生成PDF文档</button></div>
	<script type="text/javascript">
		//初始化 echarts 实例
		var myChart = echarts.init(document.getElementById('main'));
		//设置图标配置项
		myChart.setOption({
			title : {
				text : ''
			},
			toolbox : {},
	
			legend : {
				data : [ '2016', '2017' ]
			},
	
			xAxis : {
				data : []
			},
	
			yAxis : {},
			series : [
				{
					name : '2016',
					type : 'line',
					data : []
				},
				{
					name : '2017',
					type : 'line',
					data : []
				}
	
			]
		})
	
	
		//异步加载数据
		$.get('/day03-work/json/data2.json').done(function(data) {
			data = eval(data);
	
			console.log((data[0].list1)[0].release_date);
			console.log((data[0].list2)[0].release_date);
	
			var time = new Array(); //横轴时间1-30
	
			for (i = 0; i < time.length; i++) {
	
	
			}
	
			var average_price2016 = new Array(); // 2016年11月份平均价
			var average_price2017 = new Array(); // 2017年11月份平均价
	
			for (var i = 0; i < 30; i++) {
	
	
	
				var temp = (data[0].list1)[i].release_date;
	
	
				var newTemp = temp.substr(8);
	
				time.push(newTemp);
	
				$('#time').append('<td>' + newTemp + '</td>')
	
				average_price2016.push((data[0].list1)[i].average_price);
	
				$('#td2016').append('<td>' + ((data[0].list1)[i].average_price) + '</td>')
	
				average_price2017.push((data[0].list2)[i].average_price);
	
				$('#td2017').append('<td>' + ((data[0].list2)[i].average_price) + '</td>')
	
			}
	
			myChart.setOption({
				xAxis : {
					data : time //横轴时间1-30
				},
				series : [
					{
						//根据名字对应到相应的系列
						name : "2016",
						data : average_price2016
					},
					{
						//根据名字对应到相应的系列
						name : "2017",
						data : average_price2017
					}
	
				]
			})
	
		})
	</script>
	</div>
</body>
</html>
