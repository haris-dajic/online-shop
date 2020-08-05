angular.module('productService', ['ngResource'])
    .factory('product', [
        '$resource',
        function($resource) {
            return $resource("http://localhost:8761/admin-service/product/:name/:category/:amount/:price/:discount/:barcode/:description/:imageURL/:product_id",
                {
                    name: '@name',
                    category: '@category',
                    amount: '@amount',
                    price: '@price',
                    discount: '@discount',
                    barcode: '@barcode',
                    description: '@description',
                    imageURL: '@imageURL',
                    product_id: '@product_id'
                },
                {
                    addProduct: {
                        method: 'POST'
                    },

                    updateProduct: {
                        method: 'PUT'
                    },

                    deleteProduct: {
                        method: 'DELETE'
                    },

                    getAllProduct: {
                        method: 'GET',
                        isArray: true,
                        params: { name: 'all' }
                    }
                });
        }
    ])