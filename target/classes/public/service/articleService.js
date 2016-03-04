app.factory('ArticleService', function ($http) {
	return {
		deleteById : function(id){
			var promise = $http({
				method : 'DELETE',
				url : '/articles/' + id,
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded',
				}
			}).then(function(res){
				return res;
			});
			return promise;
		}
	};
});