app.controller('adminMarketController',
    ['$scope', 'market', '$http', '$timeout',
    function($scope, market, $http, $timeout) {

        $scope.successfulMarketReply = null;
        $scope.unsuccessfulMarketReply = null;

        $scope.addMarketFormValues = {};
        $scope.updateMarketFormValues = {};

        $scope.markets = [];

        $scope.updateMarketID = false;
        $scope.selectMarketID = function(market_id) {
            $scope.deleteMarketID = market_id;
        };

        $scope.showUpdateMarketForm = function(market) {
            $scope.updateMarketID = true;

            $scope.updateMarketFormValues.name = market.name;
            $scope.updateMarketFormValues.contact = market.contact;
            $scope.updateMarketFormValues.email = market.email;
            $scope.updateMarketFormValues.city = market.city;
            $scope.updateMarketFormValues.address = market.address;
            $scope.updateMarketFormValues.imageURL = market.imageURL;
            $scope.updateMarketFormValues.market_id = market.id;
        };

        $scope.loadAllMarket = function() {
            $http.get('/admin/market/all')
                .then(function(response) {
                   $scope.markets = response.data;
                }, function(error) {
                    console.log(error.data);
                });

        }();

        var config = {
            headers: {
                "Accept": "text/plain"
            }
        };

        $scope.addMarket = function(addMarketFormValues) {

            var data = {
                name: addMarketFormValues.name,
                contact: addMarketFormValues.contact,
                email: addMarketFormValues.email,
                city: addMarketFormValues.city,
                address: addMarketFormValues.address,
                imageURL: addMarketFormValues.imageURL
            };

            $http.post('market', data, config)
                .then(function(response){
                    $scope.successfulMarketReply = "Market uspješno dodan!";
                    $timeout(function () {
                        location.reload();
                    }, 2000);
                }, function(error) {
                    $scope.unsuccessfulMarketReply = error.data.message;
                });
        };

        $scope.updateMarket = function(updateMarketFormValues) {

            var data = {
                name: updateMarketFormValues.name,
                contact: updateMarketFormValues.contact,
                email: updateMarketFormValues.email,
                city: updateMarketFormValues.city,
                address: updateMarketFormValues.address,
                imageURL: updateMarketFormValues.imageURL,
                market_id: updateMarketFormValues.market_id,
            };

            $http.put('market', data, config)
                .then(function(response) {
                    $scope.successfulMarketReply = "Market uspješno uređen!";
                    $timeout(function () {
                        location.reload();
                    }, 2000);
                }, function(error) {
                    $scope.unsuccessfulMarketReply = error.data.message;
                });
        }

        $scope.deleteMarket = function(market_id) {
            market.deleteMarket({ name: market_id })
                .$promise.then(function(response) {
                    $scope.successfulMarketReply = "Market uspješno obrisan!";
                    $timeout(function () {
                        location.reload();
                    }, 2000);
            }, function(error) {
                    $scope.unsuccessfulMarketReply = error.data.message;
            });
        }
    }
]);