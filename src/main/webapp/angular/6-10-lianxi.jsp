<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>过滤器</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/angularJs-6-10.js"></script>
	<style type="text/css">
	.orderColor{
		color:red;
	}
	</style>
  </head>
  
  <body>
  <h1>6-10练习</h1>
  	<div ng-app="product" >
  		<div class="container" ng-controller="productController">
	  		<form class="form-inline">
			  <div class="form-group">
			    <label for="exampleInputName2" class="sr-only"></label>
			<!--     搜索单属性 ,意思为搜索下面 filter中 search对象的id -->
			    <input type="text" ng-model="search.id" class="form-control" id="exampleInputName2" placeholder="搜索一下">
			    <!-- <input type="text" ng-model="search" class="form-control" id="exampleInputName2" placeholder="搜索一下"> -->
			  </div>
			</form>
  			<table class="table table-hover">
  				<tr>
  					<th ng-click="changeOrder('id')" ng-class="{dropup : order == ''}">
  						产品编号
  						<span ng-class="{orderColor : orderType == 'id'}" class="caret"></span>
  					</th>
  					<th ng-click="changeOrder('name')" ng-class="{dropup : order == ''}">
  						产品名称
  						<span ng-class="{orderColor : orderType == 'name'}" class="caret"></span>
  					</th>
  					<th ng-click="changeOrder('price')" ng-class="{dropup : order == ''}">
  						产品价格
  						<span ng-class="{orderColor : orderType == 'price'}" class="caret"></span>
  					</th>
  				</tr>
  				<!-- 当 ng-model="search" 情况 搜索所有属性中包含xx的数据 -->
  				<!-- <tr ng-repeat="product in productData | filter : search"> -->
  				<!-- 当 ng-model="search" 情况 搜索某个属性中包含xx的数据 -->
  			<!-- 	<tr ng-repeat="product in productData | filter : {id:search}"> -->
  				<!-- <tr ng-repeat="product in productData | filter : search"> -->
  				<!--     搜索单属性 ,意思为搜索下面 filter中 search对象的id -->
  				<tr ng-repeat="product in productData | filter : search | orderBy : order + orderType">
  					<td>{{product.id}}</td>
  					<td>{{product.name}}</td>
  					<td>{{product.price | currency : '￥'}}</td>
  				</tr>
  			</table>
  		</div>
  	</div>
  </body>
</html>
