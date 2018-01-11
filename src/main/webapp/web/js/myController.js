



var firstController = function($scope){
	//初始化 ng-model=""
	$scope.name="张三";
	$scope.age=20;
}

var secondContorller = function($scope){
	//$scope.name = "李四";
}

var applyController = function($scope){
	$scope.date = new Date();
	
	setInterval(function(){
		$scope.$apply(function(){
			$scope.date = new Date();
		});
	},1000)
}

var watchController = function($scope){
	$scope.name = "张三";
	$scope.sr = {
		name:'aaa',
		age:666
	}
	//不写第三个参数，会检查对象的引用是否变更了，不管你的属性是不是会变,如果设置第三个参数为true并且是对象/数组时，会检查对象的属性
	$scope.$watch('sr',function(newValue,oldValue){
		alert("改变了name:"+JSON.stringify(oldValue));
	},true)
}