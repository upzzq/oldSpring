<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
  <!--   可以禁用其缩放（zooming）功能。这样禁用缩放功能后，用户只能滚动屏幕，就能让你的网站看上去更像原生应用的感觉 -->
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	<style type="text/css">
	a{
		text-decoration: none;
	}
	</style>
	<script type="text/javascript">
	$(function(){
		 $('input').iCheck({
    		checkboxClass: 'icheckbox_square',
    		radioClass: 'iradio_square',
    		increaseArea: '20%'
  		 });
	});
	
	angular.module("loginModule",[]).controller("loginController",["$scope",function($scope){
	}]);
	</script>
	
	<script type="text/javascript">
	function submit1(){
		$("#form1").submit();
	}
	function changeImg(){
        var img = document.getElementById("img"); 
        img.src = "${pageContext.request.contextPath}/yzm.do?date=" + new Date();;
    }
	</script>
	
  </head>
  
  <body>
	<div ng-app="loginModule" class="container">
    	<div ng-controller="loginController" class="center-block" style="margin-top:200px">
    		<form id="form1" name="myForm" class="form-horizontal" action="login.do" method="post">
			  <div class="form-group" ng-class="{'has-error':myForm.username.$touched && myForm.username.$invalid}">
			    <div class="col-sm-4">
			      <input type="text" autocomplete="off" class="form-control" ng-required="true" ng-model="username" name="username" placeholder="请输入用户名" aria-describedby="username">
			      <div ng-show="myForm.username.$touched && myForm.username.$error.required" class="alert alert-danger help-block">
			      	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  	<span class="sr-only">Error:</span>
			      	用户名不能为空
			      </div>
			    </div>
			  </div>
			  <div class="form-group" ng-class="{'has-error':myForm.password.$touched && myForm.password.$invalid}">
			    <label for="password" class="col-sm-4 control-label">密码</label>
			    <div class="col-sm-4">
			      <input type="password" class="form-control" name="password" ng-model="password" ng-required="true" placeholder="请输入密码">
			      <div ng-show="myForm.password.$touched && myForm.password.$error.required" class="alert alert-danger help-block">
			      	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  	<span class="sr-only">Error:</span>
				  	密码不能为空
			      </div>
			    </div>
			  </div>
			  <div class="form-group" ng-class="{'has-error':myForm.yzm.$touched && myForm.yzm.$invalid}">
			    <label class="col-sm-4 control-label">验证码</label>
			    <div class="col-sm-2">
			      <input class="form-control" maxlength="4" id="yzm" name="yzm" ng-model="yzm" ng-required="true" ng-minlength="4">
			      <div ng-show="myForm.yzm.$error.required && myForm.yzm.$touched" class="alert alert-danger help-block">
			      	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  	<span class="sr-only">Error:</span>
			      	验证码不能为空
			      </div>
			      
			       <div ng-show="myForm.yzm.$error.minlength && myForm.yzm.$touched" class="alert alert-danger help-block">
			      	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  	<span class="sr-only">Error:</span>
			      	验证码输入有误
			      </div>
			      
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-4 control-label sr-only"></label>
			    <div class="col-sm-4">
			      <img id="img" src="${pageContext.request.contextPath}/yzm.do"/>
			      <a href='javascript:changeImg()'><lable>看不清？</lable></a>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-4 col-sm-4">
			      <div class="checkbox">
			      	<input type="checkbox" name="remberMe"> 记住我
			      </div>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-4 col-sm-4">
			      <button type="button" class="btn btn-primary btn-lg btn-block" ng-disabled="myForm.$invalid" onclick="submit1()">	登	录	</button>
			    </div>
			  </div>
			</form>
			<!-- {{myForm.password.$error}}
			{{myForm.username.$dirty}}
			{{myForm.username.$pristine}} -->
    	</div>
    </div>
	
    
    <h1>${msg}</h1>
  </body>
</html>
