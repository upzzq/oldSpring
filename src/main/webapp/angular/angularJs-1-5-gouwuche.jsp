<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>1-5混合使用</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/gouwuche.js"></script>
</head>
  <body ng-app>
  
  
	<div class="container" ng-controller="cartController">
		<form class="form-inline">
			<table class="table table-hover" ng-show="cart.length">
				<tr style="text-align: center">
					<th>产品编号</th>
					<th>产品名字</th>
					<th>产品数量</th>
					<th>产品单价</th>
					<th>产品总价</th>
					<th>操作</th>
				</tr>
				<tr ng-repeat="item in cart">
					<td>{{item.id}}</td>
					<td>{{item.name}}</td>
					<td>
						<button type="button" class="btn btn-primary" ng-click="reduce(item.id)">-</button>
						<input type="text" class="form-control" value="{{item.quantity}}" ng-model="item.quantity">
						<button type="button" class="btn btn-primary" ng-click="add(item.id)">+</button>
					</td>
					<td>{{item.price}}</td>
					<td>{{item.price * item.quantity}}</td>
					<td><button type="button" class="btn btn-danger" ng-click="remove(item.id)">移除</button></td>
				</tr>
				<tr>
					<td>总购买价</td>
					<td>{{totalPrice()}}</td>
					<td>总购数量</td>
					<td>{{totalQuantity()}}</td>
					<td colspan="2"><button type="button" ng-click="cart = {}" class="btn btn-danger">清空购物车</button></td>
				</tr>
			</table>
			<table class="table table-hover" ng-show="!cart.length">
				<tr>
					<td colspan="6">购物车为空</td>
				</tr>
				<tr>
					<td colspan="6"></td>
				</tr>
			</table>
		</form>
	</div>
	
<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">购物车</h4>
      </div>
      <div class="modal-body">
        	确定要删除此商品吗？
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>
	
  </body>
</html>