app.controller('productCatalogController', [
    '$scope', '$http', '$location',
    function ($scope, $http, $location) {
        $scope.productAmount = 1;

        $scope.addProductToShoppingCart = addProductToShoppingCart;        
        $scope.fillProduct = fillProduct;

        function fillProduct(products, amounts) {
            for (var i = 0; i < products.length; i++) {
                $scope.products[i].amount = amounts[i].amount;
                $scope.products[i].productAmount_id = amounts[i].id;
            }
        };

        $scope.init = function(){
            var getAllProductsURL = $location.absUrl() + "/product/all";
            var getMarketNameURL = $location.absUrl() + "/market/name";
            var getProductAmountsURL = $location.absUrl() + "/productamount/all";

            $http.get(getAllProductsURL).then(function (response) {
                    $scope.products = response.data;


            $http.get(getMarketNameURL).then(function (response) {
                $scope.marketName = response.data;


            $http.get(getProductAmountsURL).then(function (response) {
                $scope.amounts = response.data;           

                fillProduct($scope.products, $scope.amounts);

            }, function error(response) {
                console.log("Neuspjesan load amounts: " + response.statusText);
            });
            }, function error(response) {
                console.log("Neuspjesan load proizvoda: " + response.statusText);
            });
            }, function error(response) {
                console.log("Neuspjesan load za name marketa: " + response.statusText);
            });
        }();
    
        function addProductToShoppingCart(product, productAmount) {
            var notification = document.getElementById("shoppingCartNotification");

            var config = {
                headers : {
                    'Accept': 'application/json'
                }
            }
            var dataProduct = {
                id: product.id,
                name: product.name,
                category: product.category,
                price: product.price,
                discount: product.discount,
                barcode: product.barcode,
                description: product.description,
                imageURL: product.imageURL,
                amount: productAmount,
                productAmount_id: product.productAmount_id
            };

            $http.post('productcatalog/product/add', dataProduct, config).then(function () {
                notification.className = "show";

                setTimeout(function () {
                    notification.className = notification
                        .className
                        .replace("show", "");
                }, 1200);

                for(var i = 0; i < $scope.products.length; i++) {
                    if($scope.products[i].id == product.id)
                        $scope.products[i].amount -= productAmount;
                }

            }, function error(response) {
                console.log("Error with status: " +  response.data.message);
            });
        }
    }
]);