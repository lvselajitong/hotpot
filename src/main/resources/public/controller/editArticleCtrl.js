app.controller("editArticleCtrl",["$scope","$http","$location","DataStore",function($scope,$http,$location,DataStore){
	var initScope;
	initScope = function(){
		if(DataStore.selectedArticle.length !== 0){
			$scope.curArticle = DataStore.selectedArticle;
		}
	};
	$scope.add = function(){
		
		if($scope.curArticle.title!== undefined){
			$http({
				method : 'POST',
				url : '/articles',
				params : {
					title : $scope.curArticle.title,
					content:$scope.curArticle.content,
					postStatus:"Publish"
				},
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded',
				}
			}).then(function(response) {
				if (response !== undefined) {
					if (response.status === 200 && response.data.success) {
						$location.path("articles");
					}
				}
			}, function errorCallback(response) {
				notify("HTTP error! <br>" + "Error Status: " + response.status + '<br> Status Text:' + response.response, 'danger', true);
				if (response.data !== undefined && response.data !== null) {
					notify("Fail to query all accounts! <p>errorcode:" + response.data.errorCode + '<br> message:' + response.data.message + '<br>exception: ' + response.data.exception, 'danger', true);
				}
			});
		}
	};
	$scope.draft = function(){
		
		if($scope.curArticle.title!== undefined){
			$http({
				method : 'POST',
				url : '/articles',
				params : {
					title : $scope.curArticle.title,
					content:$scope.curArticle.content,
					postStatus:"Draft"
				},
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded',
				}
			}).then(function(response) {
				if (response !== undefined) {
					if (response.status === 200 && response.data.success) {
						$location.path("articles");
					}
				}
			}, function errorCallback(response) {
				notify("HTTP error! <br>" + "Error Status: " + response.status + '<br> Status Text:' + response.response, 'danger', true);
				if (response.data !== undefined && response.data !== null) {
					notify("Fail to query all accounts! <p>errorcode:" + response.data.errorCode + '<br> message:' + response.data.message + '<br>exception: ' + response.data.exception, 'danger', true);
				}
			});
		}
	};
	$scope.loadAvailableLabels = function(){
		var labels = [{'id':1,'labelName':'Note'},{'id':2,'labelName':'Sentiment'}];
		return labels;
	};
	$scope.cancel = function(){
		$location.path("articles");
	};
	initScope();
}]);