<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <script src="js/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.2.1.js"></script>
<style type="text/css">
	div{
		float: left;
	}
</style>
</head>
<body>

<div id="main" style="width: 600px; height: 400px;"></div>
<div id="demo"></div>
<script type="text/javascript">
var myChart = echarts.init(document.getElementById('main'));
var app = {};
	$(function(){
		//页面加载函数就会执行
		//页面加载异步查询字典数据
		$.post("${pageContext.request.contextPath }/test_Picture2.action",{},function(data){
			
			var data1 = data["data1"];
			var huafen = data["huafen"];
			option = {
					title:{
						text:'年龄段与购买力',
					},
					toolbox:{
						feature: {
						    magicType: {
						        type: ['line', 'bar', 'stack', 'tiled']
						    }
						}
					},
				    xAxis: {
				    	name:'年龄段',
				        type: 'category',
				        data: huafen,
				    },
				    yAxis: {
				    	name:'购买力',
				        type: 'value'
				    },
				    series: [{
				        data: data1,
				        type: 'bar',
				        color:'#F08080',
				    }]
				};

			 myChart.setOption(option, true);
		},"json")
	});
</script>
</body>
</html>