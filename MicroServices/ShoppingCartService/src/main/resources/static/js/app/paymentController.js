app.controller('paymentController', [
    '$scope', '$http', 'shoppingCartModel',
    function ($scope, $http, shoppingCartModel) {
      $scope.products = shoppingCartModel.getModel('products');
      $scope.price = shoppingCartModel.getModel('price');
      $scope.paymentDone = function() {
        console.log("Plaćanje uspješno završeno!");
      };
    }
]);