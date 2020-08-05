var templateApp = angular.module('templateApp', ['ngRoute','shoppingCartApp', 'shippingApp', 'paymentApp', 'orderHistoryApp', 'marketInfoApp', 'productCatalogApp']);

templateApp.config(function ($routeProvider) {
    $routeProvider
    .when('/marketInfo', {
        templateUrl: '../html/marketInfo.html',
        controller: 'marketInfoController'
    })
    .when('/shipping', {
        templateUrl: '../html/shipping.html',
        controller: 'shippingController'
    })
    .when('/orderHistory', {
        templateUrl: '../html/orderHistory.html',
        controller: 'orderHistoryController'
    })
    .when('/korpa', {
        templateUrl: '../html/shoppingCart.html',
        controller: 'shoppingCartController'
    })
    .when('/payment', {
        templateUrl: '../html/payment.html',
        controller: 'paymentController'
    })
    .when('/productCatalog', {
        templateUrl: '../html/productCatalog.html',
        controller: 'productCatalogController'
    })
});