app.factory('UserIdentity', ["$cookies", function ($cookies) {
	var user;
	var set = function(Object) {
		user= Object;
	};
	var get = function() {
		
	     user = $cookies.getObject('token');
	     return user;

	};
	return {
		set : set,
		get : get
	};
}]);