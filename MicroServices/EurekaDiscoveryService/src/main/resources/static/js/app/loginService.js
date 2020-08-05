angular.module('loginService', ['ngResource'])
    .factory('login', [
        '$resource',
        function($resource) {
            return $resource("http://localhost:8761/login/:email/:password",
                {
                    email: '@email',
                    password: '@password'
                },
                {
                    login: {
                        method: 'GET'
                    }
                });
        }
    ]);