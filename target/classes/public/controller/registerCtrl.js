app.controller("registerCtrl",["$scope","$location","$http",function($scope,$location,$http){
	$scope.register = function(){
		var param = $.param({
			username : $scope.username,
			password : $scope.password,
			email: $scope.email
		});
		$http({
			url:"/register",
			method:"POST",
			data:param,
			headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		}).then(function(res){
			if (res !== undefined) {
				if (res.status === 200 && res.data.success) {
					$location.path("/");	
				}else{
					$scope.error = res.data.message;
				}
			}
		},function(res){
			$scope.error = "Internal Server Error";
		});
	}
	
}]);