app.controller("postDetailCtrl",["$scope","$http","DataStore","ArticleService",function($scope,$http,DataStore,ArticleService){
	$scope.article = DataStore.selectedArticle;
}]);