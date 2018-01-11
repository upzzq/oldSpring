<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>$scope里的$watch方法(监听对象/属性)</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
</head>
  <body ng-app> 
  
	  	<div ng-controller="watchController">
	  		<input value="" ng-model="name"><span ng-bind="name"></span>
	  		<input value="" ng-model="sr.age"><span ng-bind="sr.age"></span>
  		</div>
  		  	<h1>
  		  	</h1>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/myController.js"></script>
	<script type="text/javascript">
	
	</script>
  </body>
</html>