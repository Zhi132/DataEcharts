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
	$(function(){
		//页面加载函数就会执行
		//页面加载异步查询字典数据
		$.post("${pageContext.request.contextPath }/zuizong.json",{},function(data){
			//遍历json的数据
			//alert(data);
			var t= 1;
			function sleep(numberMillis) {
			    var now = new Date();
			    var exitTime = now.getTime() + numberMillis;
			    while (true) {
			        now = new Date();
			        if (now.getTime() > exitTime)
			            return;
			    }
			}
			
			function split_array(str){
				var arr = str.split(',');
				return arr;
			}
			
			for(var val in data){
				//console.log(data[val]);
				
				var t1=1;
				var data1;
				var data2;
				var series="";
				var series1="";
				var x="进行第"+t+"次迭代<br>";	
				$.each(data[val],function(i,n){   //each遍历json对象,i为key，n为value
					//alert(data[val][i]);
					//console.log(data[val][i]);
					if(t1 %2 == 0){
						data2=data[val][i];
						//console.log(data2);
						//console.log(JSON.stringify(data2));
						var data4 = JSON.stringify(data2).replace(/\"/g,"'");
						//console.log(data4);
						series += "{symbolSize: 20,data: "+data4+",label: {show: true,formatter: function (param) {return param.data[2];},position: 'top'},type: 'scatter',},";
						t1+=1;
					}
					else{
						data1=data[val][i];
						x+="第"+Math.floor(t1/2+1)+"簇的中心为"+data1;
						t1+=1;
					}
			         //console.log(series);
				});
				series1 += "["+series+"],";
				console.log(series1);
			}
			document.getElementById("demo").innerHTML=x+"<br>迭代已完成";
		},"json")
	});
</script>
</body>
</html>