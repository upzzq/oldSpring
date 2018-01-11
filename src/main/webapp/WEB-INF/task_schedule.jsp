<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>任务</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	<script type="text/javascript">
	$(function(){
		$("#tj").click(function(){
			$.post("${pageContext.request.contextPath}/taskAdd.do",$("#form1").serialize(),function(data){
				if(!data.flag){
					layer.msg(data.msg);
				}else{
					layer.msg("保存成功!");
				}
			});		
		});
	});
	angular.module("taskModule",[]).controller("taskController",["$scope","$http",function($scope,$http){
		$http({
			method:"post",
			url:"",
			data:{},
		}).success(function(data){
			
		}).error(function(data){
			
		});
	}]);
	</script>
  </head>
  
  <body ng-app="taskModule">
	<div ng-controller="taskController">
		<div class="table-responsive">
			<table class="table table-hover">
				<tr>
					<th width="150">任务名称</th>
					<th width="150">任务分组</th>
					<th width="150">任务状态</th>
					<th width="150">创建时间</th>
					<th width="300">修改时间</th>
					<th width="150">类名</th>
					<th width="150">方法名</th>
					<th width="150">cron表达式</th>
					<th width="150">描述</th>
					<th colspan="3">操作</th>
				</tr>
				<c:forEach items="${tasks}" var="t">
					<tr>
						<td>${t.jobName}</td>
						<td>${t.jobGroup}</td>
						<td>${t.jobStatus}</td>
						<td><fmt:formatDate value="${t.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${t.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${t.beanClass}</td>
						<td>${t.methodName}</td>
						<td>${t.cronExpression}</td>
						<td>${t.description}</td>
						<td><button type="button" class="btn btn-primary btn-xs" ng-click="">启用</button></td>
						<td><button type="button" class="btn btn-success btn-xs">禁用</button></td>
						<td><button type="button" class="btn btn-danger btn-xs">删除</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>	
	</div>
	
	
<form id="form1" class="form-horizontal">
  <div class="form-group">
    <label for="jobName" class="col-sm-2 control-label">任务名称</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="jobName" placeholder="任务名称">
    </div>
    
  </div>
  <div class="form-group">
    <label for="jobGroup" class="col-sm-2 control-label">任务分组</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="jobGroup" placeholder="任务小组">
    </div>
  </div>
  
  <div class="form-group">
    <label for="jobStatus" class="col-sm-2 control-label">任务状态</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="jobStatus" value="0" placeholder="任务状态">
    </div>
  </div>
  
  <div class="form-group">
    <label for="cronExpression" class="col-sm-2 control-label">克龙表达式</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" value="*/5 * * * * ?" name="cronExpression" placeholder="克龙表达式">
    </div>
  </div>
  
  <div class="form-group">
    <label for="description" class="col-sm-2 control-label">任务描述</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="description" placeholder="任务描述">
    </div>
  </div>
  
  <div class="form-group">
    <label for="isConcurrent" class="col-sm-2 control-label">是否有状态</label>
    <div class="col-sm-10">
      <select class="form-control" name="isConcurrent">
	  	<option>0</option>
	  	<option>1</option>
  	  </select>
    </div>
  </div>
   
  
  <div class="form-group">
    <label for="beanClass" class="col-sm-2 control-label">beanClass</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="beanClass" value="com.supplies.pojo.Tas" placeholder="beanClass">
    </div>
  </div>
    
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">springId</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="springId" value="tas" placeholder="springId">
    </div>
  </div>
    
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">methodName</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="methodName" placeholder="methodName">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button class="btn btn-default" id="tj">提交</button>
    </div>
  </div>
</form>

	<h2>执行中的任务</h2>
	<div>
		<div class="table-responsive">
			<table class="table table-hover">
				<tr>
					<th width="100">任务名称</th>
					<th width="100">任务分组</th>
					<th width="100">任务状态</th>
					<th width="100">创建时间</th>
					<th width="100">修改时间</th>
					<th width="100">类名</th>
					<th width="100">方法名</th>
					<th width="100">cron表达式</th>
					<th width="100">描述</th>
					<th width="100" colspan="3">操作</th>
				</tr>
				<c:forEach items="${allJob}" var="t">
					<tr>
						<td>${t.jobName}</td>
						<td>${t.jobGroup}</td>
						<td>${t.jobStatus}</td>
						<td>${t.createTime}</td>
						<td>${t.updateTime}</td>
						<td>${t.beanClass}</td>
						<td>${t.methodName}</td>
						<td>${t.cronExpression}</td>
						<td>${t.description}</td>
						<td><button type="button" class="btn btn-primary btn-xs">启用</button></td>
						<td><button type="button" class="btn btn-success btn-xs">禁用</button></td>
						<td><button type="button" class="btn btn-danger btn-xs">删除</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>	
	</div>
	
  </body>
</html>


