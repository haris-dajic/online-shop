app.controller('adminController', [
    '$scope', '$http',
    function($scope, $http) {
        $scope.link = false;
        $scope.tabs = false;

        $http.get("user")
            .then(function(response) {
                $scope.user = response.data;          
            }, function(error) {
                console.log("Neuspjesan load korisnika: " + error.data.message);
        });

        $scope.tabFunction = function(value) {
            $scope.link = value;
            $scope.active = 0;
            $scope.tabs = false;
        } 

        $scope.active = 1;
	    $scope.selectTab = function(value){            
            $scope.active = value;
            $scope.tabs = true;
	    }

	    $scope.isActive = function(value){
            if($scope.active==value){
                return true;
            }
            else{
                return false;
            }
        }
    }
]);