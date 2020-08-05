paymentApp.controller('paymentController', [
    '$scope', 
    function ($scope) {
        $scope.model = {

        };
        $scope.paymentDone = paymentDone;

        function paymentDone() {
          console.log("Plaćanje uspješno završeno!");
      };
    }
]);