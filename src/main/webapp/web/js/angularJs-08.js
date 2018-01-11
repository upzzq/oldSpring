//在module中,$provide的service、factory方法定义服务，类似于初始化一个方法,只能在指定的作用域使用,但是此方法不常用 ,模块的第三个参数变量名固定
//用法和provider一样,就是provider的简写形式/快捷方法，不用写this.$get了
//申明模块
var myApp = angular.module("myApp", [],function($provide){
	
	//自定义工厂 什么都能返回
	$provide.factory("customFactory", function(){
		return "333";
	});
	
	//自定义服务 只能返回引用类型的对象,不能返回基本数据类型，如int、string之类的不行，数组也是对象
	$provide.service("customService", function(){
		return ["111","222","333"];
	})
});

//使用自定义服务,只用在第二个参数的方法在加一个参数，名字为自定义的服务名，无视$scope顺序
myApp.controller("myController", function($scope,customFactory,customService) {
	$scope.name = "张三111";
	console.log(customFactory);
	console.log(JSON.stringify(customService));
});
//定义服务也可这样写通过module.service()方法也是一样的
//如myApp已经是个模块，myApp.service('name',function)/myApp.factory('name',function)

