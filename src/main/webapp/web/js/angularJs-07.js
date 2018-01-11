//在module中,$provide的provider方法定义服务，类似于初始化一个方法,只能在指定的作用域使用,但是此方法不常用 ,模块的第三个参数变量名固定
//申明模块
var myApp = angular.module("myApp", [],function($provide){
	//自定义服务
	$provide.provider("serviceName", function() {
		this.$get = function(){
			return {
				message:"自定义的provide"
			}
		}
	});
	
	$provide.provider("serviceName2", function() {
		this.$get = function(){
			return {
				message:"自定义的provide2"
			}
		}
	});
});

//使用自定义服务,只用在第二个参数的方法在加一个参数，名字为自定义的服务名，无视$scope顺序
myApp.controller("myController", function($scope,serviceName,serviceName2) {
	$scope.name = "张三111";
	console.log(serviceName);
	console.log(JSON.stringify(serviceName2));
})

