


angular.module("myApp", []).service("Data", function() {
	return [
	   {
		   id:111,
		   name:"iphone",
		   price:1000
	   },
	   {
		   id:222,
		   name:"上去",
		   price:2000
	   },
	   {
		   id:333,
		   name:"上海",
		   price:3000
	   },
	   {
		   id:444,
		   name:"daswrwer",
		   price:5000
	   },
	   {
		   id:555,
		   name:"dsafsdfs",
		   price:6000
	   },
	   {
		   id:666,
		   name:"dsgdfjghjgh",
		   price:7000
	   }
	]
}).controller("firstController",function($scope,Data,$filter){
	//controller使用过滤器
	var number = $filter('number')(3000);
	console.log(number);
	var json = $filter('json')(Data);
	console.log(json);
	//自定义方法过滤
	$scope.checkName = function(obj){
		if(obj.name.indexOf('j') == '-1'){
			return false;
		}
		return true;
	}
	$scope.datas = Data;
	$scope.message = "sdasADA";
})



