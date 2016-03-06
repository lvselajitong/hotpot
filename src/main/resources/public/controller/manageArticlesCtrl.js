app.controller("manageArticlesCtrl",["$scope","$http","$location","DataStore","ArticleService","UserIdentity",function($scope,$http,$location,DataStore,ArticleService,UserIdentity){
	var getArticlesByAuthId;
	$scope.showSelect = false;
	$scope.selectedCollection = [];
	getArticlesByAuthId = function(){
		if(DataStore.selectedAuth.length === 0){
			$http({
				method : 'GET',
				url : '/article/' + UserIdentity.get().id,
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded',
				}
			}).then(function(res){
				if (res !== undefined) {
					if (res.status === 200 && res.data.success) {
						$scope.articles = res.data.data.articles;
						angular.forEach($scope.articles,function(art){
							art.outline = art.content.substring(0,50);
						});
					}
				}
			});
		} else {
			$scope.articles = DataStore.selectedAuth.articles;
			angular.forEach($scope.articles,function(art){
				art.outline = art.content.substring(0,50);
			});
		}
		
	};
	
	$scope.addNewArticle = function() {
		$location.path("editArticle");
	};
	$scope.edit = function(article) {
		DataStore.selectedArticle = article;
		$location.path("editArticle");
	};
	$scope.select = function(){
		$scope.showSelect = true;
	};
	$scope.cancel = function(){
		$scope.showSelect = false;
	};
	$scope.changeSelectedCollection = function(article){
		if(article.isSelected || article.isSelected === undefined){
			$scope.selectedCollection.push(article);
		}else{
			for(var i=0;i<$scope.selectedCollection.length;i++){
				if($scope.selectedCollection[i].id === article.id){
					$scope.selectedCollection.splice(i,1);
				}
			}
		}
	};
	$scope.deleteArticles = function(){
		angular.forEach($scope.selectedCollection,function(item){
			ArticleService.deleteById(item.id,UserIdentity.get().id).then(function(res){
				if(res !== undefined){
					if(res.status === 200 && res.data.success) {
						for(var i=0;i<$scope.articles.length;i++){
							if($scope.articles[i].id===item.id){
								$scope.articles.splice(i,1);
							}
						}
						$scope.showSelect = false;
					}else{
						console.log("Delete error");
					}
				}				
			});	
		});
		$scope.selectedCollection = [];
	};
	getArticlesByAuthId();
}]);