<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>自定义指令</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/web/common/style.jsp" %>
	<%@ include file="/web/common/script.jsp" %>
	<script type="text/javascript">
	angular.module("myApp",[],['$compileProvider',function($compileProvider){
	//简写
	}]).directive("customTags",function(){
		return {
			restrict:'ECAM',
			template:'<div> 777 <span ng-transclude></span></div>',//目标url里也可以使用变量
			replace:true, //设置为true时模板内容必须有标签围起来
			transclude:true
		}
	}).directive("customTags2",function(){
		return {
			restrict:'ECAM',
			template:'<div> 777</div>',//目标url里也可以使用变量
			replace:true //设置为true时模板内容必须有标签围起来
		}
	}).controller("firstController",['$scope',function($scope){
		$scope.name = "OMG";
	}])
	</script>
</head>
  <body ng-app="myApp" class="container">
  	<h1>自定义指令,指令定义选项有以下13种,使用 transclude:true</h1>
  	<ul>
  		<li style="color:red">priority:指定一个位置有多个指令，设置执行的优先级(不常用)</li>
  		<li style="color:red">terminal:在设置为true时,而且在同一个位置有多个指令时，小于当前执行顺序的不执行,相同的还是会执行</li>
  		<li>scope</li>
  		<li>controller</li>
  		<li>controller-As</li>
  		<li>require</li>
  		<li>restrict</li>
  		<li>template</li>
  		<li>templateUrl</li>
  		<li>replace</li>
  		<li style="color:red">transclude：指令元素中的子节点移动到一个新模板内部,当为true时,指令会删除原来的内容,使你的模板可以用ng-transclude指令进行重新插入</li>
  		<li>compile</li>
  		<li>link</li>
  	</ul>
  <div ng-controller="firstController">
  <p>E(element元素):驼峰命名法注意,如指令名叫customTags,以大写边界使用-代替</p>
  <p><custom-tags>232132143</custom-tags></p>
  <p>C(class样式):驼峰命名法注意,如指令名叫customTags,以大写边界使用-代替</p>
  <p class="custom-tags"></p>
  <p>A(元素的属性):驼峰命名法注意,如指令名叫customTags,以大写边界使用-代替</p>
  <!-- <p custom-tags  custom-tags2></p> 有2个不同的会报错,执行顺序有大小，默认都为0 ng-repeat 是1000 -->
  <p custom-tags></p>
  <p>M(XML的注释):驼峰命名法注意,如指令名叫customTags,以大写边界使用-代替,(注意)directive:开头,然后在指令首尾个加一个空格隔开</p>
	<!--  directive:custom-tags  -->
	
	
	</div>
  </body>
</html>