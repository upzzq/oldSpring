angular.module("myApp", [],function($filterProvider,$provide,$controllerProvider){
	//参数是为了注册xxx
	$provide.service('Data',function(){
		return [
		   {
			   name:"张三",
			   age:20,
			   city:"北京"
		   },
		   {
			   name:"李四",
			   age:30,
			   city:"上海"
		   }
		]
	});
	
	$controllerProvider.register("firstController",function($scope,Data){
		$scope.data = Data;
	});
	
	//注册/自定义过滤器
	$filterProvider.register("filterAge",function(){
		return function(obj){
			newObj = [];
			angular.forEach(obj, function(o) {
				if(o.age > 20){
					newObj.push(o);
				}
			});
			return newObj;
		}
	})
	//注册/自定义过滤器快捷方法
}).filter("filterCity", function() {
	return function(obj){
		var newObj = [];
		angular.forEach(obj, function(o) {
			if(o.city == '北京'){
				newObj.push(o);
			}
		});
		return newObj;
	}
})