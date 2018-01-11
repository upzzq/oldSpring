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
		$compileProvider.directive('customTags',function(){
			return {
				restrict:'ECAM',
				template:'<div>测试自定义指令</div>',//我即将要替换的模板,要替换成什么东西
				replace:true,//设置false或不指定,只是在你使用指令的元素下追加,但是对于E:标签来说，html没有我自定义的这个标签，所以通常为trues
				compile: function(){
					console.log(1);				
				}
			}
		});
	}]);
	</script>
</head>
  <body ng-app="myApp" class="container">
  	<h1>自定义指令,指令定义选项有以下13种,restrict、template、replace使用</h1>
  	<ul>
  		<li>priority</li>
  		<li>terminal</li>
  		<li>scope</li>
  		<li>controller</li>
  		<li>controller-As</li>
  		<li>require</li>
  		<li>restrict</li>
  		<li>template</li>
  		<li>templateUrl</li>
  		<li>replace</li>
  		<li>transclude</li>
  		<li>compile</li>
  		<li>link</li>
  	</ul>
  
  <p>E(element元素):驼峰命名法注意,如指令名叫customTags,以大写边界使用-代替</p>
  <p><custom-tags>232132143</custom-tags></p>
  <p>C(class样式):驼峰命名法注意,如指令名叫customTags,以大写边界使用-代替</p>
  <p class="custom-tags"></p>
  <p>A(元素的属性):驼峰命名法注意,如指令名叫customTags,以大写边界使用-代替</p>
  <p custom-tags ></p>
  <p>M(XML的注释):驼峰命名法注意,如指令名叫customTags,以大写边界使用-代替,(注意)directive:开头,然后在指令首尾个加一个空格隔开</p>
	<!--  directive:custom-tags  -->
  </body>
</html>