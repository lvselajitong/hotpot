app.factory('ArticleService', function ($http) {
	return {
		deleteById : function(articleId,authId){
			var promise = $http({
				method : 'DELETE',
				url : '/articles/' + articleId + "/auth/" + authId,
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded',
				}
			}).then(function(res){
				return res;
			},function errorCallback(response) {
				notify("HTTP error! <br>" + "Error Status: " + response.status + '<br> Status Text:' + response.response, 'danger', true);
				if (response.data !== undefined && response.data !== null) {
					notify("Fail to query all accounts! <p>errorcode:" + response.data.errorCode + '<br> message:' + response.data.message + '<br>exception: ' + response.data.exception, 'danger', true);
				}
			});
			return promise;
		},
		addArticle : function(title,content,authId,postStatus,category){
			var promise = $http({
				method : 'POST',
				url : '/postaritcle',
				params : {
					title : title,
					content : content,
					authId : authId,
					postStatus: postStatus,
					category : category
				},
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded',
				}
			}).then(function(res) {
				return res;
			}, function errorCallback(response) {
				notify("HTTP error! <br>" + "Error Status: " + response.status + '<br> Status Text:' + response.response, 'danger', true);
				if (response.data !== undefined && response.data !== null) {
					notify("Fail to query all accounts! <p>errorcode:" + response.data.errorCode + '<br> message:' + response.data.message + '<br>exception: ' + response.data.exception, 'danger', true);
				}
			});
			return promise;
		},
		queryArticlesByDateDuration : function(){}
		
	};
});