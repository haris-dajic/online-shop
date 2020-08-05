app.controller('headerController', [
    '$scope', '$http',
    function($scope, $http) {        
        $http.get('user').then(function (response) {
            $scope.user = response.data;
        }, function error(error) {
            console.log("Neuspjesan prijem user-a: " + error.data.message);
        });
    }
]);