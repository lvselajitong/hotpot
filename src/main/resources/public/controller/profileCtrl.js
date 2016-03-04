app.controller("profileCtrl",["$scope","$cookies",function($scope,$cookies){
	var cookies = $cookies.getObject("token");
	if(cookies !== null){
		$scope.username = cookies.name;
		$scope.email = cookies.email;
	}
	
}]);