<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>自定义过滤器和controller另一种写法</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/angularJs-12.js"></script>
</head>
  <body ng-app="myApp" class="container">
  	<h1>自定义过滤器和controller另一种写法</h1>
  	<div ng-controller="firstController">
  		<ul>
  			<li ng-repeat="d in data | filterCity">
  				{{d.name}}
  				{{d.age}}
  				{{d.city}}
  			</li>
  		</ul>
	</div>
	
	
	<script type="text/javascript">
	
	</script>
  </body>
</html>