<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>内置渲染指令</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/angularJs-13.js"></script>
	<script type="text/javascript">
	angular.module("myApp",[])
	</script>
</head>
  <body ng-app="myApp" class="container">
  	<h1>指令和html校验,ng指令在很多html校验规则是不合法的，所以提供了很多种调用指令写法，可以顺利通过不同的校验</h1>
  	<p>校验器:none,指令:ng-bind:</p>
  	<p ng-bind="{{22+33}}"></p>
    <p>校验器:XML,指令:ng:bind:</p>
  	<p ng:bind="{{33+44}}"></p>
  	<p>校验器:HTML55,指令:data-ng-bind</p>
  	<p data-ng-bind="{{44+55}}"></p>
  	<p>校验器:XHTML,指令:x-ng-bind</p>
  	<p x-ng-bind="{{660+77}}"></p>
	
	<p>ng-init:初始化数据</p>
	<p ng-init="cityArr = ['上海','北京','四川']"></p>
	<ul>
		<li ng-repeat="c in cityArr">
			<span>index:{{$index}}</span>
			<span>first:{{$first}}</span>
			<span>middle:{{$middle}}</span>
			<span>last:{{$last}}</span>
			<span>{{c}}</span>
		</li>
	</ul>
  </body>
</html>