app.factory('DataStore', function () {
	var selectedArticle = [];
	var selectedAuth = [];
	return {
		selectedArticle : selectedArticle,
		selectedAuth : selectedAuth
	};
});