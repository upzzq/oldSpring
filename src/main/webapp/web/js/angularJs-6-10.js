

//第二种 通过factory方法初始化数据，让Controller同一引用
angular.module("product", []).service("productData", function() {
	return [
	   {
		   id:111,
		   name:"iphone",
		   price:1000
	   },
	   {
		   id:222,
		   name:"ipad",
		   price:2000
	   },
	   {
		   id:333,
		   name:"鼠标",
		   price:3000
	   },
	   {
		   id:444,
		   name:"电视",
		   price:4000
	   }
	]
}).controller("productController",function($scope,productData){
	$scope.productData = productData;
	//排序 默认以id排序
	$scope.orderType = "id";
	//降序用 - 号
	$scope.order = "-";
	
	$scope.changeOrder = function(type){
		$scope.orderType = type;
		if($scope.order == ''){
			$scope.order = '-';
		}else{
			$scope.order = '';
		}
	}
})



