app.controller('mainController',
    ['$scope', '$timeout', '$http', '$location',
    function($scope, $timeout, $http, $location) {
        $scope.loginFormValues = {};
        $scope.registrationFormValues = {};
        $scope.successfulRegistrationReply = null;
        $scope.unsuccessfulRegistrationReply = null;
        $scope.unsuccessfulLoginReply = null;
        $scope.locations = [{'name':'Sarajevo'}, {'name':'Banja Luka'}, {'name':'Zenica'}, {'name':'Tuzla'}, {'name':'Mostar'}, {'name':'Bihać'}, {'name':'Travnik'}, {'name':'Sarajevo'}, {'name':'Banja Luka'}, {'name':'Zenica'}, {'name':'Tuzla'}, {'name':'Mostar'}, {'name':'Bihać'}, {'name':'Travnik'}, {'name':'Sarajevo'}];
        
        $scope.register = function(registerFormValues) {
            if(registerFormValues.password != registerFormValues.password2){
                $scope.passwordsMatch = true;
            }
            else {
                $scope.passwordsMatch = false;

                var config = {
                    headers : {
                        'Accept': 'application/json'
                    }
                }

                $http.post($location.absUrl() + '/user', registerFormValues, config)
                    .then(function(response) {
                        console.log(registerFormValues);
                        console.log($location.absUrl());
                        $scope.successfulRegistrationReply = "Korisnik uspješno registrovan";
                        $timeout(function () {                    
                            window.location.href = 'http://localhost:8761/login';
                        }, 2000);
                    }, function(error) {
                        $scope.unsuccessfulRegistrationReply = error.data.message;
                    });


                /*registration.submit({
                    name: registerFormValues.name,
                    surname: registerFormValues.surname,
                    address: registerFormValues.address,
                    phoneNumber: registerFormValues.phoneNumber,
                    email: registerFormValues.email,
                    password: registerFormValues.password
                })
                    .$promise.then(function (response) {
                        $scope.successfulRegistrationReply = "Korisnik uspješno registrovan";
                    $timeout(function () {
                        location.reload();
                    }, 2000);
                }, function (response) {
                    $scope.unsuccessfulRegistrationReply = response.data.message;
                });*/
            }
        }

}]);