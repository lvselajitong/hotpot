app.controller("loginCtrl",["$scope","$rootScope","$location","$cookies","LoginService",function($scope,$rootScope,$location,$cookies,LoginService){
	$scope.login = function(){
		LoginService.authenticate($.param({
			username : $scope.username,
			password : $scope.password,
		}), function(res) {
			if(res !== undefined){
				if(res.success){
					var cookieObj = {
							id:res.data.id,
							name:res.data.name,
							email:res.data.email
					};
					$cookies.putObject('token',cookieObj);
					$rootScope.user = cookieObj;
					$location.path("index");
				}else{
					$scope.error = res.message;
				}
			}
		});
	};
}]);