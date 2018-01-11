<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>$scope里的$apply、$digest方法</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
</head>
  <body ng-app> 
  
	  	<div ng-controller="applyController">
	  		<span ng-bind="date"></span>
	  		{{date}}
  		</div>
  		<script type="text/javascript">alert("1")</script>
  		  	<h1>angularJs有脏检查机制，当带 ng-表达式的内容 初始化后，会自动执行脏检查一次，使用$apply()方法手动触发脏检查,如果不给参数,就自动检查所有属性,推荐给参数<br>
  		  		angularJs脏检查策略：不会检查所有对象,当对象被绑定到html中,或属性被绑定后,就会变成脏检查对象/属性,当angularJs初始化后,会将绑定的对象/属性添加为监听对象<br>
  		  		如果一个对象绑定了N个属性，就会添加N个监听对象(watcher)<br>
  		  		脏检查其实是$digest()方法执行的，只是通过$apply() 方法检查通过后才执行 $digest()方法
  		  	</h1>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/myController.js"></script>
	<script type="text/javascript">
	
	</script>
  </body>
</html>