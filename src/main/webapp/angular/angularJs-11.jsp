<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>过滤器2</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/angularJs-11.js"></script>
</head>
  <body ng-app="myApp">
  
  <div ng-controller="firstController">
    {{[1,2,3,4,5,6] | limitTo:2}}<br>
    倒序:{{[1,2,3,4,5,6] | limitTo:-2}} 
    <p>{{message | lowercase}}</p>
    <p>{{message | uppercase}}</p>
  	<p>搜索全部属性中带'xx'的输出,过滤器只能匹配value值:{{datas | filter : '上'}}</p>
  	<p>{{datas | filter : {name:'d'} }}</p>
  	<p>排序-正序:{{datas | orderBy : 'name'}}</p>
  	<p>倒序-正序:{{datas | orderBy : '-name'}}</p>
  	<p>自定义方法过滤:{{datas | filter : checkName}}</p>
  </div>
	
	
  </body>
</html>