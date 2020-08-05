app.controller('marketInfoController', [
    '$scope', '$http', '$location',
    function ($scope, $http, $location) {
       
        $scope.init = function() {
            var urlAllMarkets = $location.absUrl() + "market/all";        
            $http.get(urlAllMarkets).then(function (response) {
                $scope.markets = response.data;                
            }, function error(response) {
                console.log("Neuspjesan load marketa: " + response.statusText);
            });            
        }();

        $scope.sendMarketID = function(marketID){
            var url = $location.absUrl() + "market";
            
            var data = { market_id: marketID};
            $http.post(url, data).then(function (response) {
                $location.url('/productcatalog');
            }, function error(response) {
                console.log("Neuspjesno slanje id marketa: " + response.statusText);
            });
        };


    }
]);
