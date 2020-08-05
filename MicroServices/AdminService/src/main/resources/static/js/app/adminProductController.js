app.controller('adminProductController',
    ['$scope', 'product', '$http', '$timeout',
    function($scope, product, $http, $timeout) {

        $scope.successfulProductReply = null;
        $scope.unsuccessfulProductReply = null;

        $scope.addProductFormValues = {};
        $scope.updateProductFormValues = {};

        $scope.products = [];
        $scope.productAmount = [];
        $scope.deleteList = [];

        $scope.updateProductID = false;
        $scope.addProductFormShown = false;
        $scope.updateProductFormShown = false;

        $scope.showAddProductForm = function(market_id) {
            $scope.addProductFormShown = true;
            $scope.addProductFormValues.market_id = market_id;
        };

        $scope.showUpdateProductForm = function(market_id) {
            $scope.updateProductFormShown = true;
            $scope.updateProductFormValues.market_id = market_id;
        };

        $scope.showDeleteProductForm = function(market_id) {
            $scope.deleteProductFormShown = true;
            $scope.deleteProductMarketID = market_id;
        };

        $scope.showRelevantProductOnUpdate = function(market_id) {
            if($scope.updateProductFormValues.market_id === market_id)
                return true;
            return false;
        };

        $scope.showRelevantProductOnDelete = function(market_id) {
            if($scope.deleteProductMarketID === market_id)
                return true;
            return false;
        };

        $scope.showUpdateProductForm = function(product, amount) {
            $scope.updateProductID = true;

            $scope.updateProductFormValues.name = product.name;
            $scope.updateProductFormValues.category = product.category;
            $scope.updateProductFormValues.amount = amount;
            $scope.updateProductFormValues.price = product.price;
            $scope.updateProductFormValues.discount = product.discount;
            $scope.updateProductFormValues.barcode = product.barcode;
            $scope.updateProductFormValues.description = product.description;
            $scope.updateProductFormValues.imageURL = product.urlSlike;
            $scope.updateProductFormValues.product_id = product.id;
        }

        $scope.addToDeleteList = function(product_id) {
            if($scope.deleteList.includes(product_id)){
                var index = $scope.deleteList.indexOf(product_id);
                if (index !== -1) $scope.deleteList.splice(index, 1);
            }
            else
                $scope.deleteList.push(product_id);
        }

        $scope.loadAllProduct = function() {
            $http.get('product/all')
                .then(function(response) {
                    $scope.products = response.data;
                }, function(error) {
                    console.log(error.data);
                });
        }();

        $scope.loadAllProductAmount = function() {
            $http.get('productamount/all')
                .then(function(response) {
                    $scope.productAmount = response.data;
                }, function(error) {
                    console.log(error.data);
                });
        }();

        var config = {
            headers: {
                "Accept": "application/json"
            }
        };

        $scope.addProduct = function(addProductFormValues) {
            var data = {
                name: addProductFormValues.name,
                category: addProductFormValues.category,
                amount: addProductFormValues.amount,
                price: addProductFormValues.price,
                discount: addProductFormValues.discount,
                barcode: addProductFormValues.barcode,
                description: addProductFormValues.description,
                imageURL: addProductFormValues.imageURL,
                market_id: addProductFormValues.market_id
            };

            $http.post('product', data, config)
                .then(function(response) {
                    $scope.successfulProductReply = "Proizvod uspješno dodan!";
                    $timeout(function () {
                        location.reload();
                    }, 2000);
                }, function(error) {
                    $scope.unsuccessfulProductReply = error.data.message;
                });
        }

        $scope.updateProduct = function(updateProductFormValues) {
            var data = {
                name: updateProductFormValues.name,
                category: updateProductFormValues.category,
                amount: updateProductFormValues.amount,
                price: updateProductFormValues.price,
                discount: updateProductFormValues.discount,
                barcode: updateProductFormValues.barcode,
                description: updateProductFormValues.description,
                imageURL: updateProductFormValues.imageURL,
                product_id: updateProductFormValues.product_id,
                market_id: updateProductFormValues.market_id
            };

            $http.put('product', data, config)
                .then(function(response) {
                    $scope.successfulProductReply = "Proizvod uspješno uređen!";
                    $timeout(function () {
                        location.reload();
                    }, 2000);
                }, function(error) {
                    $scope.unsuccessfulProductReply = error.data.message;
                });
        }

        $scope.deleteProduct = function() {
            for(i = 0; i < $scope.deleteList.length; i++) {
                product.deleteProduct({ name: $scope.deleteList[i] })
                    .$promise.then(function(response) {
                    $scope.successfulProductReply = "Proizvod uspješno obrisan!";
                    $timeout(function () {
                        location.reload();
                    }, 2000);
                }, function(error) {
                    $scope.unsuccessfulProductReply = error.data.message;
                });
            }
        };
    }
]);
