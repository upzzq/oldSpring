<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
  </head>
  
  <body ng-app>
	<div class="container">
		<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
		  Launch demo modal
		</button>
		
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">标题</h4>
		      </div>
		      <div class="modal-body">
		        你好,${user.username}  
		      
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
		        <button type="button" class="btn btn-default">关闭</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="table-responsive">
			<table class="table table-hover">
				<tr>
					<th>用户名</th>
					<th>密码</th>
				</tr>
				<c:forEach items="${users}" var="u">
					<tr>
						<td>${u.username}</td>
						<td>${u.password}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	  <a href="logout.do"> 退出</a>
	  <a href="taskSchedule.do" class="btn btn-info" role="button">动态任务</a>
	<div style="height: 500px;">
		<input type="text" id="username" name="username" value="" ng-model="name">{{name}}
	</div>
	
	<!-- 重复提交测试 -->
	<form action="saves.do" name="form1" method="post">
		<input type="text" name="token" width="400" value="${token}">
		<input name="id">
			<input name="name">
				<input name="available">
		<input type="submit" value="提交">
	</form>

	<%-- <shiro:hasPermission name="item:query">有权限</shiro:hasPermission> --%>

	<c:if test="${user.menus!=null }">
		<ul style="list-style: none;">
			<c:forEach items="${user.menus }" var="menu">
				<li>
					<div>
						<a title="${menu.name}" href="#">${menu.name}</a>
					</div>
				</li>
			</c:forEach>
		</ul>		
	</c:if>
	

  </body>
</html>


