var app = angular.module('app',
    [
        'ngResource',
        'ngRoute',
        'productService',
        'marketService'
    ]);

app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);

    $routeProvider
        .when('/product/add', {
            templateUrl: 'pages/addProduct.html',            
            title: 'Admin Service'            
        })
        .when('/product/update', {
            templateUrl: 'pages/updateProduct.html',
            title: 'Admin Service'       
        })
        .when('/product/delete', {
            templateUrl: 'pages/deleteProduct.html',
            title: 'Admin Service'            
        })
        .when('/market/add', {
            templateUrl: 'pages/addMarket.html',
            title: 'Admin Service'            
        })
        .when('/market/update', {
            templateUrl: 'pages/updateMarket.html',
            title: 'Admin Service'        
        })
        .when('/market/delete', {
            templateUrl: 'pages/deleteMarket.html',
            title: 'Admin Service'          
        })
        .otherwise({
            redirectTo: '/'
        });

}]);