<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>多个控制器的作用域链(嵌套Controller)</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
</head>
<!-- 现在一共有3个作用域 -->
 <!-- ng-app第一个作用域  范围最大，因为是body -->
  <body ng-app> 
  	<!-- firstController第二个作用域   -->
	  	<div ng-controller="firstController">
  			<input type="text" value="" ng-model="name">
  			<input type="text" value="" ng-model="age">
  			<span ng-bind="name"></span>
  			<span ng-bind="age"></span>
  			<!-- secondContorller,第三个作用域   -->
  			<div ng-controller="secondContorller">
  				<input type="text" value="" ng-model="name">  
  			</div>
  		</div>
  		  	<h1>当secondContorller 没写任何东西时/或者没写name时，secondContorller里的input会输出张三，
  			原因是：当secondContorller的 ng-model="name"想要输出值时，发现我没有name，于是向上寻找，找到作用域更大一级（也可以理解为父标签）的firstController
  			里面是否有name,如果有就拿来用，没有就继续向上寻找，没找到什么也不输出。
  			在向上寻找时，找到的情况：例如在firstController找到了name并使用,firstController里的name改变时，secondContorller的name也改变,当改变secondContorller里的name的value时，
  			firstController不会改变，原因是：在secondContorller的name值被改变后，会在secondContorller作用域里创建一个name变量，可以理解为，我自己有了name，就不用别人的name了</h1>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/myController.js"></script>
	<script type="text/javascript">
	
	</script>
  </body>
</html>