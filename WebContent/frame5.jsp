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
		$.post("${pageContext.request.contextPath }/test_Picture3.action",{},function(data){
			var huafen="";
			for(var i in data){
				//console.log(data[i]);
				//console.log(data[i]["name"]);
				huafen+="\'"+data[i]["name"]+"\',";
			}
			var data2 = "["+huafen+"]";
			console.log(data2);
			var json = eval('(' + data2 + ')'); 
			option = {
				    title : {
				        text: '年龄段与购买力',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        left: 'left',
				        data: json
				    },
				    series : [
				        {
				            name: '年龄段与购买力',
				            type: 'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
				            data:data,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};


			 myChart.setOption(option, true);
		},"json")
	});
</script>
</body>
</html>