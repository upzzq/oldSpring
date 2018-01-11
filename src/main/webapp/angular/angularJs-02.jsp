<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>控制器</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	
</head>
  <body>
  
  	<div ng-app>
  		<!-- 一个控制器代表一个方法 myController.js中有申明 -->
  		<div ng-controller="firstController">
  			<input type="text" value="" ng-model="name">
  			<input type="text" value="" ng-model="age">
  			{{name}}
  			{{age}}
  		</div>
  		<!-- ng-bind说明:当 angularJS 加载之前或者加载的速度比较慢,会在页面中显示 {{name}}，被用户看到，因为只有angularJS加载完成时才会替换{{name}} -->
  		<!-- 而使用 ng-bind 表达式时,会在angularJS加载完成时才会替换值 -->
  		<div ng-controller="firstController">
  			<input type="text" value="" ng-model="name">
  			<input type="text" value="" ng-model="age">
  			<span ng-bind="name"></span>
  			<span ng-bind="age"></span>
  		</div>
  	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/myController.js"></script>
	<script type="text/javascript">
	
	</script>
  </body>
</html>