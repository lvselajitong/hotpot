app.controller("manageArticlesCtrl",["$scope","$http","$location","DataStore","ArticleService",function($scope,$http,$location,DataStore,ArticleService){
	var getAllArticles;
	$scope.showSelect = false;
	$scope.selectedCollection = [];
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
					angular.forEach($scope.articles,function(art){
						art.outline = art.content.substring(0,50);
					});
				}
			}
		});
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
			ArticleService.deleteById(item.id).then(function(res){
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
	getAllArticles();
}]);