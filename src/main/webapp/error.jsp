<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>提交成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript" src="<%=request.contextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.contextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.contextPath()%>/dwr/interface/MessagePush.js"></script>
	<script type="text/javascript">
	var error = "${error}";
	if(error != ''){
		alert(error);
	}
	</script>

  </head>
  
  <body>
    <h1>ERROR</h1>
  </body>
</html>
