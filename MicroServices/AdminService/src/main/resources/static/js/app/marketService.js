angular.module('marketService', ['ngResource'])
    .factory('market', [
        '$resource',
        function($resource) {
            return $resource("http://localhost:8761/admin-service/market/:name/:contact/:email/:city/:address/:imageURL/:market_id",
                {
                    name: '@name',
                    contact: '@contact',
                    email: '@email',
                    city: '@city',
                    address: '@address',
                    imageURL: '@imageURL',
                    market_id: '@market_id'
                },
                {
                    addMarket: {
                        method: 'POST'
                    },
                    
                    updateMarket: {
                        method: 'PUT'
                    },

                    deleteMarket: {
                        method: 'DELETE'
                    },

                    getAllMarket: {
                        method: 'GET',
                        isArray: true,
                        params: { name: 'all' }
                    }
                });
        }
    ]);