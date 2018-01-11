<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	
	<script type="text/javascript">
	angular.module("ang", []).factory("Datas",["$http","$scope",function($http,$scope){
		return [
			$http({
			url:"users.do",
			method:"post"
		}).success(function(data){
			$scope.data = data;
		})
		]
	}]).controller("firstController",["$scope","Datas",function($scope,Datas){
		console.log(Datas);
		$scope.users = Datas;
	}]);
	</script>
</head>
  <body ng-app="ang">
  
  <div ng-controller="firstController" style="height: 500px;">
		<table class="table table-hover">
			<tr ng-repeat="u in data">
				<td>{{u.username}}</td>
				<td>{{u.password}}</td>
			</tr>
		</table>
  </div>
	
	
	<script type="text/javascript">
	
	</script>
  </body>
</html>