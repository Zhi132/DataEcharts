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
		$.post("${pageContext.request.contextPath }/test_Picture.action",{},function(data){
			//遍历json的数据
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
			for(var val in data){
				//console.log(data[val]);
				
				var t1=1;
				var data1;
				var data2;
				var series="";
				var series1;
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
						series += "{symbolSize: 5,data: "+data4+",label: {show: true,fontsize:1,formatter: function (param) {return param.data[2];},position: 'top'},type: 'scatter',},";
						t1+=1;
					}
					else{
							
						data1=data[val][i];
						
						x+="第"+Math.floor(t1/2+1)+"簇的中心为["+data1+"]";
						t1+=1;
					}
					
					
					
			         //console.log(series);
				});
				series1 = "["+series+"]";
				var json = eval('(' + series1 + ')'); 
				//series1 = series1.replace(/\"/g,"")
				console.log(json);
		        // console.log(series1);
				var timestamp1 =Date.parse(new Date());
				option = {
					    xAxis: {
					    	splitLine: {
					            show: false
					        },
					    },
					    yAxis: {
					    	splitLine: {
					            show: false
					        },
					    },
					    series:json,
					};
				myChart.setOption(option);
				document.getElementById("demo").innerHTML=x;
				var r=confirm("是否进行下一次迭代!");
				if (r==true){
					//x="进行"+t+"迭代<br>第一个簇中心为"+data1[0]+"，第二个簇中心为"+data1[1];
					
					t+=1;
				}
				else{
					var x1="您已取消本次迭代";
					document.getElementById("demo").innerHTML=x;
					return;
				}
				var timestamp2 =(new Date()).valueOf();
				while(timestamp2 - timestamp2 <= 100){
					timestamp2 =(new Date()).valueOf();
					break;
				}
				//sleep(3000);
			}
			document.getElementById("demo").innerHTML=x+"<br>迭代已完成";
		},"json")
	});
</script>
</body>
</html>