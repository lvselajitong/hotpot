app.factory('CategoryService', function($http) {
	return {
		getAllCategories : function() {
			var promise = $http({
				method : 'GET',
				url : '/category',
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded',
				}
			}).then(
					function(res) {
						return res;
					},
					function errorCallback(response) {
						notify("HTTP error! <br>" + "Error Status: "
								+ response.status + '<br> Status Text:'
								+ response.response, 'danger', true);
						if (response.data !== undefined
								&& response.data !== null) {
							notify("Fail to query all accounts! <p>errorcode:"
									+ response.data.errorCode + '<br> message:'
									+ response.data.message + '<br>exception: '
									+ response.data.exception, 'danger', true);
						}
					});
			return promise;
		},
		addCategory : function(categoryName){
			var promise = $http({
				method : 'POST',
				url : '/category',
				params : {
					categoryName : categoryName
				},
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded',
				}	
			}).then(
					function(res) {
						return res;
					},
					function errorCallback(response) {
						notify("HTTP error! <br>" + "Error Status: "
								+ response.status + '<br> Status Text:'
								+ response.response, 'danger', true);
						if (response.data !== undefined
								&& response.data !== null) {
							notify("Fail to query all accounts! <p>errorcode:"
									+ response.data.errorCode + '<br> message:'
									+ response.data.message + '<br>exception: '
									+ response.data.exception, 'danger', true);
						}
					});
			return promise;
		},
		deleteCategory : function(){
			
		}
	}
});