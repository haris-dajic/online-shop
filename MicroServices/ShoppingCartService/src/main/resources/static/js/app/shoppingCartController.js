app.controller('shoppingCartController', [
    '$scope', '$http', 'shoppingCartModel', 
    function ($scope, $http, shoppingCartModel) {
        
        $scope.eraseOrder = eraseOrder;
        $scope.calculatePrice = calculatePrice;
        $scope.removeProduct = removeProduct;
        $scope.lowerProductAmountByOne = lowerProductAmountByOne;

        $scope.init = function init() {            

            var detailsScrollBar = document.getElementById("detailsScrollBar");
            var nextBtn = document.getElementById("nextBtn");

            $http.get("product/all").then(function (response) {
                $scope.products = response.data;
                shoppingCartModel.setModel('products', response.data);

                $scope.styleOrder = styleOrder;              
                $scope.disableNextBtn = disableNextBtn;                               

                disableNextBtn();
                styleOrder();
                calculatePrice();
                
                function disableNextBtn() {
                    if ($scope.products.length === 0)
                        nextBtn.classList.add("disabled");
                    else
                        nextBtn.classList.remove("disabled");
                }

                function styleOrder() {
                    switch ($scope.products.length) {
                        case 0:
                            detailsScrollBar
                                .classList
                                .remove("detailsScrollBar");
                            break;
                        case 1:
                            detailsScrollBar
                                .classList
                                .remove("detailsScrollBar");
                            break;
                        case 2:
                            detailsScrollBar
                                .classList
                                .remove("detailsScrollBar");
                            break;
                        default:
                            detailsScrollBar
                                .classList
                                .add("detailsScrollBar");
                    }
                }                             
            }, function error(response) {
                console.log("Neuspjesan load producta: " + response.statusText);
            });
            
        }();

        function calculatePrice() {
            $scope.price = {
                priceWithoutVAT: 0,
                VAT: 0,
                discount: 10,
                total: 0
            };

            for (var i = 0; i < $scope.products.length; i++)
                $scope.price.total += $scope.products[i].price * $scope.products[i].amount;

            $scope.price.total = $scope.price.total * (1 - $scope.price.discount / 100);
            $scope.price.VAT = $scope.price.total * 0.17;                    
            $scope.price.priceWithoutVAT = $scope.price.total - $scope.price.VAT;

            shoppingCartModel.setModel('price', $scope.price);
        }

        function removeProduct(product) {
            for (var i = 0; i < $scope.products.length; i++)
                if (product.id == $scope.products[i].id) {
                    $scope.products.splice(i, 1);
                    i--;
                }
            calculatePrice();
            shoppingCartModel.setModel('products', $scope.products);   

            $http.put('product', product).then(function (response) {
                console.log("Proizvod uspjesno izbrisan!")
            }, function error(response) {
                console.log("Neuspjesno ponistavanje narudzbe: " + response.data.message);
            });
        }

        function lowerProductAmountByOne(product) {
            if(product.amount == 1)
                removeProduct(product);
            else
                $http.put('product/lower', product)
                    .then(function(response) {
                        console.log("Uspjesno uklanjanje jednog proizvoda");
                    }, function(error) {
                        console.log("Neuspjesno uklanjanje jednog proizvoda: " + response.data.message);
                    });

            for (var i = 0; i < $scope.products.length; i++)
                if (product.id == $scope.products[i].id) {
                    $scope.products[i].amount -= 1;                            
                }
            calculatePrice();
            shoppingCartModel.setModel('products', $scope.products);                                                                                       
        }

        function eraseOrder() {
            for (var i = 0; i < $scope.products.length; i++) {
                $http.put('product', $scope.products[i]).then(function (response) {
                    console.log("Proizvod uspjesno izbrisan!")
                }, function error(response) {
                    console.log("Neuspjesno ponistavanje narudzbe: " + response.statusText);
                }); 
            }
            $scope.products = [];
            calculatePrice();
            shoppingCartModel.setModel('products', $scope.products);   
        }
    }
]);