app.run(["$rootScope","$location", "$cookies", function($rootScope, $location, $cookies) {

		/* Reset error when a new view is loaded */
		$rootScope.$on('$viewContentLoaded', function() {
			delete $rootScope.error;
		});



		$rootScope.logout = function() {
			delete $rootScope.user;
			$cookies.remove('token');
			$location.path("/");
		};

		 /* Try getting valid user from cookie or go to login page */
		var originalPath = $location.path();
		$location.path("/");
		var token = $cookies.getObject('token');
		if (token !== undefined) {
			$rootScope.user = token;
			$location.path(originalPath);
		}
		

	}]);