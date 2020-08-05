shippingApp.controller('shippingController', [
    '$scope',
    function ($scope) {
        $scope.model = {

        };
        $scope.goToPayment = goToPayment;

        function goToPayment() {
          console.log("Shipping uspješno prošao!");
      };
    }
]);

