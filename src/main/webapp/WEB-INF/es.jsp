<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'es.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%@ include file="/web/common/style.jsp" %>
<%@ include file="/web/common/script.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jquery-easyui-1.4.1/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery-easyui-1.4.1/plugins/datagrid-export.js"></script>
<script type="text/javascript">
$(function(){
	$('#cc').combogrid({    
    delay: 500,    
    mode: 'remote',    
    url: '${pageContext.request.contextPath}/uList.do',    
    idField: 'id',    
    textField: 'name',    
    columns: [[    
        {field:'username',title:'Code',width:120,sortable:true},    
        {field:'password',title:'Name',width:400,sortable:true}    
    ]]    
}); 

});
</script>
  </head>
  
  <body>
    <h1>111111</h1>
    <input id="cc" name="dept" />
  </body>
</html>
