var app = angular.module('myApp', ['ngRoute', 'ngCookies','ngResource', 'ui.bootstrap', 'textAngular', 'ngTagsInput']);
app.config(
		[ '$routeProvider', '$locationProvider', '$httpProvider', function($routeProvider, $locationProvider, $httpProvider) {
			$routeProvider.when('/login', {
				templateUrl: 'view/login.html',
				controller: 'loginCtrl'
			}).when('/register',{
				templateUrl: 'view/register.html',
				controller: 'registerCtrl'
			}).when('/articles',{
				templateUrl: 'view/articles.html',
			}).when('/manageArticles',{
				templateUrl: 'view/manageArticles.html',
				controller:'manageArticlesCtrl'
			}).when('/editArticle',{
				templateUrl: 'view/editArticle.html',
				controller:'editArticleCtrl'
			}).when('/profile',{
				templateUrl: 'view/profile.html',
				controller:'profileCtrl'
			}).otherwise({
				templateUrl: 'view/dashboard.html',
				controller:'mainCtrl'
			});
			

//			$locationProvider.hashPrefix('!');

			/* Intercept http errors */
			var interceptor = function ($rootScope, $q, $location) {

		        function success(response) {
		            return response;
		        }

		        function error(response) {

		            var status = response.status;
		            var config = response.config;
		            var method = config.method;
		            var url = config.url;

		            if (status == 401) {
		            	$location.path( "/login" );
		            } else {
		            	$rootScope.error = "Invalid username or password";
		            }

		            return $q.reject(response);
		        }

		        return function (promise) {
		            return promise.then(success, error);
		        };
		    };
		    $httpProvider.interceptors.push(interceptor);

		} ]

	);

