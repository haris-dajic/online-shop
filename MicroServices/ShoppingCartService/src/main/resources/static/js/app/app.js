var app = angular.module("app",
    [
        'ngRoute'
    ]);

app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);

    $routeProvider
        .when('/', {
            templateUrl: 'pages/shoppingCart.html',
            controller: 'shoppingCartController',
            title: 'Shopping Cart',
            css: 'css/shoppingCart.css'
        })
        .when('/payment', {
            templateUrl: 'pages/payment.html',
            controller: 'paymentController',
            title: 'Payment',
            css: 'css/payment.css'
        })
        .when('/shipping', {
            templateUrl: 'pages/shipping.html',
            controller: 'shippingController',
            title: 'Shipping',
            css: 'css/shipping.css'
        })
        .when('/orderhistory', {
            templateUrl: 'pages/orderHistory.html',
            controller: 'orderHistoryController',
            title: 'Order history',
            css: 'css/orderHistory.css'
        })
        .otherwise({
            redirectTo: '/'
        });

}]);

