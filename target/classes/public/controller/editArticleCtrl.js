app.controller("editArticleCtrl",["$scope","$http","$location","ArticleService","DataStore","UserIdentity","CategoryService",function($scope,$http,$location,ArticleService,DataStore,UserIdentity,CategoryService){
	var initScope,getAllCategories;
	initScope = function(){
		if(DataStore.selectedArticle.length !== 0){
			$scope.curArticle = DataStore.selectedArticle;
		}
		
	};
	getAllCategories = function(){
		CategoryService.getAllCategories().then(function(res){
			if(res != undefined){
				if(res.status === 200 && res.data.success){
					$scope.categories = res.data.data;
				}else{
					notify("load categories error.","danger",true);
				}
			}
		});
	};
	$scope.add = function(){
		var curDate = new Date();
		if($scope.curArticle.title!== undefined){
			ArticleService.addArticle($scope.curArticle.title,$scope.curArticle.content,UserIdentity.get().id,"Publish",$scope.selectedCategory).then(function(res){
				if (res !== undefined) {
					if (res.status === 200 && res.data.success) {
						DataStore.selectedAuth = res.data.data;  
						$location.path("/manageArticles");
					}else{
						console.log("add failed");
					}
				}
			});
		}
	};
	$scope.draft = function(){
		var curDate = new Date();
		if($scope.curArticle.title!== undefined){
			ArticleService.addArticle($scope.curArticle.title,$scope.curArticle.content,UserIdentity.get().id,"Draft").then(function(res){
				if (res !== undefined) {
					if (res.status === 200 && res.data.success) {
						$location.path("/manageArticles");
					}else{
						console.log("draft failed");
					}
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
	getAllCategories();
}]);