
//购物车
var cartController = function($scope){
	$scope.cart = [
	    {
	    	id:1000,
	    	name:'电池',
	    	quantity:3,
	    	price:1300
	    },
	    {
	    	id:2000,
	    	name:'鼠标',
	    	quantity:6,
	    	price:2100
	    },
	    {
	    	id:3000,
	    	name:'电脑',
	    	quantity:9,
	    	price:3600
	    },
	    {
	    	id:4000,
	    	name:'钥匙',
	    	quantity:12,
	    	price:2000
	    },
	];
	
	/*
	 * 获取索引
	 */
	var findIndex = function(id){
		var index = -1;
		angular.forEach($scope.cart, function(item, key) {
			if(item.id == id){
				index = key;
				return index;
			}
		});
		return index;
	}
	
	/*
	 * 计算购物总价
	 */
	$scope.totalPrice = function(){
		var total = 0;
		angular.forEach($scope.cart, function(item) {
			total += item.quantity * item.price;
		});
		return total;
	}
	
	/*
	 * 计算购买总数
	 */
	$scope.totalQuantity = function(){
		var total = 0;
		angular.forEach($scope.cart, function(item) {
			total += item.quantity;
		});
		return total;
	}
	
	/*
	 * 移除
	 */
	$scope.remove = function(id){
		var index = findIndex(id);
		if(index != -1){
			$scope.cart.splice(index, 1);
		}
	}
	
	
	/*
	 * 商品数量增加
	 */
	
	$scope.add = function(id){
		var index = findIndex(id);
		if(index != -1){
			++$scope.cart[index].quantity;
		}
	}
	
	/*
	 * 商品数量减少
	 */
	
	$scope.reduce = function(id){
		var index = findIndex(id);
		if(index != -1){
			var item = $scope.cart[index];
			if(item.quantity > 1){
				--$scope.cart[index].quantity;
			}else{
				/*layer.confirm('确定从购物车删除此商品吗？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					alert("执行删除");
					$scope.remove(id);
					layer.msg('删除成功', {
					    time: 2000 
					  });
				});*/
				var isDel = confirm("是否删除？");
				if(isDel){
					$scope.remove(id);
				}
			}
		}
	}
	
	
	
	$scope.$watch('cart',function(newValue,oldValue){
		angular.forEach(newValue, function(item, key) {
			if(item.quantity == 0){
				var isDel = confirm("是否删除？");
				if(isDel){
					$scope.remove(item.id);
				}else{
					item.quantity = oldValue[key].quantity;
				}
			}
		})
	},true);
}