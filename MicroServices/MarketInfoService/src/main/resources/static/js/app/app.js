var app = angular.module('app',
    [        
        'ngRoute',
        'angularCSS'
    ]);

app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);

    $routeProvider
        .when('/', {
            templateUrl: 'pages/marketInfo.html',
            controller: 'marketInfoController',
            title: 'Market Info',
            css: 'css/marketInfo.css'            
        })
        .when('/productcatalog', {
            templateUrl: 'pages/productCatalog.html',
            controller: 'productCatalogController',
            title: 'Admin Service',
            css: 'css/productCatalog.css'      
        })
        .otherwise({
            redirectTo: '/'
        });
}]);