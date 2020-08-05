angular.module('registrationService', ['ngResource'])
    .factory('registration', [
        '$resource',
        function($resource) {
            return $resource("http://localhost:8761/register/:name/:surname/:address/:phoneNumber/:email/:password",
                {
                    name: '@name',
                    surname: '@surname',
                    address: '@address',
                    phoneNumber: '@phoneNumber',
                    email: '@email',
                    password: '@password'
                },
                {
                    submit: {
                        method: 'POST'
                    }
                });
        }
    ]);