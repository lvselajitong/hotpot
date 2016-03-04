app.controller("mainCtrl",["$scope","$http",function($scope,$http){
	var getAllArticles;
	getAllArticles = function(){
		$http({
			method : 'GET',
			url : '/articles',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded',
			}
		}).then(function(res){
			if (res !== undefined) {
				if (res.status === 200 && res.data.success) {
					$scope.articles = res.data.data;
				}
			}
		});
	};
	getAllArticles();
	
}]);