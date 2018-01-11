//通过数组的方式传参可以使参数名使任意名称,好处是项目上线后，压缩js代码，为了把变量名替换成 a,b,c之类的压缩代码
//显示依赖注入 1-11使用隐式依赖注入
angular.module("myApp", [],["$filterProvider","$provide","$controllerProvider",function(){
	
}]).factory("customService",["$window",function(w){
	console.log(w);
	return "22";
}]).controller("firstController",["$scope","customService",function(a,s){
	a.data = "a.属性";
	console.log(s);
}]);

//全局controller显示依赖注入的特殊写法
function otherController(a){
	a.data = "全局";
}
otherController.$inject = ["$scope"];

