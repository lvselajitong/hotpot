app.controller("mainCtrl",["$scope","$http","DataStore","CategoryService",function($scope,$http,DataStore,CategoryService){
	var getAllArticles,getAllCategories;
	$scope.isAddNewCategory = false;
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
				}
			}
		});
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
	$scope.showDetail = function(article){
		DataStore.selectedArticle = article;
	};
	$scope.showAddCategoryPanel = function(){
		$scope.isAddNewCategory = true;
	};
	
	$scope.addCategory = function(){
		CategoryService.addCategory($scope.newCategory).then(function(res){
			if(res != undefined){
				if(res.status === 200 && res.data.success){
					$scope.categories.push(res.data.data);
					$scope.isAddNewCategory = false;
				}else{
					notify("add categories error.","danger",true);
				}
				
			}
		});
	};
	$scope.cancelAddCategory = function(){
		$scope.isAddNewCategory = false;
	};
	getAllArticles();
	getAllCategories();
}]);