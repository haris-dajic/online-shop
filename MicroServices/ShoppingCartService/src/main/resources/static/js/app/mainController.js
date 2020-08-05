app.controller('mainController', [
    '$scope', '$http', 'shoppingCartModel', function($scope, $http, shoppingCartModel) {
    
        $http.get("user")
            .then(function(response) {
                $scope.user = response.data;
                shoppingCartModel.setModel('user', response.data);
            }, function(error) {
                console.log("Neuspjesan load korisnika: " + error.data.message);
        }); 
    }        
]);