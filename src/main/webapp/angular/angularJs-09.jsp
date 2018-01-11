<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>多个Controller内数据共享</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/angularJs-09.js"></script>

  </head>
  
  <body>
  	<div ng-app="myApp">
  		<!-- 第一种 -->
  		<!-- <div ng-controller="firstController">
  			<input value="" ng-model="data.name"><br>
  			firstContorller:{{data.name}}
  		</div>
  		<div ng-controller="secondController">
  			secondContorller:{{data.name}}
  		</div> -->
  		
  		<!-- 第二种 -->
  		<div ng-controller="firstController">
  			service.datas<input value="" ng-model="datas.message"><br>
  			service.datas:{{datas.message}}
  		</div>
  		<div ng-controller="secondController">
  			secondContorller-nmessage:{{datas.message}}<br>
  		</div>
  	</div>
  </body>
</html>
