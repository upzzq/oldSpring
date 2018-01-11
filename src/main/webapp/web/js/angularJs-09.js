//多个controller内数据共享,这里使用链式操作

//第一种,使用兄弟Controller对象来获取数据,但是不推荐,因为当页面Controller过多或页面比较复杂时难以处理
//申明模块
/*var myApp = angular.module("myApp", []).controller("firstController",function($scope){
	//$scope.name = "张三";
	$scope.data = {
			name:"张三"
	}
	
}).controller("secondController",function($scope){
	//$scope.name = $scope.$$prevSibling.name;//这种写法必须在辈分元素的属性已有初始化值才能获取到，并且如果是基本数据类型，不能双向绑定,必须是引用类型，因为可以指向同一对象
	//引用类型,页面也要更改为data.name
	$scope.data = $scope.$$prevSibling.data;
})*/

//第二种 通过factory方法初始化数据，让Controller同一引用
var myApp = angular.module("myApp", []).factory("datas",function(){
	return {
		message:"服务初始化，统一让Controller使用"
	}
}).controller("firstController",function($scope,datas){
	
	//从服务获取数据
	$scope.datas = datas;
	
}).controller("secondController",function($scope,datas){
	//从服务获取数据
	$scope.datas = datas;
})



