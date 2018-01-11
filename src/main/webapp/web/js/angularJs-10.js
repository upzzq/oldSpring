

//第二种 通过factory方法初始化数据，让Controller同一引用
var myApp = angular.module("myApp", []).controller("firstController",function($scope){
	
	//从服务获取数据
	$scope.date = new Date();
	
})



