//申明模块
var myApp = angular.module("myApp", []);

//模块所属Controller,只有指定了ng-app="模块名",那么那个模块才能使用
myApp.controller("myController", function($scope) {
	$scope.name = "张三111";
})

//全局Controller 不管在哪都能用
var myController = function($scope){
	$scope.name = "张三11122";
}