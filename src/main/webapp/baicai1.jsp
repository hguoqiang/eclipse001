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
</head>

<body>

<!-- 准备一个放图表的容器 -->
	<div id="main" style="width: 1200px; height: 600px;"></div>

	<script type="text/javascript">
		//初始化 echarts 实例
		var myChart = echarts.init(document.getElementById('main'));
		//设置图标配置项
		myChart.setOption({
			title : {
				text : '2017年11月份大白菜价格走势'
			},
			toolbox : {},
		
			legend : {
				data : [ '最低价','平均价','最高价' ]
			},
			
			xAxis : {
				data : []
			},
			
			yAxis : {},
			series : [
				{
					name : '最低价',
					type : 'line',
					data : []
				},
				{
					name : '平均价',
					type : 'line',
					data : []
				},{
					name : '最高价',
					type : 'line',
					data : []
				}
			]
		})
		
		
		//异步加载数据
		$.get('/day03-work/json/data.json').done(function(data) {
			data = eval(data);
			
			var time =new Array();
			
			var min_price = new Array();
			
			var max_price = new Array();
			
			var average_price = new Array();
			
			for(var i = 0;i<data.length;i++){
			
			var temp=data[i].release_date;
			
			
		    var temp2=temp.substr(8);
		    console.log(temp2);
			 time.push(temp2);
			
			min_price.push(data[i].min_price);
			
			max_price.push(data[i].max_price);
			
			average_price.push(data[i].average_price);
			
			
			
			}
		/* 	console.log(time)  */
			/* console.log(min_price) 
			console.log(max_price) 
			console.log(average_price)  */
			
			myChart.setOption({
				xAxis : {
					data :time
				},
				series : [
					{
						//根据名字对应到相应的系列
						name : "最低价",
						data : min_price
					},
					{
						//根据名字对应到相应的系列
						name : "平均价",
						data : average_price
					},
					{
						//根据名字对应到相应的系列
						name : "最高价",
						data : max_price
					}
				]
			})
			
		})
	</script>
</body>
</html>
