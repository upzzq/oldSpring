<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>

	<script type="text/javascript" src="<%=request.contextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.contextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.contextPath()%>/dwr/interface/MessagePush.js"></script>
	<script type="text/javascript">
	$(function(){
		//页面加载进行反转激活  
       // dwr.engine.setActiveReverseAjax(true);
		//dwr.engine.setNotifyServerOnPageUnload(true);
		//MessagePush.onPageLoad("1");
	})
	
	
          
         //推送信息    
 function showMessage(autoMessage){    
                  
             alert(autoMessage);  
                  
        }    
	</script>
  </head>
  
  <body>  

    111111111
  </body>
</html>
