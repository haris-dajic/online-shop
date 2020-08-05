app.controller('shippingController', [
    '$scope', '$http', 'shoppingCartModel', 
    function ($scope, $http, shoppingCartModel) {
      $scope.products = shoppingCartModel.getModel('products');
      $scope.price = shoppingCartModel.getModel('price');
      $scope.goToPayment = goToPayment;

      function goToPayment() {
        console.log("Shipping uspješno prošao!");
      };
    }
]);

